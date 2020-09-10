package net.runelite.client.plugins.pvphelper;

import com.google.inject.Provides;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.AccessLevel;
import lombok.Getter;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.events.*;
import net.runelite.api.kit.KitType;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import net.runelite.client.plugins.botutils.BotUtils;
import net.runelite.client.plugins.pvphelper.ScriptCommand.ScriptCommand;
import net.runelite.client.ui.overlay.OverlayManager;

import org.pf4j.Extension;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Extension
@Slf4j
@PluginDependency(BotUtils.class)
@PluginDescriptor(
        name = "Ben63riggs PvP Helper",
        description = "",
        tags = {"combat", "player", "enemy", "tracking", "overlay", "op"},
        enabledByDefault = false,
        type = PluginType.PVP
)
public class PvPHelperPlugin extends Plugin {

    private static final Duration WAIT = Duration.ofSeconds(5);
    public Queue<ScriptCommand> commandList = new ConcurrentLinkedQueue<>();
    public Queue<MenuEntry> entryList = new ConcurrentLinkedQueue<>();

    @Inject
    public Client client;

    @Inject
    private PvPHelperConfig config;

    @Inject
    private ConfigManager configManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PvPHelperOverlay pvpHelperOverlay;

    @Inject
    private PvPHelperHotkeyListener pvpHelperHotkeyListener;

    @Inject
    private KeyManager keyManager;

    @Inject
    BotUtils utils;

    @Getter(AccessLevel.PACKAGE)
    public Player lastEnemy;
    private Instant lastTime;
    public long sleepLength;

    PlayerAppearance targetAppearance;

    @Provides
    PvPHelperConfig provideConfig(final ConfigManager configManager) {
        return configManager.getConfig(PvPHelperConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(pvpHelperOverlay);
        keyManager.registerKeyListener(pvpHelperHotkeyListener);
    }

    @Override
    protected void shutDown() throws Exception {
        lastTime = null;
        overlayManager.remove(pvpHelperOverlay);
        keyManager.unregisterKeyListener(pvpHelperHotkeyListener);
    }

    @Subscribe
    public void onInteractingChanged(final InteractingChanged event) {
        if (client.getGameState() != GameState.LOGGED_IN) {
            return;
        }

        if (event.getSource() != client.getLocalPlayer()) {
            return;
        }

        final Actor opponent = event.getTarget();

        if (opponent == null) {
            lastTime = Instant.now();
            return;
        }

        Player localPlayer = client.getLocalPlayer();
        final List<Player> players = client.getPlayers();

        for (final Player player : players) {
            if (player == localPlayer.getInteracting()) {
                lastEnemy = player;
            }
        }
    }



    @Subscribe
    public void onGameTick(GameTick event) {
        if (client.getGameState() != GameState.LOGGED_IN) {
            return;
        }

        lastEnemyTimer();

        doAutoSwapPrayers();

        processCommands();
    }

    private void processCommands()
    {
        while (commandList.peek() != null)
        {
            commandList.poll().execute(client, config, this, pvpHelperOverlay,configManager);
        }
    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (entryList != null && !entryList.isEmpty()) {
            event.setMenuEntry(entryList.poll());
            handleHotkeyTasks();
        }
    }

    public void handleHotkeyTasks()
    {
        if (entryList == null || entryList.isEmpty())
        {
            return;
        }

        click();
    }

    public void lastEnemyTimer() {
        Player localPlayer = client.getLocalPlayer();

        if (localPlayer == null)
        {
            return;
        }

        if (lastEnemy == null)
        {
            return;
        }

        if (localPlayer.getInteracting() == null)
        {
            if (Duration.between(lastTime, Instant.now()).compareTo(PvPHelperPlugin.WAIT) > 0)
            {
                lastEnemy = null;
            }
        }
    }

    public void activatePrayer(WidgetInfo widgetInfo) {
        Widget prayer_widget = client.getWidget(widgetInfo);

        if (prayer_widget == null) {
            return;
        }

        if (client.getBoostedSkillLevel(Skill.PRAYER) <= 0) {
            return;
        }

        entryList.add(new MenuEntry("Activate", prayer_widget.getName(), 1, MenuOpcode.CC_OP.getId(), -1, prayer_widget.getId(), false));
    }

    public void doAutoSwapPrayers() {
        if (!config.autoPrayerSwitcher()) {
            return;
        }

        if (!config.autoPrayerSwitcherEnabled()) {
            return;
        }

        boolean PROTECT_MELEE = client.getVar(Prayer.PROTECT_FROM_MELEE.getVarbit()) != 0;
        boolean PROTECT_RANGED = client.getVar(Prayer.PROTECT_FROM_MISSILES.getVarbit()) != 0;
        boolean PROTECT_MAGIC = client.getVar(Prayer.PROTECT_FROM_MAGIC.getVarbit()) != 0;

        if (client.getBoostedSkillLevel(Skill.PRAYER) <= 0) {
            return;
        }

        if (lastEnemy == null) {
            return;
        }

        targetAppearance = lastEnemy.getPlayerAppearance();

        if (targetAppearance == null)
        {
            return;
        }

        int WEAPON_INT = targetAppearance.getEquipmentId(KitType.WEAPON);

        if (WEAPON_INT <= 0)
        {
            return;
        }

		if ((Arrays.asList(PvPHelperOverlay.MELEE_LIST).contains(WEAPON_INT)) && !PROTECT_MELEE) {
			log.info("Prayer: Melee");
			activatePrayer(WidgetInfo.PRAYER_PROTECT_FROM_MELEE);
		} else if ((Arrays.asList(PvPHelperOverlay.RANGED_LIST).contains(WEAPON_INT)) && !PROTECT_RANGED) {
			log.info("Prayer: Ranged");
			activatePrayer(WidgetInfo.PRAYER_PROTECT_FROM_MISSILES);
		} else if ((Arrays.asList(PvPHelperOverlay.MAGIC_LIST).contains(WEAPON_INT)) && !PROTECT_MAGIC) {
			log.info("Prayer: Magic");
			activatePrayer(WidgetInfo.PRAYER_PROTECT_FROM_MAGIC);
		} else {
			log.info("No matches found or prayer already activated");
		}
    }

    public long sleepDelay() {
        sleepLength = utils.randomDelay(config.sleepWeightedDistribution(), config.sleepMin(), config.sleepMax(), config.sleepDeviation(), config.sleepTarget());
        return sleepLength;
    }

    public void click() {
        Point pos = client.getMouseCanvasPosition();

        if (client.isStretchedEnabled()) {
            final Dimension stretched = client.getStretchedDimensions();
            final Dimension real = client.getRealDimensions();
            final double width = (stretched.width / real.getWidth());
            final double height = (stretched.height / real.getHeight());
            final Point point = new Point((int) (pos.getX() * width), (int) (pos.getY() * height));
            client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 501, System.currentTimeMillis(), 0, point.getX(), point.getY(), 1, false, 1));
            client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 502, System.currentTimeMillis(), 0, point.getX(), point.getY(), 1, false, 1));
            client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 500, System.currentTimeMillis(), 0, point.getX(), point.getY(), 1, false, 1));
            return;
        }

        client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 501, System.currentTimeMillis(), 0, pos.getX(), pos.getY(), 1, false, 1));
        client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 502, System.currentTimeMillis(), 0, pos.getX(), pos.getY(), 1, false, 1));
        client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 500, System.currentTimeMillis(), 0, pos.getX(), pos.getY(), 1, false, 1));
    }
}
