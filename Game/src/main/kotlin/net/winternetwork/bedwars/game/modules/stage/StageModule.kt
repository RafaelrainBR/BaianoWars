package net.winternetwork.bedwars.game.modules.stage

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.maps.MapModule
import net.winternetwork.bedwars.game.modules.stage.`object`.GameStartedStage
import net.winternetwork.bedwars.game.modules.stage.`object`.ToStartStage
import net.winternetwork.bedwars.game.modules.stage.`object`.WaitingPlayerStage
import net.winternetwork.bedwars.game.modules.stage.listener.FlagListener

class StageModule(mapModule: MapModule) : Module("Stages") {

    val stageManager: StageManager

    private val stages = listOf(
            WaitingPlayerStage(this),
            ToStartStage(this, mapModule),
            GameStartedStage()
    )

    init {
        log("Carregando estágios...")
        stageManager = StageManager(stages)

        log("Registrando listeners...")
        game.listeners(FlagListener(this))
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