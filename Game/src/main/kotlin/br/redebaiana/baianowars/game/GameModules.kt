package br.redebaiana.baianowars.game

import br.redebaiana.baianowars.api.module.Module
import br.redebaiana.baianowars.api.util.logColoredMessage
import br.redebaiana.baianowars.modules.build.BuildModule
import br.redebaiana.baianowars.modules.generators.GeneratorsModule
import br.redebaiana.baianowars.modules.maps.MapModule
import br.redebaiana.baianowars.modules.score.ScoreModule
import br.redebaiana.baianowars.modules.shop.ShopModule
import br.redebaiana.baianowars.modules.stage.StageModule
import br.redebaiana.baianowars.modules.teams.TeamsModule
import br.redebaiana.baianowars.modules.timer.TimerModule
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
        initModule { TimerModule() }
        initModule { MapModule() }
        initModule { StageModule(get(), get()) }
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

        it.manager.let { manager ->
            logColoredMessage(
                "§b§l[%s] §7Descarregando %s...".format(it.name, manager?.name)
            )
            manager?.unloadAll()
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
            manager.let {
                logColoredMessage(
                    "§b§l[%s] §7Carregando %s...".format(name, it?.name)
                )
                it?.loadAll()
            }
            init()
        }
    }
}