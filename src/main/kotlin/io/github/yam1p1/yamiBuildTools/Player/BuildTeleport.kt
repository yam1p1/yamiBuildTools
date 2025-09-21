package io.github.yam1p1.yamiBuildTools.Player

import io.github.yam1p1.yamiBuildTools.plugin
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.UUID

//TP距離保存用
private var teleportDistance: MutableMap<UUID, Int> = mutableMapOf()

class BuildTeleport {

    fun teleportForward(p: Player) {
        val default = plugin.config.getInt("default_teleport_distance")
        val distance = teleportDistance[p.uniqueId] ?: default

        val loc = p.location.clone()
        val target = loc.add(loc.direction.normalize().multiply(distance))

        p.teleport(target)
    }

    /**
     * @param p : 編集されるプレイヤー
     * @param delta : 変更値 (+-表記)
     */

    fun changeTeleportDistance(p: Player, delta: Int) {
        val default = plugin.config.getInt("default_teleport_distance")

        val updated = (teleportDistance[p.uniqueId] ?: default) + delta
        if(updated < 1){
            p.sendMessage(plugin.config.getString("message.teleport_distance_not_changed"))
            return
        }
        teleportDistance[p.uniqueId] = updated

        val message = plugin.config.getString("message.teleport_distance_change")
            ?.replace("%distance%", updated.toString())
            ?: "Teleport distance: $updated"

        p.sendMessage(message)
        p.playSound(p.location, Sound.UI_BUTTON_CLICK, 1f, 1f)
    }

}