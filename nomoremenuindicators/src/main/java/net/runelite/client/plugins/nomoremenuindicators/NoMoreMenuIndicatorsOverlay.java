package net.runelite.client.plugins.nomoremenuindicators;

import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class NoMoreMenuIndicatorsOverlay extends Overlay {
    //combat , levels , quest , inventory , equipment , prayer , magic , friends , account , clan , settings , emote , music
    private final Client client;
    private final NoMoreMenuIndicatorsPlugin plugin;
    private final NoMoreMenuIndicatorsConfig config;
/*
    private Widget combat;
    private Widget skills;
    private Widget quest;
    private Widget inventory;
    private Widget equipment;
    private Widget prayer;
    private Widget magic;
    private Widget friends;
    private Widget account;
    private Widget clan;
    private Widget settings;
    private Widget emote;
    private Widget music;*/


    @Inject
    NoMoreMenuIndicatorsOverlay(final Client client, final NoMoreMenuIndicatorsPlugin plugin, final NoMoreMenuIndicatorsConfig config) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        /*this.combat = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_COMBAT_TAB);
        this.skills = client.getWidget(WidgetInfo.SKILLS_CONTAINER);
        this.quest = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_QUESTS_TAB);
        this.inventory = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);
        this.equipment = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_EQUIPMENT_ICON);
        this.prayer = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_PRAYER_TAB);
        this.magic = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MAGIC_TAB);
        this.friends = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_FRIENDS_TAB);
        this.clan = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_CLAN_CHAT_TAB);
        this.settings = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_OPTIONS_TAB);
        this.emote = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_EMOTES_TAB);
        this.music = client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_MUSIC_TAB);*/
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        Player player = client.getLocalPlayer();
        if (player == null) {
            System.out.println("null");
            return null;
        }
        return null;
    }


    public void renderBox(Graphics2D graphics, Rectangle shapeClickBox, Color color, int boxSize) {
        if (shapeClickBox != null) {
            int x = (int) shapeClickBox.getCenterX() - boxSize / 2;
            int y = (int) shapeClickBox.getCenterY() - boxSize / 2;
            graphics.setColor(color);
            graphics.fillRect(x, y, boxSize, boxSize);
        }
    }
}
