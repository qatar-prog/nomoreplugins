package net.runelite.client.plugins.pvphelper;

import net.runelite.client.config.*;

import java.awt.event.KeyEvent;

@ConfigGroup("pvphelper")
public interface PvPHelperConfig extends Config
{

	String commands = "protectitem, rigour, augury, piety, " +
		"incrediblereflexes, ultimatestrength, steelskin, eagleeye, mysticmight, " +
		"freeze, vengeance, teleblock, entangle, " +
		"spec, doublespec, wait, clickenemy, " +
		"group1, group2, group3, group4";

	@ConfigItem(
		keyName = "label1",
		name = "Hotkeys",
		description = "",
		position = 1
	)
	default Title label1()
	{
		return new Title();
	}

	@ConfigItem(
		keyName = "key1",
		name = "Hotkey 1",
		description = "Activates script for this key.",
		position = 2
	)
	default Keybind key1()
	{
		return new Keybind(KeyEvent.VK_1, 0);
	}

	@ConfigItem(
		keyName = "key2",
		name = "Hotkey 2",
		description = "Activates script for this key.",
		position = 3
	)
	default Keybind key2()
	{
		return new Keybind(KeyEvent.VK_2, 0);
	}

	@ConfigItem(
		keyName = "key3",
		name = "Hotkey 3",
		description = "Activates script for this key.",
		position = 4
	)
	default Keybind key3()
	{
		return new Keybind(KeyEvent.VK_3, 0);
	}

	@ConfigItem(
		keyName = "key4",
		name = "Hotkey 4",
		description = "Activates script for this key.",
		position = 5
	)
	default Keybind key4()
	{
		return new Keybind(KeyEvent.VK_4, 0);
	}

	@ConfigItem(
		keyName = "key5",
		name = "Hotkey 5",
		description = "Activates script for this key.",
		position = 6
	)
	default Keybind key5()
	{
		return new Keybind(KeyEvent.VK_5, 0);
	}

	@ConfigItem(
			keyName = "key6",
			name = "Hotkey 6",
			description = "Activates script for this key.",
			position = 6
	)
	default Keybind key6()
	{
		return new Keybind(KeyEvent.VK_6, 0);
	}

	@ConfigItem(
			keyName = "key7",
			name = "Hotkey 7",
			description = "Activates script for this key.",
			position = 6
	)
	default Keybind key7()
	{
		return new Keybind(KeyEvent.VK_7, 0);
	}

	@ConfigItem(
			keyName = "key8",
			name = "Hotkey 8",
			description = "Activates script for this key.",
			position = 6
	)
	default Keybind key8()
	{
		return new Keybind(KeyEvent.VK_8, 0);
	}

	@ConfigItem(
		position = 7,
		keyName = "key1_script",
		name = "Key 1 Script",
		description = PvPHelperConfig.commands
	)
	default String key1_script()
	{
		return "group1\nwait\npiety";
	}

	@ConfigItem(
		position = 8,
		keyName = "key2_script",
		name = "Key 2 Script",
		description = PvPHelperConfig.commands
	)
	default String key2_script()
	{
		return "group2\nwait\nrigour";
	}

	@ConfigItem(
		position = 9,
		keyName = "key3_script",
		name = "Key 3 Script",
		description = PvPHelperConfig.commands
	)
	default String key3_script()
	{
		return "group3\nwait\naugury";
	}

	@ConfigItem(
		position = 10,
		keyName = "key4_script",
		name = "Key 4 Script",
		description = PvPHelperConfig.commands
	)
	default String key4_script()
	{
		return "group4\npiety\nspec\nclickenemy";
	}

	@ConfigItem(
		position = 11,
		keyName = "key5_script",
		name = "Key 5 Script",
		description = PvPHelperConfig.commands
	)
	default String key5_script()
	{
		return "freeze\nclickenemy";
	}

	@ConfigItem(
			position = 11,
			keyName = "key6_script",
			name = "Key 6 Script",
			description = PvPHelperConfig.commands
	)
	default String key6_script()
	{
		return "protectfrommelee";
	}


	@ConfigItem(
			position = 11,
			keyName = "key7_script",
			name = "Key 7 Script",
			description = PvPHelperConfig.commands
	)
	default String key7_script()
	{
		return "protectfrommissiles";
	}

	@ConfigItem(
			position = 11,
			keyName = "key8_script",
			name = "Key 8 Script",
			description = PvPHelperConfig.commands
	)
	default String key8_script()
	{
		return "protectfrommagic";
	}

	@ConfigItem(
		keyName = "label2",
		name = "Prayer",
		description = "",
		position = 12
	)
	default Title label2()
	{
		return new Title();
	}

	@ConfigItem(
		position = 13,
		keyName = "autoPrayerSwitcher",
		name = "Auto Prayer Switcher",
		description = "Automatic Prayer Switching"
	)
	default boolean autoPrayerSwitcher()
	{
		return false;
	}

	@ConfigItem(
		position = 14,
		keyName = "autoPrayerSwitcherHotkey",
		name = "Prayer Switch Toggle Hotkey",
		description = "Hotkey to toggle the prayer switcher on/off"
	)
	default Keybind prayerKey()
	{
		return new Keybind(KeyEvent.VK_6, 0);
	}

	@ConfigItem(
		position = 15,
		keyName = "autoPrayerSwitcherEnabled",
		name = "Prayer Switcher Enabled",
		description = "Enable Prayer Switcher?"
	)
	default boolean autoPrayerSwitcherEnabled()
	{
		return false;
	}

	@ConfigItem(
		position = 16,
		keyName = "prayerHelper",
		name = "Prayer Helper",
		description = "Draws icons to suggest proper prayer switches"
	)
	default boolean prayerHelper()
	{
		return true;
	}

	@ConfigItem(
			keyName = "sleepMin",
			name = "Sleep Min",
			description = "sleep min",
			position = 4,
			section = "delayConfig"
	)
	default int sleepMin()
	{
		return 60;
	}

	@Range(
			min = 0,
			max = 2000
	)
	@ConfigItem(
			keyName = "sleepMax",
			name = "Sleep Max",
			description = "",
			position = 5,
			section = "delayConfig"
	)
	default int sleepMax()
	{
		return 350;
	}

	@Range(
			min = 0,
			max = 2000
	)
	@ConfigItem(
			keyName = "sleepTarget",
			name = "Sleep Target",
			description = "",
			position = 6,
			section = "delayConfig"
	)
	default int sleepTarget()
	{
		return 100;
	}

	@Range(
			min = 0,
			max = 2000
	)
	@ConfigItem(
			keyName = "sleepDeviation",
			name = "Sleep Deviation",
			description = "",
			position = 7,
			section = "delayConfig"
	)
	default int sleepDeviation()
	{
		return 10;
	}

	@ConfigItem(
			keyName = "sleepWeightedDistribution",
			name = "Sleep Weighted Distribution",
			description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution",
			position = 8,
			section = "delayConfig"
	)
	default boolean sleepWeightedDistribution()
	{
		return false;
	}
}
