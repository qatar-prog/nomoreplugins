package net.runelite.client.plugins.nomoremenuindicators;

import net.runelite.client.config.*;

import java.awt.Color;


@ConfigGroup("nomoremenuindicators")
public interface NoMoreMenuIndicatorsConfig extends Config
{
	@ConfigTitleSection(
			keyName = "menu",
			name = "Menu tabs",
			description = "",
			position = 1
	)
	default Title nomoreMenu()
	{
		return new Title();
	}

	@ConfigItem(
			position = 2,
			keyName = "boxSize",
			name = "Box Size",
			description = "Set Box Size",
			titleSection = "menu"
	)
	default int boxSize() { return 0; }
 	/*
	@ConfigItem(
			position = 3,
			keyName = "combat",
			name = "Combat",
			description = "Color",
			titleSection = "menus"
	)
	default Color combatColor() { return Color.CYAN; }

	@ConfigItem(
			position = 4,
			keyName = "skills",
			name = "Skills",
			description = "Color",
			titleSection = "skills"
	)
	default Color skillsColor() { return Color.CYAN; }

	@ConfigItem(
			position = 5,
			keyName = "quest",
			name = "Quests",
			description = "Color",
			titleSection = "quest"
	)
	default Color questColor() { return Color.CYAN; }

	@ConfigItem(
			position = 6,
			keyName = "inventory",
			name = "Inventory",
			description = "Color",
			titleSection = "inventory"
	)
	default Color inventoryColor() { return Color.CYAN; }

	@ConfigItem(
			position = 7,
			keyName = "equipment",
			name = "Equipment",
			description = "Color",
			titleSection = "equipment"
	)
	default Color equipmentColor() { return Color.CYAN; }

	@ConfigItem(
			position = 8,
			keyName = "prayer",
			name = "Prayer",
			description = "Color",
			titleSection = "prayer"
	)
	default Color prayerColor() { return Color.CYAN; }

	@ConfigItem(
			position = 9,
			keyName = "magic",
			name = "Magic",
			description = "Color",
			titleSection = "magic"
	)
	default Color magicColor() { return Color.CYAN; }

	@ConfigItem(
			position = 10,
			keyName = "friends",
			name = "Friends",
			description = "Color",
			titleSection = "friends"
	)
	default Color friendsColor() { return Color.CYAN; }

	@ConfigItem(
			position = 11,
			keyName = "account",
			name = "Account",
			description = "Color",
			titleSection = "account"
	)
	default Color accountColor() { return Color.CYAN; }

	@ConfigItem(
			position = 12,
			keyName = "clan",
			name = "Clan",
			description = "Color",
			titleSection = "clan"
	)
	default Color clanColor() { return Color.CYAN; }

	@ConfigItem(
			position = 13,
			keyName = "settings",
			name = "Settings",
			description = "Color",
			titleSection = "settings"
	)
	default Color settingsColor() { return Color.CYAN; }
	@ConfigItem(
			position = 14,
			keyName = "emote",
			name = "Emote",
			description = "Color",
			titleSection = "emote"
	)
	default Color emoteColor() { return Color.CYAN; }

	@ConfigItem(
			position = 15,
			keyName = "music",
			name = "Music",
			description = "Color",
			titleSection = "music"
	)
	default Color musicColor() { return Color.CYAN; }*/
}