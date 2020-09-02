/*
 * Copyright (c) 2018, James Swindle <wilingua@gmail.com>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.motherloadmine;

import com.google.inject.Provides;

import javax.annotation.Nullable;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;

import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.queries.WallObjectQuery;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import net.runelite.client.plugins.botutils.BotUtils;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.HotkeyListener;
import net.runelite.client.menus.MenuManager;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import org.pf4j.Extension;

import java.time.Instant;


@Extension
@PluginDescriptor(
        name = "Pinq's Motherload Mine",
        description = "Does Motherload mine flawelessly",
        tags = {"pinqer", "Mining", "3 Tick", "Tick", "mlm"},
        type = PluginType.SKILLING
)
@Slf4j
@PluginDependency(BotUtils.class)
public class MotherloadMinePlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private MotherloadMineConfig config;

    @Inject
    private BotUtils utils;

    @Inject
    private KeyManager keyManager;

    @Inject
    private MenuManager menuManager;

    @Inject
    OverlayManager overlayManager;

    @Inject
    MotherloadMineOverlay overlay;

    @Inject
    ExecutorService executorService;

    Player player;
    LocalPoint beforeLoc = new LocalPoint(0, 0);
    MenuEntry targetMenu;
    Instant botTimer;
    int tickTiming = 0;
    int initialLevel = 0;
    long sleepLength;
    String status;
    MotherloadMineState state;
    int depositCount = 0;
    int timeout = 0;
    boolean clickedHopper;
    boolean startMining = false;
    boolean animating = false;
    boolean moving = false;
    private final Set<Integer> requiredIds = new HashSet<>();
    boolean run = false;
    boolean timeout2 = false;
    boolean foundOre = false;
    boolean clickOre = false;
    boolean clickedOre = false;
    boolean isNoVeinFound = false;
    WorldPoint oreVeinPoint = new WorldPoint(3729, 5677, 0);

    @Provides
    MotherloadMineConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(MotherloadMineConfig.class);
    }

    @Override
    protected void startUp() {
        overlayManager.add(overlay);
        keyManager.registerKeyListener(hotkeyListener);
        if (config.floorSelect() == MotherloadMineConfig.OptionEnum.UPPERLEVEL);
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(overlay);
        tickTiming = 0;
        keyManager.unregisterKeyListener(hotkeyListener);
    }

    public MotherloadMineState getState()
    {
        if (timeout > 0) {
            timeout2 = true;
            return MotherloadMineState.TIMEOUT;
        } else {
            timeout2 = false;
        }
        if (utils.isMoving(beforeLoc))
        {
            moving = true;
            return MotherloadMineState.MOVING;
        } else {
            moving = false;
        }

        if (utils.isAnimating()) {
            animating = true;
            return MotherloadMineState.ANIMATING;
        } else {
            animating = false;
        }

        if (utils.getInventoryItemCount(ItemID.PAYDIRT, false) > 23)
        {
            return MotherloadMineState.DEPOSIT;
        }

        if (!utils.isDepositBoxOpen()) {
            if (utils.inventoryContains(ItemID.COAL) || utils.inventoryContains(ItemID.GOLD_ORE) || utils.inventoryContains(ItemID.MITHRIL_ORE)) {
                return MotherloadMineState.DEPOSITBOX;
            }
        }
        if (utils.inventoryContains(ItemID.COAL) || utils.inventoryContains(ItemID.GOLD_ORE) || utils.inventoryContains(ItemID.MITHRIL_ORE)) {
            if (utils.isDepositBoxOpen()) {
                return MotherloadMineState.DEPOSIT_ORES;
            }
        }

        if (startMining) {
            return MotherloadMineState.MINE;
        }

        if (depositCount == 2) {
            return MotherloadMineState.SACK;
        }

        if (isSackEmpty()) {
            return MotherloadMineState.GO_MINE;
        }

        return MotherloadMineState.GO_MINE;
    }

    @Subscribe
    public void onGameTick(GameTick tick) {
        log.info(String.valueOf(depositCount) + ", " + timeout + ", " + startMining);
        if (!run)
        {
            return;
        }
        player = client.getLocalPlayer();
        if (client != null && player != null)
        {
            state = getState();
            beforeLoc = player.getLocalLocation();
            switch (state)
            {
                case TIMEOUT:
                    utils.sendGameMessage("TIMEOUT");
                    log.info("TIMEOUT");
                    utils.handleRun(30, 20);
                    timeout--;
                    break;
                case ANIMATING:
                    utils.sendGameMessage("ANIMATING");
                    log.info("ANIMATING");
                    if (player.getAnimation() == 6752) {
                        timeout = 7;
                    } else {
                        timeout = 2;
                    }
                    break;
                case MOVING:
                    utils.sendGameMessage("MOVING");
                    log.info("MOVING");
                    timeout = 1;
                    break;
                case MINE:
                    utils.sendGameMessage("MINE");
                    log.info("MINE");
                    clickOre();
                    timeout = 3;
                    break;
                case DEPOSIT:
                    utils.sendGameMessage("DEPOSIT");
                    log.info("DEPOSIT");
                    if (isRockfall()) {
                        handleRockfall();
                    } else {
                        clickHopper();
                    }
                    break;
                case SACK:
                    utils.sendGameMessage("SACK");
                    log.info("SACK");
                    clickSack();
                    break;
                case DEPOSITBOX:
                    utils.sendGameMessage("DEPOSITBOX");
                    log.info("DEPOSITBOX");
                    clickDepositBox();
                    break;
                case DEPOSIT_ORES:
                    utils.sendGameMessage("DEPOSIT_ORES");
                    log.info("DEPOSIT_ORES");
                    depositOres();
                    break;
                case GO_MINE:
                    utils.sendGameMessage("GO MINE");
                    log.info("GO_MINE");
                    if (isRockfall()) {
                        handleRockfall();
                        timeout = 2;
                    } else {
                        timeout = 1;
                    }
                    break;
            }
        }
    }

    public void depositOres() {
        requiredIds.add(ItemID.HAMMER);
        requiredIds.add(ItemID.DRAGON_PICKAXE);
        requiredIds.add(ItemID.RUNE_PICKAXE);
        requiredIds.add(ItemID.ADAMANT_PICKAXE);
        requiredIds.add(ItemID.MITHRIL_PICKAXE);
        requiredIds.add(ItemID.BLACK_PICKAXE);
        requiredIds.add(ItemID.STEEL_PICKAXE);

        utils.depositAllExcept(requiredIds);
        timeout = utils.getRandomIntBetweenRange(1,3);
    }

    public boolean isSackEmpty() {
        GroundObject emptySackCheck = utils.findNearestGroundObject(26688);
        return emptySackCheck.getModel().getHash() == 27317424;
    }

    public void clickDepositBox() {
        GameObject depositBox = utils.findNearestGameObject(25937);
        if (depositBox != null) {
            targetMenu = new MenuEntry("Deposit", "Deposit Box", depositBox.getId(), MenuOpcode.GAME_OBJECT_FIRST_OPTION.getId(),
                    depositBox.getSceneMinLocation().getX(), depositBox.getSceneMinLocation().getY(), false);
            utils.delayClickRandomPointCenter(-75,75, sleepDelay());
        }
    }

    public void clickSack() {
        GroundObject sack = utils.findNearestGroundObject(26688);
        if (sack != null) {
            targetMenu = new MenuEntry("Empty", "Sack", sack.getId(), MenuOpcode.GAME_OBJECT_FIRST_OPTION.getId(),
                    sack.getLocalLocation().getSceneX(), sack.getLocalLocation().getSceneY(), false);
            utils.delayClickRandomPointCenter(-75,75, sleepDelay());
        }
        if (isSackEmpty()) {
            log.info("Deposit count reset");
            depositCount = 0;
        }
    }

    public void clickOre() {
        clickOre = true;
        log.info("Clicking ore");
        utils.sendGameMessage("Clicking ore");
        oreVeinPoint = new WorldPoint(3729, 5677, 0);
        WallObject oreVein = findNearestWallObjectWithin(oreVeinPoint, 4, 26663, 26662, 26664);
        if (oreVein != null) {
            log.info("Found ore");
            utils.sendGameMessage("Found ore");
            foundOre = true;
            targetMenu = new MenuEntry("Mine", "Ore Vein", oreVein.getId(), MenuOpcode.GAME_OBJECT_FIRST_OPTION.getId(),
                    oreVein.getLocalLocation().getSceneX(), oreVein.getLocalLocation().getSceneY(), false);
            utils.delayClickRandomPointCenter(-75,75, sleepDelay());
            clickedOre = true;
        } else {
            isNoVeinFound = true;
            log.info("Didn't Found ore");
            utils.sendGameMessage("Didn't find ore");
        }
    }

    public boolean isRockfall() {
        WorldPoint rockfallPoint = new WorldPoint(3731, 5682, 0);
        GameObject rockfall = utils.findNearestGameObjectWithin(rockfallPoint, 5, 26679, 26680);
        if (rockfall != null) {
            return true;
        } else {
            WorldPoint startPoint = new WorldPoint(3727, 5682, 0);
            if (startPoint.distanceTo(player.getWorldLocation()) < 5) {
                startMining = true;
            }
            return false;
        }
    }

    public void handleRockfall() {
        log.info("Handling rock fall");
        WorldPoint rockfallPoint = new WorldPoint(3731, 5682, 0);
        GameObject rockfall = utils.findNearestGameObjectWithin(rockfallPoint, 5, 26679, 26680);
        if (startMining) {
            log.info("Started Mining = true");
            WorldPoint rockfallPoint2 = new WorldPoint(3729, 5684, 0);
            GameObject rockfall2 = utils.findNearestGameObjectWithin(rockfallPoint2, 3, 26679, 26680);
            if (rockfall2 != null) {
                log.info("Changed rock fall location priority");
                rockfall = rockfall2;
            }
        }
        if (rockfall != null) {
            log.info("Found rock fall");
            targetMenu = new MenuEntry("Mine", "Rockfall", rockfall.getId(), MenuOpcode.GAME_OBJECT_FIRST_OPTION.getId(),
                    rockfall.getLocalLocation().getSceneX(), rockfall.getLocalLocation().getSceneY(), false);
            utils.delayClickRandomPointCenter(-75,75, sleepDelay());
        } else {
            log.info("Didnt find rockfall");
        }
    }

    public void clickHopper() {
        GameObject hopper = utils.findNearestGameObject(26674);
        if (hopper != null) {
            targetMenu = new MenuEntry("Deposit", "Hopper", hopper.getId(), MenuOpcode.GAME_OBJECT_FIRST_OPTION.getId(),
                    hopper.getSceneMinLocation().getX(), hopper.getSceneMinLocation().getY(), false);
            utils.delayClickRandomPointCenter(-75,75, sleepDelay());
            clickedHopper = true;
            startMining = false;
        }
    }

    private long sleepDelay() {
        sleepLength = utils.randomDelay(false, 100, 300, 30, 200);
        return sleepLength;
    }

    @Subscribe
    public void onItemContainerChanged(ItemContainerChanged event) {
        if (clickedHopper) {
            depositCount++;
            clickedHopper = false;
        }
    }

    @Nullable
    public WallObject findNearestWallObjectWithin(WorldPoint worldPoint, int dist, int... ids)
    {
        utils.sendGameMessage("FINDDING NEAREST WALL OBJECT");
        assert client.isClientThread();

        if (client.getLocalPlayer() == null)
        {
            return null;
        }

        return new WallObjectQuery()
                .idEquals(ids)
                .isWithinDistance(worldPoint, dist)
                .result(client)
                .nearestTo(client.getLocalPlayer());
    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (targetMenu == null) {
            //log.info("Modified MenuEntry is null");
        } else {
            log.info("MenuEntry string event: " + targetMenu.toString());
            event.setMenuEntry(targetMenu);
            targetMenu = null; //this allows the player to interact with the client without their clicks being overridden
        }
    }

    public HotkeyListener hotkeyListener = new HotkeyListener(() -> config.toggleKey()) {
        @Override
        public void hotkeyPressed() {
            run = !run;
            if (!run) {
                log.info("Stopped");
                status = "Starting...";
                startMining = false;
                foundOre = false;
                clickedOre = false;
                clickOre = false;
                isNoVeinFound = false;
            }
            if (run) {
                log.info("Started");
                initialLevel = client.getRealSkillLevel(Skill.MINING);
                botTimer = Instant.now();
            }
        }
    };
}
