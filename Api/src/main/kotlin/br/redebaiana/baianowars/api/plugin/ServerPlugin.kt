package br.redebaiana.baianowars.api.plugin

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import me.saiintbrisson.bukkit.command.BukkitFrame
import me.saiintbrisson.minecraft.command.message.MessageType.*
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

typealias KoinModule = org.koin.core.module.Module

abstract class ServerPlugin(val hasModules: Boolean = false) : JavaPlugin() {

    val coroutineScope = CoroutineScope(Dispatchers.IO + CoroutineName(name))

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

    open val modules: KoinModule.() -> Unit = {}
}