package io.github.yam1p1.yamiBuildTools.Event

import io.github.yam1p1.yamiBuildTools.Player.BuildTeleport
import io.github.yam1p1.yamiBuildTools.Player.PlayerFlag
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class PlayerClicked : Listener {

    @EventHandler
    fun onPlayerClick(e: PlayerInteractEvent) {
        val player = e.player

        //多重起動防止
        val playerFlag = PlayerFlag(player)
        if (playerFlag.get()) {
            return
        }

        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (player.inventory.itemInMainHand.type == Material.DIAMOND_SWORD) {
                playerFlag.set()
                e.isCancelled = true

                //テレポート処理
                BuildTeleport().teleportForward(player)
            }
            //オフハンドにダイヤモンドの剣
            if (player.inventory.itemInOffHand.type == Material.DIAMOND_SWORD) {
                playerFlag.set()
                e.isCancelled = true
                //スニークした状態
                if (player.isSneaking) {
                    BuildTeleport().changeTeleportDistance(player,-1)
                } else{
                    BuildTeleport().changeTeleportDistance(player,1)
                }

            }
        }
    }
}