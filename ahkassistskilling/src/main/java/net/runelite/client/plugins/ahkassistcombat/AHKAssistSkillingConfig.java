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
package net.runelite.client.plugins.ahkassistcombat;

import java.awt.*;

import net.runelite.client.config.*;

@ConfigGroup("ahkassist")
public interface AHKAssistSkillingConfig extends Config {

    // Animation -------------------------------------------------------------

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


    // MISC- ---------------------------------------------------------------------

    @ConfigTitleSection(
            keyName = "miscTitle",
            name = "Miscellaneous Indicators",
            description = "",
            position = 300
    )
    default Title miscTitle() {
        return new Title();
    }

    @ConfigItem(
            keyName = "displayConnected",
            name = "Logged in",
            description = "Displays an indicator when logged in.",
            position = 301,
            titleSection = "miscTitle"
    )
    default boolean displayConnected() {
        return false;
    }

    @ConfigItem(
            keyName = "displayConnectedConfig",
            name = "Logged in configuration",
            description = "Displays the configuration options.",
            position = 302,
            hidden = true,
            unhide = "displayConnected",
            unhideValue = "true",
            titleSection = "miscTitle"
    )
    default boolean displayConnectedConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "connectedX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 303,
            hidden = true,
            unhide = "displayConnectedConfig",
            unhideValue = "true",
            titleSection = "miscTitle"
    )
    default int connectedX() {
        return 745;
    }

    @ConfigItem(
            keyName = "connectedY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 304,
            hidden = true,
            unhide = "displayConnectedConfig",
            unhideValue = "true",
            titleSection = "miscTitle"
    )
    default int connectedY() {
        return 0;
    }

    @ConfigItem(
            keyName = "connectedWidth",
            name = "Indicator width",
            description = "The indicator width.",
            position = 305,
            hidden = true,
            unhide = "displayConnectedConfig",
            unhideValue = "true",
            titleSection = "miscTitle"
    )
    default int connectedWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "connectedHeight",
            name = "The indicator height",
            description = "The height of the indicator.",
            position = 306,
            hidden = true,
            unhide = "displayConnectedConfig",
            unhideValue = "true",
            titleSection = "miscTitle"
    )
    default int connectedHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "connectedColor",
            name = "Indicator color",
            description = "The indicator color.",
            position = 307,
            hidden = true,
            unhide = "displayConnectedConfig",
            unhideValue = "true",
            titleSection = "miscTitle"
    )
    default Color connectedColor() {
        return Color.GREEN;
    }



    // Interfaces


    @ConfigTitleSection(
            keyName = "interfacesTitle",
            name = "Interfaces",
            description = "",
            position = 400
    )
    default Title interfacesTitle() {
        return new Title();
    }

    @ConfigItem(
            keyName = "bankInterface",
            name = "Bank",
            description = "Displays an indicator if the bank interface is open.",
            position = 401,
            titleSection = "interfacesTitle"
    )
    default boolean displayBank() {
        return false;
    }

    @ConfigItem(
            keyName = "bankInterfaceConfig",
            name = "Bank configuration",
            description = "Displays the configuration options.",
            position = 402,
            hidden = true,
            unhide = "bankInterface",
            unhideValue = "true",
            titleSection = "interfacesTitle"
    )
    default boolean bankInterfaceConfig() {
        return false;
    }

    @ConfigItem(
            keyName = "bankX",
            name = "Indicator X Pos",
            description = "The X position of the indicator.",
            position = 403,
            hidden = true,
            unhide = "bankInterfaceConfig",
            unhideValue = "true",
            titleSection = "interfacesTitle"
    )
    default int bankX() {
        return 740;
    }

    @ConfigItem(
            keyName = "bankY",
            name = "Indicator Y Pos",
            description = "The Y position of the indicator.",
            position = 404,
            hidden = true,
            unhide = "bankInterfaceConfig",
            unhideValue = "true",
            titleSection = "interfacesTitle"
    )
    default int bankY() {
        return 0;
    }

    @ConfigItem(
            keyName = "bankWidth",
            name = "The indicator width",
            description = "The width of the indicator.",
            position = 405,
            hidden = true,
            unhide = "bankInterfaceConfig",
            unhideValue = "true",
            titleSection = "interfacesTitle"
    )
    default int bankWidth() {
        return 5;
    }

    @ConfigItem(
            keyName = "bankHeight",
            name = "The indicator height",
            description = "The height of the indicator.",
            position = 406,
            hidden = true,
            unhide = "bankInterfaceConfig",
            unhideValue = "true",
            titleSection = "interfacesTitle"
    )
    default int bankHeight() {
        return 5;
    }

    @ConfigItem(
            keyName = "bankColor",
            name = "The indicator color",
            description = "The color of the indicator.",
            position = 407,
            hidden = true,
            unhide = "bankInterfaceConfig",
            unhideValue = "true",
            titleSection = "interfacesTitle"
    )
    default Color bankColor() {
        return Color.ORANGE;
    }

    @ConfigItem(
    		keyName = "showLocation",
    		name = "Player location",
    		description = "Outputs the players current location.",
    		position = 500
    )
    default boolean playerLocation() { return false; }
//
    //@ConfigItem(
    //		keyName = "displayGameTicks",
    //		name = "Display game ticks",
    //		description = "Displays the game ticks.",
    //		position = 23
    //)
    //default boolean displayGameTicks() { return false; }
//
    //@ConfigItem(
    //		keyName = "displayInCombat",
    //		name = "Display player in combat",
    //		description = "Displays an indicator if the player is in combat.",
    //		position = 24
    //)
    //default boolean displayInCombat() { return false; }
}
