/*
 * Copyright (c) 2018, James Swindle <wilingua@gmail.com>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package net.runelite.client.plugins.autologin2;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;

import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import org.pf4j.Extension;


@Extension
@PluginDescriptor(
        name = "Pinq's Auto Login TWO",
        description = "description. description. description.",
        tags = {"pinqer"},
        type = PluginType.MINIGAME
)
@Slf4j
public class AutoLogin2Plugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private AutoLogin2Config config;

    @Inject
    private KeyManager keyManager;

    String playerName;
    Boolean sameAcc = false;

    @Provides
    AutoLogin2Config provideConfig(ConfigManager configManager) {
        return configManager.getConfig(AutoLogin2Config.class);
    }

    @Override
    protected void startUp() {
    }

    @Override
    protected void shutDown() {
    }

    @Subscribe
    public void onGameTick(GameTick tick) {
        if (client.getUsername() != null) {
            log.info(client.getUsername());
            log.info(config.email2());
            if (client.getUsername().equals(config.email2())) {
                log.info("same acc = true");
                sameAcc = true;
            } else {
                log.info("same acc = false");
                sameAcc = false;
            }
        }
    }

    @Subscribe
    private void onGameStateChanged(GameStateChanged gm) {
        log.info("Gamestate changed");
        log.info("The value of 'config.example()' is ${config.example()}");
        log.info(gm.getGameState().toString());
        if (gm.getGameState() == GameState.LOGIN_SCREEN && sameAcc) {
            log.info(gm.getGameState().toString());
            client.setUsername(config.email2());
            client.setPassword(config.password2());
            client.setGameState(GameState.LOGGING_IN);
        }
        /*
        if (gm.getGameState() == GameState.LOGGED_IN) {
            KeyEvent keyPress = new KeyEvent(this.client.getCanvas(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_NUMPAD2);
            this.client.getCanvas().dispatchEvent(keyPress);
            KeyEvent keyRelease = new KeyEvent(this.client.getCanvas(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_NUMPAD2);
            this.client.getCanvas().dispatchEvent(keyRelease);
        }
         */
    }
}
