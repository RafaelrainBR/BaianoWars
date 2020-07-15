package net.winternetwork.bedwars.game.scheduler

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.winternetwork.bedwars.api.module.Module
import java.util.concurrent.ConcurrentLinkedQueue

object GameScheduler {

    // TODO: pegar todos os modulos
    val modules = listOf<Module>()

    init {
        GlobalScope.launch {
            while (true) {
                delay(1000)
                schedule()
            }
        }
    }

    private fun schedule() {
        val queue = ConcurrentLinkedQueue(modules)

        while (!queue.isEmpty()) {
            val module = queue.poll()

            module.onSecPassed()
        }
    }
}