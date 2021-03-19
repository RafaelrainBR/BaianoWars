package br.redebaiana.baianowars.game.listener

import br.redebaiana.baianowars.api.plugin.ServerPlugin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginEnableEvent
import org.koin.core.KoinApplication
import org.koin.dsl.module

class PluginEnableListener(private val koin: KoinApplication) : Listener {

    @EventHandler
    fun onPluginEnable(event: PluginEnableEvent) {
        if (event.plugin !is ServerPlugin) return

        val plugin = event.plugin as ServerPlugin
        if (!plugin.hasModules) return

        koin.modules(
            module {
                plugin.modules(this)
            }
        )
    }
}