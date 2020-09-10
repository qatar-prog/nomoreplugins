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
package net.runelite.client.plugins.cursealch;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import net.runelite.client.plugins.botutils.BotUtils;
import net.runelite.client.ui.overlay.OverlayManager;
//import net.runelite.client.plugins.chinbreakhandler.ChinBreakHandler;
import org.pf4j.Extension;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import java.time.Instant;
import java.util.concurrent.ExecutorService;


@Extension
@PluginDescriptor(
        name = "Pinq's Curse alch",
        description = "Curse alch's for massive magic gains",
        tags = {"pinqer", "Fishing", "Tick", "Magic"},
        type = PluginType.SKILLING
)
@Slf4j
@PluginDependency(BotUtils.class)
public class CurseAlchPlugin extends Plugin implements KeyListener {

    @Inject
    private Client client;

    @Inject
    private CurseAlchConfig config;

    @Inject
    private BotUtils utils;

    @Inject
    private KeyManager keyManager;

    @Inject
    private MenuManager menuManager;

    @Inject
    OverlayManager overlayManager;

    @Inject
    CurseAlchOverlay overlay;

 //   @Inject
 //   ChinBreakHandler chinBreakHandler;

    @Inject
    ExecutorService executorService;

    Player player;
    LocalPoint beforeLoc = new LocalPoint(0, 0);
    MenuEntry targetMenu;
    Instant botTimer;
    int timeout;
    long sleepLength;
    long sleepLength2;
    CurseAlchState state;
    String status;
    NPC minnowsSpot;
    boolean wait = true;
    int ticks = 0;
    private static final int[] FILLED_MAP_REGION = {8011};
    boolean shouldCast = true;
    boolean run = false;
    WidgetInfo spell;

    @Provides
    CurseAlchConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(CurseAlchConfig.class);
    }

    @Override
    protected void startUp() {
        overlayManager.add(overlay);
        keyManager.registerKeyListener(this);
     //   chinBreakHandler.registerPlugin(this);
        botTimer = Instant.now();
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(overlay);
        keyManager.unregisterKeyListener(this);
     //   chinBreakHandler.unregisterPlugin(this);
    }

    private CurseAlchState getState() {
        if (timeout > 0)
        {
            return CurseAlchState.TIMEOUT;
        }

        if (utils.isAnimating() && canAlch() && player.getAnimation() != 713) {
            shouldCast = true;
            return CurseAlchState.ALCH_BOOK;
        }

        if (shouldCast) {
            shouldCast = false;
            return CurseAlchState.SPELL_TARGET;
        }



      //  if (chinBreakHandler.shouldBreak(this))
      //  {
      //      return HANDLE_BREAK;
      //  }
        shouldCast = true;
        return CurseAlchState.TIMEOUT;
    }

    @Subscribe
    private void onGameTick(GameTick event)
    {
        if (!run)
        {
            return;
        }
        player = client.getLocalPlayer();
        if (client != null && player != null && client.getGameState() == GameState.LOGGED_IN)
        {
            ticks++;
            state = getState();
            switch (state)
            {
                case TIMEOUT:
                    log.info("TIMEOUT");
                    timeout--;
                    break;
                case ALCH_BOOK:
                    log.info("ALCH");
                    castHighAlch(856);
                    timeout = utils.getRandomIntBetweenRange(1,2);
                    break;
                case SPELL_TARGET:
                    log.info("SPELL_TARGET");
                    NPC target = utils.findNearestNpc(528);
                    if (target != null) {
                        targetMenu = new MenuEntry("", "", 21691, MenuOpcode.SPELL_CAST_ON_NPC.getId(),
                                0, 0, false);
                        utils.oneClickCastSpell(getSpell(), targetMenu, target.getConvexHull().getBounds(), sleepDelay());
                        timeout = utils.getRandomIntBetweenRange(1,2);
                    } else {
                        log.info("TARGET IS NULL");
                    }
                    break;
            }
            beforeLoc = player.getLocalLocation();
        }
    }

    //2020-09-06 19:44:13 [Client] INFO  n.r.c.p.p.PestControlPlugin - Cast
    //2020-09-06 19:44:13 [Client] INFO  n.r.c.p.p.PestControlPlugin - <col=00ff00>Vulnerability</col><col=ffffff> -> <col=ffff00>Monk of Zamorak<col=ff00>  (level-17)
    //2020-09-06 19:44:13 [Client] INFO  n.r.c.p.p.PestControlPlugin - 8
    //2020-09-06 19:44:13 [Client] INFO  n.r.c.p.p.PestControlPlugin - 21691
    //2020-09-06 19:44:13 [Client] INFO  n.r.c.p.p.PestControlPlugin - 0
    //2020-09-06 19:44:13 [Client] INFO  n.r.c.p.p.PestControlPlugin - 0



    private WidgetInfo getSpell() {
        if (config.type().name() == "CURSE") {
            spell = WidgetInfo.SPELL_CURSE;
        } else if (config.type().name() == "BIND") {
            spell = WidgetInfo.SPELL_BIND;
        } else if (config.type().name() == "SNARE") {
            spell = WidgetInfo.SPELL_SNARE;
        } else if (config.type().name() == "VULNERABILITY") {
            spell = WidgetInfo.SPELL_VULNERABILITY;
        } else if (config.type().name() == "ENFEEBLE") {
            spell = WidgetInfo.SPELL_ENFEEBLE;
        } else if (config.type().name() == "ENTANGLE") {
            spell = WidgetInfo.SPELL_ENTANGLE;
        } else if (config.type().name() == "STUN") {
            spell = WidgetInfo.SPELL_STUN;
        }
        return spell;
    }
    private long sleepDelay() {
        sleepLength = utils.randomDelay(config.sleepWeightedDistribution(), config.sleepMin(), config.sleepMax(), config.sleepDeviation(), config.sleepTarget());
        return sleepLength;
    }

    private boolean canAlch()
    {
        return client.getWidget(WidgetInfo.SPELL_HIGH_LEVEL_ALCHEMY) != null;
    }

    private void castHighAlch(Integer itemID)
    {
        WidgetItem alchItem = utils.getInventoryWidgetItem(itemID);
        if (alchItem != null)
        {
            log.info("Alching item: {}", alchItem.getId());
            targetMenu = new MenuEntry("", "",
                    alchItem.getId(),
                    MenuOpcode.ITEM_USE_ON_WIDGET.getId(),
                    alchItem.getIndex(), WidgetInfo.INVENTORY.getId(),
                    false);
            utils.oneClickCastSpell(WidgetInfo.SPELL_HIGH_LEVEL_ALCHEMY, targetMenu, alchItem.getCanvasBounds().getBounds(), sleepDelay());
        }
        else
        {
            log.info("castHighAlch widgetItem is null");
        }
    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (targetMenu == null) {
            log.info("Modified MenuEntry is null");
        } else {
            log.info("MenuEntry string event: " + targetMenu.toString());
            event.setMenuEntry(targetMenu);
            targetMenu = null; //this allows the player to interact with the client without their clicks being overridden
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        final int keycode = config.toggleKey().getKeyCode();
        log.info(String.valueOf(keycode) + " : " + e.getKeyCode());

        if (e.getKeyCode() == keycode && run)
        {
            //chinBreakHandler.stopPlugin(this);
            log.info("Stopped");
            run = false;
        }
        else if (e.getKeyCode() == keycode && !run)
        {
          //  chinBreakHandler.startPlugin(this);
            log.info("Started");
            shouldCast = true;
            run = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
