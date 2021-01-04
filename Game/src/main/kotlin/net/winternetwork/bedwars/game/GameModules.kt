package net.winternetwork.bedwars.game

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.api.util.logColoredMessage
import net.winternetwork.bedwars.modules.build.BuildModule
import net.winternetwork.bedwars.modules.generators.GeneratorsModule
import net.winternetwork.bedwars.modules.maps.MapModule
import net.winternetwork.bedwars.modules.score.ScoreModule
import net.winternetwork.bedwars.modules.shop.ShopModule
import net.winternetwork.bedwars.modules.stage.StageModule
import net.winternetwork.bedwars.modules.teams.TeamsModule
import org.koin.core.context.startKoin
import org.koin.core.definition.BeanDefinition
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.core.module.Module as KoinModule

lateinit var game: Game

val moduleList = mutableListOf<Module>()

fun initModules() = startKoin {
    printLogger()

    val modules = module(createdAtStart = true) {
        initModule { MapModule() }
        initModule { StageModule(get()) }
        initModule { ScoreModule(get()) }
        initModule { GeneratorsModule(get()) }
        initModule { BuildModule() }
        initModule { ShopModule() }
        initModule { TeamsModule() }
    }
    modules(modules)
}

fun disableModules() {
    moduleList.forEach {
        it.disable()

        it.manager?.let { manager ->
            logColoredMessage(
                "§b§l[%s] §7Descarregando %s...".format(it.name, manager.name)
            )
            manager.unloadAll()
        }
    }
}

private inline fun KoinModule.initModule(crossinline block: Scope.() -> Module): BeanDefinition<Module> {
    return single {
        block(this).apply {
            moduleList.add(this)

            logColoredMessage(
                "§6§l[%s] §fIniciando modulo §e§l%s".format("Módulos", name)
            )
            manager?.let {
                logColoredMessage(
                    "§b§l[%s] §7Carregando %s...".format(name, it.name)
                )
                it.loadAll()
            }
            init()
        }
    }
    99
}