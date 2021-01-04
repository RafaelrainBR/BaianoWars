package net.winternetwork.bedwars.api.plugin

import me.saiintbrisson.bukkit.command.BukkitFrame
import me.saiintbrisson.minecraft.command.message.MessageType.*
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

abstract class ServerPlugin : JavaPlugin() {

    private val frame: BukkitFrame by lazy {
        BukkitFrame(this, true).apply {
            mapOf(
                INCORRECT_USAGE to "§cUso errado do comando. Utilize: §f{usage}§c.",
                NO_PERMISSION to "§cVocê não tem permissão para executar este comando.",
                INCORRECT_TARGET to "§cEste comando só pode ser utilizado em jogo.",
                ERROR to "§cErro ao executar este comando: {error}"
            ).forEach { (type, message) ->
                messageHolder.setMessage(type, message)
            }
        }
    }

    fun commands(vararg commands: Any) = frame.registerCommands(commands)

    fun listeners(vararg listeners: Listener) {
        Bukkit.getPluginManager().also {
            for (listener in listeners) {
                it.registerEvents(listener, this)
            }
        }
    }
}