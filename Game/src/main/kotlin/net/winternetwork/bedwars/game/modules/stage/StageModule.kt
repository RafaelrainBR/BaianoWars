package net.winternetwork.bedwars.game.modules.stage

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.maps.MapModule
import net.winternetwork.bedwars.game.modules.stage.`object`.GameStartedStage
import net.winternetwork.bedwars.game.modules.stage.`object`.ToStartStage
import net.winternetwork.bedwars.game.modules.stage.`object`.WaitingPlayerStage
import net.winternetwork.bedwars.game.modules.stage.listener.FlagListener
import net.winternetwork.bedwars.game.settings.GameSettings

class StageModule(mapModule: MapModule) : Module("Stages") {

    lateinit var stageManager: StageManager
        private set

    private val stages = listOf(
            WaitingPlayerStage(this),
            ToStartStage(this, mapModule),
            GameStartedStage()
    )

    init {
        if (GameSettings.canStart) {
            log("Carregando estágios...")
            stageManager = StageManager(stages)

            log("Registrando listeners...")
            game.listeners(FlagListener(this))
        }
    }

    override fun onSecPassed() {
        if (!GameSettings.canStart) return

        stageManager.actualStage.run {
            onSecondPassed()
            operateTime()

            if (timeLeft <= 0)
                onTimeLeft()
        }
    }

}