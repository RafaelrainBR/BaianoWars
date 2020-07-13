package net.winternetwork.bedwars.api.plugin

import me.saiintbrisson.commands.CommandFrame
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

abstract class ServerPlugin : JavaPlugin() {

    private val frame: CommandFrame by lazy {
        CommandFrame(this, true).apply {
            usageMessage = "§cUso errado do comando. Utilize: §f{usage}§c."
            lackPermMessage = "§cVocê não tem permissão para executar este comando."
            incorrectTargetMessage = "§cEste comando só pode ser utilizado em jogo."
            errorMessage = "§cErro ao executar este comando. Contate um administrador."

            registerType(OfflinePlayer::class.java, Bukkit::getOfflinePlayer)
            registerType(Player::class.java, Bukkit::getPlayer)
        }
    }

    fun commands(vararg commands: Any) = frame.register(commands)

    fun listeners(vararg listeners: Listener) {
        Bukkit.getPluginManager().also {
            for (listener in listeners) {
                it.registerEvents(listener, this)
            }
        }
    }
}