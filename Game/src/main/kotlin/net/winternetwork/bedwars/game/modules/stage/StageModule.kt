package net.winternetwork.bedwars.game.modules.stage

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.stage.listener.FlagListener

class StageModule : Module("Stages") {

    val stageManager: StageManager

    init {
        log("Carregando estágios...")
        stageManager = StageManager()

        log("Registrando listeners...")
        game.listeners(FlagListener())
    }

    override fun onSecPassed() {
        ++game.timeElapsed

        stageManager.actualStage?.run {
            onSecondPassed()
            operateTime()

            if (timeLeft <= 0)
                onTimeLeft()
        }

    }

}