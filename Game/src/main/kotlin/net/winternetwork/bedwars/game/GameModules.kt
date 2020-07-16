package net.winternetwork.bedwars.game

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.modules.build.BuildModule
import net.winternetwork.bedwars.game.modules.generators.GeneratorsModule
import net.winternetwork.bedwars.game.modules.maps.MapModule
import net.winternetwork.bedwars.game.modules.score.ScoreModule
import net.winternetwork.bedwars.game.modules.shop.ShopModule
import net.winternetwork.bedwars.game.modules.stage.StageModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initModules() = startKoin {
    printLogger()
    modules(
            module {
                single { MapModule().doInit() }
                factory { StageModule(get()).doInit() }
                factory { ScoreModule(get()).doInit() }
                factory { GeneratorsModule(get()).doInit() }
                single { BuildModule().doInit() }
                single { ShopModule().doInit() }
            }
    )
}

fun disableModules() {
    modules.forEach {
        it.disable()
    }
}

lateinit var game: Game

val modules = mutableListOf<Module>()

private fun Module.doInit() = apply {
    modules.add(this)
    println(
            "[%s] Iniciando modulo %s".format("Modulos", name)
    )
    init()
}