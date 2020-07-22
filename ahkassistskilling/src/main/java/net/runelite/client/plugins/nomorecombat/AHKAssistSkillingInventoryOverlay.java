package net.runelite.client.plugins.nomorecombat;

import net.runelite.api.Client;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;

@Singleton
class AHKAssistSkillingInventoryOverlay extends WidgetItemOverlay {

	private final ItemManager itemManager;
	private final AHKAssistSkillingPlugin plugin;
	private final AHKAssistSkillingConfig config;
	private final Client client;

	// NMZ
	private static final int[] NMZ_MAP_REGION = {9033};

	@Inject
	private AHKAssistSkillingInventoryOverlay(final ItemManager itemManager, final AHKAssistSkillingPlugin plugin, final AHKAssistSkillingConfig config, Client client) {
		this.itemManager = itemManager;
		this.plugin = plugin;
		this.config = config;
		this.client = client;
		showOnEquipment();
		showOnInventory();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget) {

	}
}