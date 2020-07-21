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

import net.runelite.api.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.ui.overlay.*;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import static net.runelite.api.AnimationID.*;

@Singleton
class AHKAssistSkillingSceneOverlay extends Overlay {
	private final Client client;
	private final AHKAssistSkillingConfig config;
	private final AHKAssistSkillingPlugin plugin;

	private boolean idleCheck = false;
	private boolean animatedCheck = false;

	private Instant lastIdle;
	private Instant currentTime;

	@Inject
	private AHKAssistSkillingSceneOverlay(final Client client, final AHKAssistSkillingConfig config, final AHKAssistSkillingPlugin plugin) {
		setPosition(OverlayPosition.DYNAMIC);
		setPriority(OverlayPriority.LOW);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		this.client = client;
		this.config = config;
		this.plugin = plugin;
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		if (config.displayBank()) {
			Widget bankWidget = client.getWidget(WidgetInfo.BANK_TITLE_BAR);
			if (bankWidget != null && !bankWidget.isHidden()) {
				graphics.setColor(config.bankColor());
				graphics.fillRect(config.bankX(), config.bankY(), config.bankWidth(), config.bankHeight());
			}
		}

		if (config.displayConnected()) {
			if (client.getGameState() == GameState.LOGGED_IN) {
				graphics.setColor(config.connectedColor());
				graphics.fillRect(config.connectedX(), config.connectedY(),config.connectedWidth(), config.connectedHeight());
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
		//boolean yes = true;
		//if (yes) {
		//	renderObjectOverlay(graphics, , Color.CYAN);
		//}

		//if (config.playerLocation()) {
		//	int currentX = client.getLocalPlayer().getWorldLocation().getX();
		//	int currentY = client.getLocalPlayer().getWorldLocation().getY();
		//	int currentRegion = client.getLocalPlayer().getWorldLocation().getPlane();
		//	int tickCount = client.getTickCount();
		//		String myString = currentX + ", " + currentY + ", " + currentRegion + ", " + tickCount;
		//		Toolkit.getDefaultToolkit()
		//				.getSystemClipboard()
		//				.setContents(
		//						new StringSelection(myString),
		//						null
		//				);
		//}
		return null;

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

	private void renderNpcOverlay(Graphics2D graphics, NPC actor, Color color) {
		Shape npc = actor.getConvexHull();
		int x = (int) (actor.getConvexHull().getBounds().getCenterX() - 4 / 2);
		int y = (int) (actor.getConvexHull().getBounds().getCenterY() - 4 / 2);
		graphics.setColor(color);
		graphics.fillRect(x, y, 4, 4);
	}
}