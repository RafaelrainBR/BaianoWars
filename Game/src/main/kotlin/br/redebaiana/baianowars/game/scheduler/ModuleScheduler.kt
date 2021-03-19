package br.redebaiana.baianowars.game.scheduler

import br.redebaiana.baianowars.api.module.Module
import kotlinx.coroutines.*

class ModuleScheduler(val modules: List<Module>) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + CoroutineName("ModuleScheduler"))

    init {
        coroutineScope.launch {
            while (this.isActive) {
                launch {
                    modules.forEach { schedule(it) }
                }
                delay(1000)
            }
        }
    }

    private suspend fun schedule(module: Module) {
        try {
            module.onSecPassed()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() = coroutineScope.cancel()
}