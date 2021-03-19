package br.redebaiana.baianowars.game

import br.redebaiana.baianowars.api.plugin.ServerPlugin
import br.redebaiana.baianowars.game.listener.JoinListener
import br.redebaiana.baianowars.game.listener.PluginEnableListener
import br.redebaiana.baianowars.game.scheduler.ModuleScheduler
import br.redebaiana.baianowars.game.settings.GameSettings
import kotlinx.coroutines.*
import org.koin.core.KoinApplication

class Game : ServerPlugin() {

    private lateinit var koinApplication: KoinApplication
    private lateinit var moduleScheduler: ModuleScheduler

    override fun onEnable() {
        coroutineScope.launch {
            game = this@Game

            saveDefaultConfig()
            GameSettings.canStart = config.getBoolean("canStart")

            launch(Dispatchers.Main) {
                koinApplication = initModules()
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