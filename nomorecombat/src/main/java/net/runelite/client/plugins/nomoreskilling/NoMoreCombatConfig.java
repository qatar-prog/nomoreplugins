/*
 * Copyright (c) 2018, Cas <https://github.com/casvandongen>
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
package net.runelite.client.plugins.nomoreskilling;

import java.awt.*;

import net.runelite.client.config.*;

@ConfigGroup("nomorecombat")
public interface NoMoreCombatConfig extends Config {

    // LOW HP

    @ConfigTitleSection(
            keyName = "indicatorTitle",
            name = "Player Indicators",
            description = "",
            position = 1
    )
    default Title indicatorTitle() {
        return new Title();
    }

    @ConfigItem(
            keyName = "displayLowHP",
            name = "Hitpoints",
            description = "Displays an indicator when low on health.",
            position = 2,
            titleSection = "indicatorTitle"
    )
    default boolean displayLowHP() {
        return false;
    }

    @ConfigItem(
            keyName = "displayLowHPConfig",
            name = "HP configuration",
            description = "Displays the configuration options.",
            position = 3,
            hidden = true,
            unhide = "displayLowHP",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean displayLowHPConfig() {
        return false;
    }

    @Range(
            min = 1,
            max = 99
    )
    @ConfigItem(
            keyName = "lowHPLevel",
            name = "HP Level",
            description = "Displays an indicator when below the HP level provided.",
            position = 4,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowHPLevel() {
        return 10;
    }

    @ConfigItem(
            keyName = "lowHPX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 5,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowHPX() {
        return 760;
    }

    @ConfigItem(
            keyName = "lowHPY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 6,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowHPY() {
        return 0;
    }

    @ConfigItem(
            keyName = "lowHPWidth",
            name = "Indicator width",
            description = "The indicator width.",
            position = 7,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowHPWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "lowHPHeight",
            name = "The indicator height",
            description = "The height of the indicator.",
            position = 8,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowHPHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "lowHPColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 9,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default Color lowHPColor() {
        return Color.RED;
    }

    @ConfigItem(
            keyName = "displayFoodMarker",
            name = "Food markers",
            description = "This will display a marker over any items in your inventory which heals would heal you when consumed.",
            position = 10,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean displayFoodMarker() {
        return true;
    }

    @ConfigItem(
            keyName = "displayFoodMarkerColor",
            name = "Food marker color",
            description = "The indicator color for health increasing items.",
            position = 11,
            hidden = true,
            unhide = "displayLowHPConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default Color displayFoodMarkerColor() {
        return Color.RED;
    }

    // Prayer

    //

    //

    //

    //

    //

    @ConfigItem(
            keyName = "displayLowPrayer",
            name = "Prayer",
            description = "Displays an indicator when low on prayer.",
            position = 12,
            titleSection = "indicatorTitle"
    )
    default boolean displayLowPrayer() {
        return false;
    }

    @ConfigItem(
            keyName = "displayLowPrayerConfig",
            name = "Prayer configuration",
            description = "Displays the configuration options.",
            position = 13,
            hidden = true,
            unhide = "displayLowPrayer",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean displayLowPrayerConfig() {
        return false;
    }

    @Range(
            min = 1,
            max = 99
    )
    @ConfigItem(
            keyName = "lowPrayerLevel",
            name = "Prayer Level",
            description = "Displays an indicator when below the prayer level provided.",
            position = 14,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowPrayerLevel() {
        return 1;
    }

    @ConfigItem(
            keyName = "lowPrayerX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 15,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowPrayerX() {
        return 760;
    }

    @ConfigItem(
            keyName = "lowPrayerY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 16,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowPrayerY() {
        return 5;
    }

    @ConfigItem(
            keyName = "lowPrayerWidth",
            name = "Indicator width",
            description = "The indicator width.",
            position = 17,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowPrayerWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "lowPrayerHeight",
            name = "The indicator height",
            description = "The height of the indicator.",
            position = 18,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowPrayerHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "lowPrayerColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 19,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default Color lowPrayerColor() {
        return Color.CYAN;
    }

    @ConfigItem(
            keyName = "displayPrayerItems",
            name = "Prayer item's marker",
            description = "The height of the indicator.",
            position = 20,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean displayPrayerMarker() {
        return true;
    }

    @ConfigItem(
            keyName = "displayPrayerItemsColor",
            name = "Indicator color",
            description = "This will display a marker over any items in your inventory which heals would restore your prayer points when consumed.",
            position = 21,
            hidden = true,
            unhide = "displayLowPrayerConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default Color displayPrayerMarkerColor() {
        return Color.CYAN;
    }

    // Low Run / Energy

    //

    //

    //

    //

    @ConfigItem(
            keyName = "displayLowEnergy",
            name = "Energy / Run",
            description = "Displays an indicator when the player is low on energy / run.",
            position = 22,
            titleSection = "indicatorTitle"
    )
    default boolean displayLowEnergy() {
        return false;
    }

    @ConfigItem(
            keyName = "displayLowEnergyConfig",
            name = "Energy / Run configuration",
            description = "Displays the configuration options.",
            position = 23,
            hidden = true,
            unhide = "displayLowEnergy",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean displayLowEnergyConfig() {
        return false;
    }

    @Range(
            min = 1,
            max = 100
    )
    @ConfigItem(
            keyName = "lowEnergyLevel",
            name = "Energy Level",
            description = "Displays an indicator when below the energy level provided.",
            position = 24,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int lowEnergyLevel() {
        return 1;
    }

    @ConfigItem(
            keyName = "energyX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 25,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int energyX() {
        return 760;
    }

    @ConfigItem(
            keyName = "energyY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 26,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int energyY() {
        return 10;
    }

    @ConfigItem(
            keyName = "energyWidth",
            name = "Indicator width",
            description = "The indicator width.",
            position = 27,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int energyWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "energyHeight",
            name = "The indicator height",
            description = "The height of the indicator.",
            position = 28,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int energyHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "energyColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 29,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default Color energyColor() {
        return Color.ORANGE;
    }

    @ConfigItem(
            keyName = "energyItemMarker",
            name = "Energy item's marker",
            description = "This will display a marker over any items in your inventory which would restore your energy level when consumed.",
            position = 30,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean energyItemMarker() {
        return true;
    }

    @ConfigItem(
            keyName = "energyItemMarkerColor",
            name = "Marker color",
            description = "The indicator color.",
            position = 31,
            hidden = true,
            unhide = "displayLowEnergyConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default Color energyItemMarkerColor() {
        return Color.ORANGE;
    }

    // Special Attack

    //

    //

    //

    //



    @ConfigItem(
            keyName = "displaySpecial",
            name = "Special",
            description = "Displays an indicator when the player's special energy is ready to be used.",
            position = 32,
            titleSection = "indicatorTitle"
    )
    default boolean displaySpecial() {
        return false;
    }

    @ConfigItem(
            keyName = "displaySpecialConfig",
            name = "Special configuration",
            description = "Displays the configuration options.",
            position = 33,
            hidden = true,
            unhide = "displaySpecial",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean displaySpecialConfig() {
        return false;
    }

    @Range(
            min = 1,
            max = 100
    )
    @ConfigItem(
            keyName = "specialLevel",
            name = "Special Level",
            description = "Displays an indicator when the special attack level equals to or is more than the level provided.",
            position = 34,
            hidden = true,
            unhide = "displaySpecialConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int specialLevel() {
        return 50;
    }

    @ConfigItem(
            keyName = "specialX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 35,
            hidden = true,
            unhide = "displaySpecialConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int specialX() {
        return 760;
    }

    @ConfigItem(
            keyName = "specialY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 36,
            hidden = true,
            unhide = "displaySpecialConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int specialY() {
        return 15;
    }

    @ConfigItem(
            keyName = "specialWidth",
            name = "Indicator width",
            description = "The indicator width.",
            position = 37,
            hidden = true,
            unhide = "displaySpecialConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int specialWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "specialHeight",
            name = "The indicator height",
            description = "The height of the indicator.",
            position = 38,
            hidden = true,
            unhide = "displaySpecialConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default int specialHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "specialColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 39,
            hidden = true,
            unhide = "displaySpecialConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default Color specialColor() {
        return Color.YELLOW;
    }

    @ConfigItem(
            keyName = "specialPressed",
            name = "Special selected",
            description = "Displays an indicator if the special attack button / orb is selected.",
            position = 40,
            hidden = true,
            unhide = "displaySpecialConfig",
            unhideValue = "true",
            titleSection = "indicatorTitle"
    )
    default boolean specialPressed() {
        return true;
    }

    // NMZ

    //

    //

    //

    //

    @ConfigTitleSection(
            keyName = "nmzTitle",
            name = "NMZ Indicators",
            description = "",
            position = 100
    )
    default Title nmzTitle() {
        return new Title();
    }

    @ConfigItem(
            keyName = "displayInNMZ",
            name = "Inside NMZ",
            description = "Displays an indicator when the player is inside the NMZ.",
            position = 101,
            titleSection = "nmzTitle"
    )
    default boolean displayInNMZ() {
        return false;
    }

    @ConfigItem(
            keyName = "displayInNMZConfig",
            name = "Inside NMZ configuration",
            description = "Displays the configuration options.",
            position = 102,
            hidden = true,
            unhide = "displayInNMZ",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean displayInNMZConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "inNMZX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 103,
            hidden = true,
            unhide = "displayInNMZConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int inNMZX() {
        return 755;
    }

    @ConfigItem(
            keyName = "inNMZY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 104,
            hidden = true,
            unhide = "displayInNMZConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int inNMZY() {
        return 0;
    }

    @ConfigItem(
            keyName = "inNMZWidth",
            name = "Indicator width",
            description = "The width position of the indicator.",
            position = 105,
            hidden = true,
            unhide = "displayInNMZConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int inNMZWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "inNMZHeight",
            name = "Indicator height",
            description = "The height position of the indicator.",
            position = 106,
            hidden = true,
            unhide = "displayInNMZConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int inNMZHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "inNMZColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 107,
            hidden = true,
            unhide = "displayInNMZConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color inNMZColor() {
        return Color.MAGENTA;
    }

    // ABSORPTION

    //

    //

    //

    //

    @ConfigItem(
            keyName = "displayLowAbsorption",
            name = "Absorption",
            description = "Displays an indicator when the player is low on absorption points.",
            position = 108,
            titleSection = "nmzTitle"
    )
    default boolean displayLowAbsorption() {
        return false;
    }

    @ConfigItem(
            keyName = "displayLowAbsorptionConfig",
            name = "Absorption configuration",
            description = "Displays the configuration options.",
            position = 109,
            hidden = true,
            unhide = "displayLowAbsorption",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean displayLowAbsorptionConfig() {
        return false;
    }

    @Range(
            min = 1,
            max = 1000
    )
    @ConfigItem(
            keyName = "lowAbsorptionLevel",
            name = "Absorption Level",
            description = "Displays an indicator when below the absorption level provided.",
            position = 110,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int lowAbsorptionLevel() {
        return 50;
    }

    @ConfigItem(
            keyName = "absorptionX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 111,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int absorptionX() {
        return 755;
    }

    @ConfigItem(
            keyName = "absorptionY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 112,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int absorptionY() {
        return 5;
    }

    @ConfigItem(
            keyName = "absorptionWidth",
            name = "Indicator width",
            description = "The width position of the indicator.",
            position = 113,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int absorptionWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "absorptionHeight",
            name = "The indicator height",
            description = "The height position of the indicator.",
            position = 114,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int absorptionHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "absorptionColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 115,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color absorptionColor() {
        return new Color(187, 196, 199);
    }

    @ConfigItem(
            keyName = "tagAbsorptionPotions",
            name = "Absorption marker",
            description = "Marks the absorption potions in your inventory.",
            position = 116,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean tagAbsorptionPotions() {
        return true;
    }

    @ConfigItem(
            keyName = "tagAbsorptionPotionColor",
            name = "Marker color",
            description = "The color of the marker displayed over the absorption potions.",
            position = 117,
            hidden = true,
            unhide = "displayLowAbsorptionConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color tagAbsorptionPotionColor() {
        return new Color(187, 196, 199);
    }

    // overload

    //

    //

    //

    //

    @ConfigItem(
            keyName = "displayLowOverload",
            name = "Overloads",
            description = "Displays an indicator when the player should drink an overload potion.",
            position = 118,
            titleSection = "nmzTitle"
    )
    default boolean displayLowOverload() {
        return false;
    }

    @ConfigItem(
            keyName = "displayLowOverloadConfig",
            name = "Overloads configuration",
            description = "Displays the configuration options.",
            position = 119,
            hidden = true,
            unhide = "displayLowOverload",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean displayLowOverloadConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "overloadX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 120,
            hidden = true,
            unhide = "displayLowOverloadConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int overloadX() {
        return 755;
    }

    @ConfigItem(
            keyName = "overloadY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 121,
            hidden = true,
            unhide = "displayLowOverloadConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int overloadY() {
        return 10;
    }

    @ConfigItem(
            keyName = "overloadWidth",
            name = "Indicator width",
            description = "The width position of the indicator.",
            position = 122,
            hidden = true,
            unhide = "displayLowOverloadConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int overloadWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "overloadHeight",
            name = "Indicator height",
            description = "The height position of the indicator.",
            position = 123,
            hidden = true,
            unhide = "displayLowOverloadConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int overloadHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "overloadColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 124,
            hidden = true,
            unhide = "displayLowOverloadConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color overloadColor() {
        return Color.BLACK;
    }

    @ConfigItem(
            keyName = "tagOverloadPotions",
            name = "Overload potion marker",
            description = "Marks the overload potions in your inventory.",
            position = 125,
            hidden = true,
            unhide = "displayLowOverloadConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean tagOverloadPotions() {
        return true;
    }

    @ConfigItem(
            keyName = "tagOverloadPotionColor",
            name = "Marker color",
            description = "The color of the marker displayed over the overload potions.",
            position = 126,
            hidden = true,
            unhide = "displayLowOverloadConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color tagOverloadPotionColor() {
        return Color.BLACK;
    }

    // NMZ Ranging Level

    //

    //

    //

    //

    @ConfigItem(
            keyName = "displayLowRange",
            name = "Super ranging potion",
            description = "Displays an indicator when the player's ranging level is at or lower the defined ranging level.",
            position = 127,
            titleSection = "nmzTitle"
    )
    default boolean displayLowRange() {
        return false;
    }

    @ConfigItem(
            keyName = "displayLowRangeConfig",
            name = "Super ranging configuration",
            description = "Displays the configuration options.",
            position = 128,
            hidden = true,
            unhide = "displayLowRange",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean displayLowRangeConfig() {
        return false;
    }

    @Range(
            min = 1,
            max = 118
    )
    @ConfigItem(
            keyName = "lowRangeLevel",
            name = "Range Level",
            description = "Displays an indicator when below the ranging level provided.",
            position = 129,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int lowRangeLevel() {
        return 1;
    }

    @ConfigItem(
            keyName = "rangeX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 130,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int rangeX() {
        return 755;
    }

    @ConfigItem(
            keyName = "RangeY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 131,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int rangeY() {
        return 15;
    }

    @ConfigItem(
            keyName = "rangeWidth",
            name = "Indicator width",
            description = "The width position of the indicator.",
            position = 132,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int rangeWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "rangeHeight",
            name = "Indicator height",
            description = "The height position of the indicator.",
            position = 133,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int rangeHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "rangeNMZColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 134,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color rangeNMZColor() {
        return Color.GREEN;
    }

    @ConfigItem(
            keyName = "tagRangePotions",
            name = "Super ranging potion marker",
            description = "Marks the super ranging potions in your inventory.",
            position = 135,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean tagRangePotions() {
        return true;
    }

    @ConfigItem(
            keyName = "tagRangePotionColor",
            name = "Marker color",
            description = "The color of the marker displayed over the super ranging potions.",
            position = 136,
            hidden = true,
            unhide = "displayLowRangeConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color tagRangePotionColor() {
        return Color.GREEN;
    }

    // NMZ Magic

    //

    //

    //

    //

    @ConfigItem(
            keyName = "displayLowMagic",
            name = "Super magic potion",
            description = "Displays an indicator when the player's magic level is at or lower the defined magic level.",
            position = 137,
            titleSection = "nmzTitle"
    )
    default boolean displayLowMagic() {
        return false;
    }

    @ConfigItem(
            keyName = "displayLowMagicConfig",
            name = "Super magic configuration",
            description = "Displays the configuration options.",
            position = 138,
            hidden = true,
            unhide = "displayLowMagic",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean displayLowMagicConfig() {
        return false;
    }

    @Range(
            min = 1,
            max = 118
    )
    @ConfigItem(
            keyName = "lowMagicLevel",
            name = "Magic Level",
            description = "Displays an indicator when below the magic level provided.",
            position = 139,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int lowMagicLevel() {
        return 1;
    }

    @ConfigItem(
            keyName = "magicX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 140,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int magicX() {
        return 755;
    }

    @ConfigItem(
            keyName = "magicY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 141,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int magicY() {
        return 20;
    }

    @ConfigItem(
            keyName = "magicWidth",
            name = "Indicator width",
            description = "The width position of the indicator.",
            position = 142,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int magicWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "magicHeight",
            name = "Indicator height",
            description = "The height position of the indicator.",
            position = 143,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default int magicHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "magicColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 144,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color magicColor() {
        return Color.CYAN;
    }

    @ConfigItem(
            keyName = "tagMagicPotions",
            name = "Super magic potions marker",
            description = "Marks the super magic potions in your inventory.",
            position = 145,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default boolean tagMagicPotions() {
        return true;
    }

    @ConfigItem(
            keyName = "tagMagicPotionsColor",
            name = "Marker color",
            description = "The indicator color.",
            position = 146,
            hidden = true,
            unhide = "displayLowMagicConfig",
            unhideValue = "true",
            titleSection = "nmzTitle"
    )
    default Color tagMagicPotionsColor() {
        return Color.CYAN;
    }

    // Animation

    //

    //

    //

    //

    @ConfigTitleSection(
            keyName = "animationTitle",
            name = "Animation Indicators",
            description = "",
            position = 200
    )
    default Title animationTitle() {
        return new Title();
    }

    // IDLE

    @ConfigItem(
            keyName = "displayIdle",
            name = "Idle animation",
            description = "Displays an indicator when idle.",
            position = 201,
            titleSection = "animationTitle"
    )
    default boolean displayIdle() {
        return false;
    }

    @ConfigItem(
            keyName = "displayIdleConfig",
            name = "Idle configuration",
            description = "Displays the configuration options.",
            position = 202,
            hidden = true,
            unhide = "displayIdle",
            unhideValue = "true",
            titleSection = "animationTitle"
    )
    default boolean displayIdleConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "idleDelay",
            name = "Idle Indication Delay",
            description = "The indicator delay after the player is idle.",
            position = 203,
            hidden = true,
            unhide = "displayIdleConfig",
            unhideValue = "true",
            titleSection = "animationTitle"
    )
    @Units(Units.MILLISECONDS)
    default int idleDelay() { return 5000; }

    @ConfigItem(
            keyName = "idleX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 204,
            hidden = true,
            unhide = "displayIdleConfig",
            unhideValue = "true",
            titleSection = "animationTitle"
    )
    default int idleX() {
        return 750;
    }

    @ConfigItem(
            keyName = "idleY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 205,
            hidden = true,
            unhide = "displayIdleConfig",
            unhideValue = "true",
            titleSection = "animationTitle"
    )
    default int idleY() {
        return 0;
    }

    @ConfigItem(
            keyName = "idleWidth",
            name = "Indicator width",
            description = "The indicator width.",
            position = 206,
            hidden = true,
            unhide = "displayIdleConfig",
            unhideValue = "true",
            titleSection = "animationTitle"
    )
    default int idleWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "idleHeight",
            name = "The indicator height",
            description = "The height of the indicator.",
            position = 207,
            hidden = true,
            unhide = "displayIdleConfig",
            unhideValue = "true",
            titleSection = "animationTitle"
    )
    default int idleHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "idleColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 208,
            hidden = true,
            unhide = "displayIdleConfig",
            unhideValue = "true",
            titleSection = "animationTitle"
    )
    default Color idleColor() {
        return Color.RED;
    }

    // Skilling boosts

    @ConfigTitleSection(
            keyName = "skillingIndicators",
            name = "Potion Boost Indicators",
            description = "",
            position = 300
    )
    default Title skillingIndicators() {
        return new Title();
    }

    // Combat

    @ConfigItem(
            keyName = "displayCombatPotion",
            name = "Combat potions",
            description = "Displays an indicator over a combat potion.",
            position = 301,
            titleSection = "skillingIndicators"
    )
    default boolean displayCombatPotion() {
        return false;
    }

    @ConfigItem(
            keyName = "displayCombatPotionConfig",
            name = "Combat potion configuration",
            description = "Displays the configuration options.",
            position = 302,
            hidden = true,
            unhide = "displayCombatPotion",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default boolean displayCombatPotionConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "combatSkillLevel",
            name = "Relative level",
            description = "If you're Attack level is 99, 3 would mea at 102, an indicator will display.",
            position = 303,
            hidden = true,
            unhide = "displayCombatPotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default int combatSkillLevel() {
        return 5;
    }

    @ConfigItem(
            keyName = "combatSize",
            name = "The indicator size",
            description = "The size of the indicator.",
            position = 304,
            hidden = true,
            unhide = "displayCombatPotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default int combatSize() {
        return 5;
    }

    @ConfigItem(
            keyName = "combatColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 305,
            hidden = true,
            unhide = "displayCombatPotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default Color combatColor() {
        return Color.GREEN;
    }

    // Range

    @ConfigItem(
            keyName = "displayRangePotion",
            name = "Range potions",
            description = "Displays an indicator over a Range potion.",
            position = 306,
            titleSection = "skillingIndicators"
    )
    default boolean displayRangePotion() {
        return false;
    }

    @ConfigItem(
            keyName = "displayRangePotionConfig",
            name = "Range potion configuration",
            description = "Displays the configuration options.",
            position = 307,
            hidden = true,
            unhide = "displayRangePotion",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default boolean displayRangePotionConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "rangeSkillLevel",
            name = "Relative level",
            description = "If you're Range level is 99, 3 would mea at 102, an indicator will display.",
            position = 308,
            hidden = true,
            unhide = "displayRangePotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default int rangeSkillLevel() {
        return 5;
    }

    @ConfigItem(
            keyName = "rangeSize",
            name = "The indicator size",
            description = "The size of the indicator.",
            position = 309,
            hidden = true,
            unhide = "displayRangePotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default int rangeSize() {
        return 5;
    }

    @ConfigItem(
            keyName = "rangeColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 310,
            hidden = true,
            unhide = "displayRangePotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default Color rangeColor() {
        return Color.CYAN;
    }

    // Mage

    @ConfigItem(
            keyName = "displayMagePotion",
            name = "Mage potions",
            description = "Displays an indicator over a Mage potion.",
            position = 311,
            titleSection = "skillingIndicators"
    )
    default boolean displayMagePotion() {
        return false;
    }

    @ConfigItem(
            keyName = "displayMagePotionConfig",
            name = "Mage potion configuration",
            description = "Displays the configuration options.",
            position = 312,
            hidden = true,
            unhide = "displayMagePotion",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default boolean displayMagePotionConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "mageSkillLevel",
            name = "Relative level",
            description = "If you're Mage level is 99, 3 would mea at 102, an indicator will display.",
            position = 313,
            hidden = true,
            unhide = "displayMagePotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default int mageSkillLevel() {
        return 5;
    }

    @ConfigItem(
            keyName = "mageSize",
            name = "The indicator size",
            description = "The size of the indicator.",
            position = 314,
            hidden = true,
            unhide = "displayMagePotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default int mageSize() {
        return 5;
    }

    @ConfigItem(
            keyName = "mageColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 315,
            hidden = true,
            unhide = "displayMagePotionConfig",
            unhideValue = "true",
            titleSection = "skillingIndicators"
    )
    default Color mageColor() {
        return Color.MAGENTA;
    }
}
