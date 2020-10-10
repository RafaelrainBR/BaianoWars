package net.winternetwork.bedwars.api.plugin

import me.saiintbrisson.bukkit.command.BukkitFrame
import me.saiintbrisson.minecraft.command.message.MessageType
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

abstract class ServerPlugin : JavaPlugin() {

    private val frame: BukkitFrame by lazy {
        BukkitFrame(this, true).apply {
            messageHolder.run {
                setMessage(MessageType.INCORRECT_USAGE, "§cUso errado do comando. Utilize: §f{usage}§c.")
                setMessage(MessageType.NO_PERMISSION, "§cVocê não tem permissão para executar este comando.")
                setMessage(MessageType.INCORRECT_TARGET, "§cEste comando só pode ser utilizado em jogo.")
                setMessage(MessageType.ERROR, "§cErro ao executar este comando: {error}")
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