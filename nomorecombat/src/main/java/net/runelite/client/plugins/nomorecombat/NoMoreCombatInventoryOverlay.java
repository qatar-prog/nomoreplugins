package net.runelite.client.plugins.nomorecombat;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.Arrays;

@Singleton
class NoMoreCombatInventoryOverlay extends WidgetItemOverlay {

	private final ItemManager itemManager;
	private final NoMoreCombatPlugin plugin;
	private final NoMoreCombatConfig config;
	private final Client client;

	// NMZ
	private static final int[] NMZ_MAP_REGION = {9033};

	@Inject
	private NoMoreCombatInventoryOverlay(final ItemManager itemManager, final NoMoreCombatPlugin plugin, final NoMoreCombatConfig config, Client client) {
		this.itemManager = itemManager;
		this.plugin = plugin;
		this.config = config;
		this.client = client;
		showOnEquipment();
		showOnInventory();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget) {
		if (config.displayLowHP() && config.displayFoodMarker()) {
			if (itemId == ItemID.SHRIMPS
					|| itemId == ItemID.COOKED_CHICKEN
					|| itemId == ItemID.COOKED_MEAT
					|| itemId == ItemID.SARDINE
					|| itemId == ItemID.MACKEREL
					|| itemId == ItemID.TROUT
					|| itemId == ItemID.COD
					|| itemId == ItemID.PIKE
					|| itemId == ItemID.SALMON
					|| itemId == ItemID.TUNA
					|| itemId == ItemID.CAKE
					|| itemId == ItemID.LOBSTER
					|| itemId == ItemID.BASS
					|| itemId == ItemID.SWORDFISH
					|| itemId == ItemID.MONKFISH
					|| itemId == ItemID.COOKED_KARAMBWAN
					|| itemId == ItemID.SHARK
					|| itemId == ItemID.MANTA_RAY
					|| itemId == ItemID.DARK_CRAB
					|| itemId == ItemID.ANGLERFISH
					|| itemId == ItemID.SARADOMIN_BREW1
					|| itemId == ItemID.SARADOMIN_BREW2
					|| itemId == ItemID.SARADOMIN_BREW3
					|| itemId == ItemID.SARADOMIN_BREW4) {
				Rectangle bounds = itemWidget.getCanvasBounds();
				int x = (int) bounds.getBounds().getCenterX();
				int y = (int) bounds.getBounds().getCenterY();
				graphics.setColor(config.displayFoodMarkerColor());
				graphics.fillRect(x - (x/2), y -(y/2), 4, 4);
			}
		}

		if (config.displayLowPrayer() && config.displayPrayerMarker()) {
			if (itemId == ItemID.PRAYER_POTION1
					|| itemId == ItemID.PRAYER_POTION2
					|| itemId == ItemID.PRAYER_POTION3
					|| itemId == ItemID.PRAYER_POTION4
					|| itemId == ItemID.PRAYER_MIX1
					|| itemId == ItemID.PRAYER_MIX2) {
				Rectangle bounds = itemWidget.getCanvasBounds();
				int x = (int) bounds.getBounds().getCenterX() - 2;
				int y = (int) bounds.getBounds().getCenterY() - 2;
				graphics.setColor(config.displayPrayerMarkerColor());
				graphics.fillRect(x - (x/2), y -(y/2), 4, 4);
			}
		}

		if (config.displayLowEnergy() && config.energyItemMarker()) {
			if (itemId == ItemID.ENERGY_POTION1
					|| itemId == ItemID.ENERGY_POTION2
					|| itemId == ItemID.ENERGY_POTION3
					|| itemId == ItemID.ENERGY_POTION4
					|| itemId == ItemID.GUTHIX_REST1
					|| itemId == ItemID.GUTHIX_REST2
					|| itemId == ItemID.GUTHIX_REST3
					|| itemId == ItemID.GUTHIX_REST4
					|| itemId == ItemID.PAPAYA_FRUIT
					|| itemId == ItemID.WHITE_TREE_FRUIT
					|| itemId == ItemID.SUPER_ENERGY1
					|| itemId == ItemID.SUPER_ENERGY2
					|| itemId == ItemID.SUPER_ENERGY3
					|| itemId == ItemID.SUPER_ENERGY4
					|| itemId == ItemID.STAMINA_POTION1
					|| itemId == ItemID.STAMINA_POTION2
					|| itemId == ItemID.STAMINA_POTION3
					|| itemId == ItemID.STAMINA_POTION4
					|| itemId == ItemID.SUMMER_PIE
					|| itemId == ItemID.HALF_A_SUMMER_PIE
					|| itemId == ItemID.PART_SUMMER_PIE
					|| itemId == ItemID.PURPLE_SWEETS
					|| itemId == ItemID.STRANGE_FRUIT
					|| itemId == ItemID.MINT_CAKE
					|| itemId == ItemID.GOUT_TUBER
					|| itemId == ItemID.WINTER_SQIRKJUICE
					|| itemId == ItemID.SPRING_SQIRKJUICE
					|| itemId == ItemID.AUTUMN_SQIRKJUICE
					|| itemId == ItemID.SUMMER_SQIRKJUICE
					|| itemId == ItemID.BANDAGES
					|| itemId == ItemID.EXPLORERS_RING_1
					|| itemId == ItemID.EXPLORERS_RING_2
					|| itemId == ItemID.EXPLORERS_RING_3
					|| itemId == ItemID.EXPLORERS_RING_4
					|| itemId == ItemID.AGILITY_CAPE) {
				Rectangle bounds = itemWidget.getCanvasBounds();
				int x = (int) bounds.getBounds().getCenterX() - 2;
				int y = (int) bounds.getBounds().getCenterY() - 2;
				graphics.setColor(config.energyItemMarkerColor());
				graphics.fillRect(x - (x/2), y -(y/2), 4, 4);
			}
		}

		if (isPlayerInNMZ()) {
			//Absorption Potions
			if (config.displayLowAbsorption() && config.tagAbsorptionPotions()) {
				if (itemId == ItemID.ABSORPTION_4
						|| itemId == ItemID.ABSORPTION_3
						|| itemId == ItemID.ABSORPTION_2
						|| itemId == ItemID.ABSORPTION_1) {
					Rectangle bounds = itemWidget.getCanvasBounds();
					int x = (int) bounds.getBounds().getCenterX() - 2;
					int y = (int) bounds.getBounds().getCenterY() - 2;
					graphics.setColor(config.tagAbsorptionPotionColor());
					graphics.fillRect(x - (x/2), y -(y/2), 4, 4);
				}
			}

			if (config.displayLowOverload() && config.tagOverloadPotions()) {
				if (itemId == ItemID.OVERLOAD_4
						|| itemId == ItemID.OVERLOAD_3
						|| itemId == ItemID.OVERLOAD_2
						|| itemId == ItemID.OVERLOAD_1) {
					Rectangle bounds = itemWidget.getCanvasBounds();
					int x = (int) bounds.getBounds().getCenterX() - 2;
					int y = (int) bounds.getBounds().getCenterY() - 2;
					graphics.setColor(config.tagOverloadPotionColor());
					graphics.fillRect(x - (x/2), y -(y/2), 4, 4);
				}
			}

			if (config.displayLowRange() && config.tagRangePotions()) {
				if (itemId == ItemID.SUPER_RANGING_1
						|| itemId == ItemID.SUPER_RANGING_3
						|| itemId == ItemID.SUPER_RANGING_2
						|| itemId == ItemID.SUPER_RANGING_4) {
					Rectangle bounds = itemWidget.getCanvasBounds();
					int x = (int) bounds.getBounds().getCenterX() - 2;
					int y = (int) bounds.getBounds().getCenterY() - 2;
					graphics.setColor(config.tagRangePotionColor());
					graphics.fillRect(x - (x/2), y -(y/2), 4, 4);
				}
			}

			if (config.displayLowMagic() && config.tagMagicPotions()) {
				if (itemId == ItemID.SUPER_MAGIC_POTION_1
						|| itemId == ItemID.SUPER_MAGIC_POTION_2
						|| itemId == ItemID.SUPER_MAGIC_POTION_3
						|| itemId == ItemID.SUPER_MAGIC_POTION_4) {
					Rectangle bounds = itemWidget.getCanvasBounds();
					int x = (int) bounds.getBounds().getCenterX() - 2;
					int y = (int) bounds.getBounds().getCenterY() - 2;
					graphics.setColor(config.tagMagicPotionsColor());
					graphics.fillRect(x - (x/2), y -(y/2), 4, 4);
				}
			}
		}

		if (config.displayCombatPotion()) {
			int max = client.getRealSkillLevel(Skill.ATTACK);
			int boosted = client.getBoostedSkillLevel(Skill.ATTACK);
			int relative = config.combatSkillLevel();
			if (itemId == ItemID.COMBAT_POTION1
					|| itemId == ItemID.COMBAT_POTION2
					|| itemId == ItemID.COMBAT_POTION3
					|| itemId == ItemID.COMBAT_POTION4
					|| itemId == ItemID.SUPER_COMBAT_POTION1
					|| itemId == ItemID.SUPER_COMBAT_POTION2
					|| itemId == ItemID.SUPER_COMBAT_POTION3
					|| itemId == ItemID.SUPER_COMBAT_POTION4
					|| itemId == ItemID.DIVINE_SUPER_COMBAT_POTION1
					|| itemId == ItemID.DIVINE_SUPER_COMBAT_POTION2
					|| itemId == ItemID.DIVINE_SUPER_COMBAT_POTION3
					|| itemId == ItemID.DIVINE_SUPER_COMBAT_POTION4) {
				if (boosted <= (max + relative)) {
					Rectangle bounds = itemWidget.getCanvasBounds();
					int x = (int) bounds.getBounds().getCenterX() - 2;
					int y = (int) bounds.getBounds().getCenterY() - 2;
					graphics.setColor(config.combatColor());
					graphics.fillRect(x - (config.combatSize()/2), y - (config.combatSize()/2), config.combatSize(), config.combatSize());
				}
			}
		}

		if (config.displayRangePotion()) {
			int max = client.getRealSkillLevel(Skill.RANGED);
			int boosted = client.getBoostedSkillLevel(Skill.RANGED);
			int relative = config.rangeSkillLevel();
			if (itemId == ItemID.RANGING_POTION1
					|| itemId == ItemID.RANGING_POTION2
					|| itemId == ItemID.RANGING_POTION3
					|| itemId == ItemID.RANGING_POTION4
					|| itemId == ItemID.DIVINE_RANGING_POTION1
					|| itemId == ItemID.DIVINE_RANGING_POTION2
					|| itemId == ItemID.DIVINE_RANGING_POTION3
					|| itemId == ItemID.DIVINE_RANGING_POTION4) {
				if (boosted <= (max + relative)) {
					Rectangle bounds = itemWidget.getCanvasBounds();
					int x = (int) bounds.getBounds().getCenterX() - 2;
					int y = (int) bounds.getBounds().getCenterY() - 2;
					graphics.setColor(config.rangeColor());
					graphics.fillRect(x - (config.rangeSize()/2), y - (config.rangeSize()/2), config.rangeSize(), config.rangeSize());
				}
			}
		}

		if (config.displayMagePotion()) {
			int max = client.getRealSkillLevel(Skill.RANGED);
			int boosted = client.getBoostedSkillLevel(Skill.RANGED);
			int relative = config.mageSkillLevel();
			if (itemId == ItemID.MAGIC_POTION1
					|| itemId == ItemID.MAGIC_POTION2
					|| itemId == ItemID.MAGIC_POTION3
					|| itemId == ItemID.MAGIC_POTION4
					|| itemId == ItemID.DIVINE_MAGIC_POTION1
					|| itemId == ItemID.DIVINE_MAGIC_POTION2
					|| itemId == ItemID.DIVINE_MAGIC_POTION3
					|| itemId == ItemID.DIVINE_MAGIC_POTION4) {
				if (boosted <= (max + relative)) {
					Rectangle bounds = itemWidget.getCanvasBounds();
					int x = (int) bounds.getBounds().getCenterX() - 2;
					int y = (int) bounds.getBounds().getCenterY() - 2;
					graphics.setColor(config.mageColor());
					graphics.fillRect(x - (config.mageSize()/2), y - (config.mageSize()/2), config.mageSize(), config.mageSize());
				}
			}
		}
	}

	public boolean isPlayerInNMZ() {
		return client.getLocalPlayer().getWorldLocation().getPlane() > 0 && Arrays.equals(client.getMapRegions(), NMZ_MAP_REGION);
	}
}