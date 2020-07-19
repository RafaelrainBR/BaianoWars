package net.winternetwork.bedwars.game

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.modules.build.BuildModule
import net.winternetwork.bedwars.game.modules.generators.GeneratorsModule
import net.winternetwork.bedwars.game.modules.maps.MapModule
import net.winternetwork.bedwars.game.modules.score.ScoreModule
import net.winternetwork.bedwars.game.modules.shop.ShopModule
import net.winternetwork.bedwars.game.modules.stage.StageModule
import org.bukkit.Bukkit
import org.koin.core.context.startKoin
import org.koin.dsl.module

lateinit var game: Game

val moduleList = mutableListOf<Module>()

fun initModules() = startKoin {
    printLogger()

    val modules = module(createdAtStart = true) {
        single { MapModule().doInit() }
        single { StageModule(get()).doInit() }
        single { ScoreModule(get()).doInit() }
        single { GeneratorsModule(get()).doInit() }
        single { BuildModule().doInit() }
        single { ShopModule().doInit() }
    }
    modules(modules)
}

fun disableModules() {
    moduleList.forEach {
        it.disable()
    }
}

private fun <T : Module> T.doInit() = apply {
    moduleList.add(this)

    Bukkit.getConsoleSender().sendMessage(
            "§6§l[%s] §fIniciando modulo §e§l%s".format("Modulos", name)
    )
    init()
}