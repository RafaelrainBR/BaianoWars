package net.winternetwork.bedwars.game.scheduler

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.winternetwork.bedwars.game.modules
import java.util.concurrent.ConcurrentLinkedQueue

class GameScheduler {

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