package net.winternetwork.bedwars.modules.stage

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.settings.GameSettings
import net.winternetwork.bedwars.modules.maps.MapModule
import net.winternetwork.bedwars.modules.stage.`object`.GameStartedStage
import net.winternetwork.bedwars.modules.stage.`object`.ToStartStage
import net.winternetwork.bedwars.modules.stage.`object`.WaitingPlayerStage
import net.winternetwork.bedwars.modules.stage.listener.FlagListener

class StageModule(mapModule: MapModule) : Module("Stages") {

    var stageManager: StageManager? = null
        private set

    private val stages = listOf(
        WaitingPlayerStage(this),
        ToStartStage(this, mapModule),
        GameStartedStage()
    )

    override fun init() {
        if (GameSettings.canStart) {
            log("Carregando estágios...")
            stageManager = StageManager(stages)

            log("Registrando listeners...")
            game.listeners(FlagListener(this))
        }
    }

    override fun onSecPassed() {
        if (!GameSettings.canStart) return

        stageManager?.actualStage?.run {
            onSecondPassed()
            operateTime()

            if (timeLeft <= 0)
                onTimeLeft()
        }
    }

}