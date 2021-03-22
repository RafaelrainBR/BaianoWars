package br.redebaiana.baianowars.game

import br.redebaiana.baianowars.api.plugin.ServerPlugin
import br.redebaiana.baianowars.api.util.logColoredMessage
import br.redebaiana.baianowars.game.listener.JoinListener
import br.redebaiana.baianowars.game.listener.PluginEnableListener
import br.redebaiana.baianowars.game.scheduler.ModuleScheduler
import br.redebaiana.baianowars.game.settings.GameSettings
import kotlinx.coroutines.*
import org.koin.core.KoinApplication
import kotlin.system.measureTimeMillis

class Game : ServerPlugin() {

    private val koinApplication: KoinApplication by lazy { initModules() }

    private lateinit var moduleScheduler: ModuleScheduler

    override fun onEnable() {
        coroutineScope.launch {
            game = this@Game

            saveDefaultConfig()
            GameSettings.canStart = config.getBoolean("canStart")

            launch {
                val time = measureTimeMillis { koinApplication }
                logColoredMessage("§6§l[Módulos]§f Modulos inicializados com sucesso! (${time}ms)")
            }.invokeOnCompletion {
                moduleScheduler = ModuleScheduler(moduleList)
            }

            listeners(
                JoinListener(),
                PluginEnableListener(koinApplication)
            )
        }
    }

    override fun onDisable() {
        moduleScheduler.stop()

        disableModules()
        koinApplication.close()
    }
}