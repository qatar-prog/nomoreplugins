/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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

import net.runelite.client.config.*;

import java.awt.event.KeyEvent;


@ConfigGroup("cursealch")
public interface CurseAlchConfig extends Config
{
	@ConfigTitleSection(
			keyName = "firstTitle",
			name = "Config",
			description = "",
			position = 1
	)
	default Title firstTitle()
	{
		return new Title();
	}

	@ConfigItem(
			keyName = "toggleKey",
			name = "Toggle Key",
			description = "Key to toggle on / off",
			position = 3
	)
	default Keybind toggleKey()
	{
		return new Keybind(KeyEvent.VK_BACK_SLASH, 0);
	}

	@ConfigItem(
			keyName = "type",
			name = "Curse",
			description = "",
			position = 4
	)
	default CurseAlchType type()
	{
		return CurseAlchType.CURSE;
	}

	@ConfigItem(
			keyName = "showOverlay",
			name = "Show Overlay",
			description = "",
			position = 5
	)
	default boolean showOverlay() { return true; }

	@ConfigSection(
			keyName = "delayConfig",
			name = "Sleep Delay Configuration",
			description = "Configure how long the bot will wait before clicking",
			position = 6
	)
	default boolean delayConfig()
	{
		return false;
	}

	@Range(
			min = 0,
			max = 500
	)
	@ConfigItem(
			keyName = "sleepMin",
			name = "Sleep Min",
			description = "sleep min",
			position = 5,
			section = "delayConfig"
	)
	default int sleepMin()
	{
		return 200;
	}

	@Range(
			min = 0,
			max = 600
	)
	@ConfigItem(
			keyName = "sleepMax",
			name = "Sleep Max",
			description = "",
			position = 7,
			section = "delayConfig"
	)
	default int sleepMax()
	{
		return 400;
	}

	@Range(
			min = 0,
			max = 600
	)
	@ConfigItem(
			keyName = "sleepTarget",
			name = "Sleep Target",
			description = "",
			position = 8,
			section = "delayConfig"
	)
	default int sleepTarget()
	{
		return 300;
	}

	@Range(
			min = 0,
			max = 600
	)
	@ConfigItem(
			keyName = "sleepDeviation",
			name = "Sleep Deviation",
			description = "",
			position = 9,
			section = "delayConfig"
	)
	default int sleepDeviation()
	{
		return 30;
	}

	@ConfigItem(
			keyName = "sleepWeightedDistribution",
			name = "Sleep Weighted Distribution",
			description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution",
			position = 10,
			section = "delayConfig"
	)
	default boolean sleepWeightedDistribution()
	{
		return false;
	}
}