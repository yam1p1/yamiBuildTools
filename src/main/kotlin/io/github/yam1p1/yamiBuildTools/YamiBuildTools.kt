package io.github.yam1p1.yamiBuildTools

import io.github.yam1p1.yamiBuildTools.Event.PlayerClicked
import org.bukkit.plugin.java.JavaPlugin

class YamiBuildTools : JavaPlugin() {

    override fun onEnable() {
        plugin = this
        logger.info("[YamiBuildTools] Enabled!")

        //EventListener
        server.pluginManager.registerEvents(PlayerClicked(), this)

        //config
        saveDefaultConfig()
    }
}

lateinit var plugin: YamiBuildTools
    private set