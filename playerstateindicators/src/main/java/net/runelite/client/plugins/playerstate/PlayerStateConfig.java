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
package net.runelite.client.plugins.playerstate;

import java.awt.*;

import net.runelite.client.config.*;

@ConfigGroup("playerstateindicators")
public interface PlayerStateConfig extends Config {

    @ConfigTitleSection(
            keyName = "firstTitle",
            name = "Player Indicators",
            description = "",
            position = 1
    )
    default Title firstTitle() {
        return new Title();
    }

    @ConfigItem(
            keyName = "playerIndicatorsEnum",
            name = "Display options for",
            description = "Drop down menu to display configuration options.",
            position = 2,
            titleSection = "firstTitle"
    )
    default PlayerIndicatorsEnum playerIndicatorsEnum() {
        return PlayerIndicatorsEnum.HITPOINTS;
    }

    @ConfigItem(
            keyName = "displayLowHP",
            name = "Hitpoints",
            description = "Displays an indicator when low on health.",
            position = 3,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "HITPOINTS",
            titleSection = "firstTitle"
    )
    default boolean displayLowHP() {
        return false;
    }

    @ConfigItem(
            keyName = "hpLevel",
            name = "HP level",
            description = "Displays an indicator when the HP level is below a certain value.",
            position = 4,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "HITPOINTS",
            titleSection = "firstTitle"
    )
    default int lowHPLevel() { return 10; }

    @ConfigItem(
            keyName = "hpLocation",
            name = "Indicator location",
            description = "Indicator location, format to use: x.y.width.height e.g 10.10.20.20",
            position = 5,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "HITPOINTS",
            titleSection = "firstTitle"
    )
    default String hpLocation() { return "50.0.5.5"; }

    @ConfigItem(
            keyName = "hpColor",
            name = "Indicator color",
            description = "Indicator color",
            position = 6,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "HITPOINTS",
            titleSection = "firstTitle"
    )
    default Color hpColor() { return Color.RED; }

    @ConfigItem(
            keyName = "displayLowPrayer",
            name = "Prayer",
            description = "Displays an indicator when low on prayer points.",
            position = 7,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "PRAYER",
            titleSection = "firstTitle"
    )
    default boolean displayLowPrayer() {
        return false;
    }

    @ConfigItem(
            keyName = "prayerLevel",
            name = "Prayer level",
            description = "Displays an indicator when the Prayer level is below a certain value.",
            position = 8,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "PRAYER",
            titleSection = "firstTitle"
    )
    default int lowPrayerLevel() { return 10; }

    @ConfigItem(
            keyName = "prayerLocation",
            name = "Indicator location",
            description = "Indicator location, format to use: x.y.width.height e.g 10.10.20.20",
            position = 9,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "PRAYER",
            titleSection = "firstTitle"
    )
    default String prayerLocation() { return "55.0.5.5"; }

    @ConfigItem(
            keyName = "prayerColor",
            name = "Indicator color",
            description = "Indicator color",
            position = 10,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "PRAYER",
            titleSection = "firstTitle"
    )
    default Color prayerColor() { return Color.CYAN; }

    @ConfigItem(
            keyName = "displayLowEnergy",
            name = "Energy",
            description = "Displays an indicator when low on run energy.",
            position = 11,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "ENERGY",
            titleSection = "firstTitle"
    )
    default boolean displayLowEnergy() { return false; }

    @ConfigItem(
            keyName = "energyLevel",
            name = "Energy level",
            description = "Displays an indicator when the run energy level is below a certain value.",
            position = 12,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "ENERGY",
            titleSection = "firstTitle"
    )
    default int lowEnergyLevel() { return 10; }

    @ConfigItem(
            keyName = "energyLocation",
            name = "Indicator location",
            description = "Indicator location, format to use: x.y.width.height e.g 10.10.20.20",
            position = 13,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "ENERGY",
            titleSection = "firstTitle"
    )
    default String energyLocation() { return "60.0.5.5"; }

    @ConfigItem(
            keyName = "energyColor",
            name = "Indicator color",
            description = "Indicator color",
            position = 14,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "ENERGY",
            titleSection = "firstTitle"
    )
    default Color energyColor() { return Color.ORANGE; }

    @ConfigItem(
            keyName = "displayLowSpecial",
            name = "Special",
            description = "Displays an indicator when low on special attack energy.",
            position = 15,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "SPECIAL",
            titleSection = "firstTitle"
    )
    default boolean displayLowSpecial() { return false; }

    @ConfigItem(
            keyName = "lowSpecialLevel",
            name = "Special level",
            description = "Displays an indicator when the special attack level is below a certain value.",
            position = 16,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "SPECIAL",
            titleSection = "firstTitle"
    )
    default int lowSpecialLevel() { return 10; }

    @ConfigItem(
            keyName = "specialLocation",
            name = "Indicator location",
            description = "Indicator location, format to use: x.y.width.height e.g 10.10.20.20",
            position = 17,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "SPECIAL",
            titleSection = "firstTitle"
    )
    default String specialLocation() { return "65.0.5.5"; }

    @ConfigItem(
            keyName = "specialColor",
            name = "Indicator color",
            description = "Indicator color",
            position = 18,
            hidden = true,
            unhide = "playerIndicatorsEnum",
            unhideValue = "SPECIAL",
            titleSection = "firstTitle"
    )
    default Color specialColor() { return Color.YELLOW; }

    @ConfigTitleSection(
            keyName = "secondTitle",
            name = "Inventory Indicators",
            description = "",
            position = 19
    )
    default Title secondTitle() {
        return new Title();
    }

    @ConfigItem(
            keyName = "inventoryIndicatorsEnum",
            name = "Inventory Indicators",
            description = "",
            position = 20,
            titleSection = "secondTitle"
    )
    default InventoryIndicatorsEnum inventoryIndicatorsEnum() {
        return InventoryIndicatorsEnum.FULL;
    }

    @ConfigItem(
            keyName = "displayFullInventory",
            name = "Full Inventory",
            description = "Displays an indicator when the inventory is full of items.",
            position = 21,
            hidden = true,
            unhide = "inventoryIndicatorsEnum",
            unhideValue = "FULL",
            titleSection = "secondTitle"
    )
    default boolean displayFullInventory() { return false; }

    @ConfigItem(
            keyName = "fullInventoryLocation",
            name = "Indicator location",
            description = "Indicator location, format to use: x.y.width.height e.g 10.10.20.20",
            position = 22,
            hidden = true,
            unhide = "inventoryIndicatorsEnum",
            unhideValue = "FULL",
            titleSection = "secondTitle"
    )
    default String fullInventoryLocation() { return "70.0.5.5"; }

    @ConfigItem(
            keyName = "fullInventoryColor",
            name = "Indicator color",
            description = "Indicator color",
            position = 23,
            hidden = true,
            unhide = "inventoryIndicatorsEnum",
            unhideValue = "FULL",
            titleSection = "secondTitle"
    )
    default Color fullInventoryColor() { return Color.RED; }
}
