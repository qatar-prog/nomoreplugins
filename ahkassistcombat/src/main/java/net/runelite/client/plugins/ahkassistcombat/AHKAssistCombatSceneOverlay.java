/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import net.runelite.api.*;
import net.runelite.client.ui.overlay.*;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.VarPlayer;

import static net.runelite.api.AnimationID.*;

@Singleton
class AHKAssistCombatSceneOverlay extends Overlay {
	private final Client client;
	private final AHKAssistCombatConfig config;
	private final AHKAssistCombatPlugin plugin;

	// NMZ
	private static final int[] NMZ_MAP_REGION = {9033};

	private boolean idleCheck = false;
	private boolean animatedCheck = false;

	private Instant lastIdle;
	private Instant currentTime;

	@Inject
	private AHKAssistCombatSceneOverlay(final Client client, final AHKAssistCombatConfig config, final AHKAssistCombatPlugin plugin) {
		setPosition(OverlayPosition.DYNAMIC);
		setPriority(OverlayPriority.LOW);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		this.client = client;
		this.config = config;
		this.plugin = plugin;
	}

	@Override
	public Dimension render(Graphics2D graphics) {

		// Low HP Indicator
		if (config.displayLowHP()) {
			int currentHPLevel = client.getBoostedSkillLevel(Skill.HITPOINTS);
			int warnHPLevel = config.lowHPLevel();
			if (currentHPLevel <= warnHPLevel) {
				graphics.setColor(config.lowHPColor());
				graphics.fillRect(config.lowHPX(), config.lowHPY(), config.lowHPWidth(), config.lowHPHeight());
			}
		}

		// Low Prayer Indicator
		if (config.displayLowPrayer()) {
			if (client.getBoostedSkillLevel(Skill.PRAYER) <= config.lowPrayerLevel()) {
				graphics.setColor(config.lowPrayerColor());
				graphics.fillRect(config.lowPrayerX(), config.lowPrayerY(), config.lowPrayerWidth(), config.lowPrayerHeight());
			}
		}

		// Low Run Indicator
		if (config.displayLowEnergy()) {
			int currentEnergyLevel = client.getEnergy();
			int warnEnergyLevel = config.lowEnergyLevel();
			if (currentEnergyLevel <= warnEnergyLevel) {
				graphics.setColor(config.energyColor());
				graphics.fillRect(config.energyX(), config.energyY(), config.energyWidth(), config.energyHeight());
			}
		}

		// Special Indicator
		if (config.displaySpecial()) {
			int specialPercentage = client.getVar(VarPlayer.SPECIAL_ATTACK_PERCENT) / 10;
			int showSpecialLevel = config.specialLevel();
			if (specialPercentage >= showSpecialLevel) {
				graphics.setColor(config.specialColor());
				graphics.fillRect(config.specialX(), config.specialY(), config.specialWidth(), config.specialHeight());
			}
		}

		// NMZ Related Configs
		if (isPlayerInNMZ()) {
			if (config.displayInNMZ()) {
				graphics.setColor(config.inNMZColor());
				graphics.fillRect(config.inNMZX(), config.inNMZY(), config.inNMZWidth(), config.inNMZHeight());
			}

			// NMZ Absorption Points
			if (config.displayLowAbsorption()) {
				int currentAbsorptionLevel = client.getVar(Varbits.NMZ_ABSORPTION);
				int warnAbsorptionLevel = config.lowAbsorptionLevel();
				if (currentAbsorptionLevel <= warnAbsorptionLevel) {
					graphics.setColor(config.absorptionColor());
					graphics.fillRect(config.absorptionX(), config.absorptionY(), config.absorptionWidth(), config.absorptionHeight());
				}
			}

			// NMZ Overload Points
			if (config.displayLowOverload()) {
				int currentOverloadLevel = client.getBoostedSkillLevel(Skill.HITPOINTS);
				int warnOverloadLevel = 51;
				if (currentOverloadLevel >= warnOverloadLevel) {
					graphics.setColor(config.overloadColor());
					graphics.fillRect(config.overloadX(), config.overloadY(), config.overloadWidth(), config.overloadHeight());
				}
			}

			// NMZ Ranging Indicator
			if (config.displayLowRange()) {
				int realRangedLevel = client.getRealSkillLevel(Skill.RANGED);
				int currentRangedLevel = client.getBoostedSkillLevel(Skill.RANGED);
				int warnRangedLevel = config.lowRangeLevel();
				if (currentRangedLevel < warnRangedLevel) {
					graphics.setColor(config.rangeNMZColor());
					graphics.fillRect(config.rangeX(), config.rangeY(), config.rangeWidth(), config.rangeHeight());
				}
			}

			// NMZ Magic Indicator
			if (config.displayLowMagic()) {
				int realMagicLevel = client.getRealSkillLevel(Skill.MAGIC);
				int currentMagicLevel = client.getBoostedSkillLevel(Skill.MAGIC);
				int warnMagicLevel = config.lowMagicLevel();
				if (currentMagicLevel < warnMagicLevel) {
					graphics.setColor(config.magicColor());
					graphics.fillRect(config.magicX(), config.magicY(), config.magicWidth(), config.magicHeight());

				}
			}
		}

		if (config.displayIdle()) {
			if (client.getLocalPlayer().getAnimation() == IDLE && !idleCheck) {
				lastIdle = Instant.now();
				idleCheck = true;
				animatedCheck = false;
			}
			if(client.getLocalPlayer().getAnimation() != IDLE && !animatedCheck) {
				animatedCheck = true;
				idleCheck = false;
			}
			if (idleCheck) {
				if (timerComplete(lastIdle, config.idleDelay())) {
					graphics.setColor(config.idleColor());
					graphics.fillRect(config.idleX(), config.idleY(), config.idleWidth(), config.idleHeight());
					if (client.getLocalPlayer().getAnimation() != IDLE)
						idleCheck = false;
				}
			}
		}
		return null;
	}

	public boolean isPlayerInNMZ() {
		if (client.getLocalPlayer().getWorldLocation().getPlane() > 0 && Arrays.equals(client.getMapRegions(), NMZ_MAP_REGION)) return true;
		return false;
	}

	public boolean timerComplete(Instant duration1, long duration2) {
		currentTime = Instant.now();
		Duration duration = Duration.between(duration1, currentTime);
		long secondsElapsed = duration.getSeconds(); // * 1000 to get milliseconds number.
		long limit = duration2;
		if ((secondsElapsed * 1000) >= limit) {
			return true;
		}
		return false;
	}
}