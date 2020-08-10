package com.owain.chinlogin3

import net.runelite.client.config.Config
import net.runelite.client.config.ConfigGroup
import net.runelite.client.config.ConfigItem

@ConfigGroup("ChinLogin3")
interface ChinLoginConfig3 : Config {
    @ConfigItem(
            keyName = "email",
            name = "Email",
            description = "",
            position = 0
    )
    @JvmDefault
    fun email(): String {
        return ""
    }

    @ConfigItem(
            keyName = "password",
            name = "Password",
            description = "",
            position = 1,
            secret = true
    )
    @JvmDefault
    fun password(): String {
        return ""
    }
}