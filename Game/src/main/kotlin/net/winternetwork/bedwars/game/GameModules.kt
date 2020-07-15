package net.winternetwork.bedwars.game

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.modules.shop.ShopModule
import org.bukkit.plugin.Plugin
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Plugin.initModules() = startKoin {
    printLogger()
    modules(
            module {
                single { ShopModule().doInit() }

            }
    )
}

// TODO: Disable all modules.
fun Plugin.disableModules() {

}

lateinit var game: Game

private fun Module.doInit() = apply {
    println(
            "[%s] Iniciando modulo %s".format("Modulos", name)
    )
    init()
}