package io.github.yam1p1.yamiBuildTools.Player

import io.github.yam1p1.yamiBuildTools.plugin
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.scheduler.BukkitRunnable

class PlayerFlag(val player: Player) {

    fun set() {
        setMetadata(true)
        object : BukkitRunnable() {
            override fun run() {
                setMetadata(false)
            }
        }.runTaskLater(plugin, 1)
    }

    private fun setMetadata(flag: Boolean) {
        player.setMetadata("PlayerInteractEvent", FixedMetadataValue(plugin, flag))
    }

    fun get(): Boolean {
        try {
            return player.getMetadata("PlayerInteractEvent")[0].value() as Boolean
        } catch (e: Exception) {
            return false
        }
    }
}