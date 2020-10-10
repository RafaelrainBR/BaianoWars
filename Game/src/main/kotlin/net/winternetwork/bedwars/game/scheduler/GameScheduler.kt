package net.winternetwork.bedwars.game.scheduler

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.winternetwork.bedwars.game.moduleList
import java.util.concurrent.ConcurrentLinkedQueue

class GameScheduler {

    init {
        GlobalScope.launch {
            async {
                while (true) {
                    delay(1000)
                    schedule()
                }
            }.join()
        }
    }

    private fun schedule() {
        val queue = ConcurrentLinkedQueue(moduleList)

        while (!queue.isEmpty()) {
            val module = queue.poll()
            module.onSecPassed()
        }
    }
}