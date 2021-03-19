package br.redebaiana.baianowars.modules.stage

import br.redebaiana.baianowars.api.module.Module
import br.redebaiana.baianowars.game.game
import br.redebaiana.baianowars.game.settings.GameSettings
import br.redebaiana.baianowars.modules.maps.MapModule
import br.redebaiana.baianowars.modules.stage.listener.FlagListener
import br.redebaiana.baianowars.modules.stage.model.GameStartedStage
import br.redebaiana.baianowars.modules.stage.model.ToStartStage
import br.redebaiana.baianowars.modules.stage.model.WaitingPlayerStage
import br.redebaiana.baianowars.modules.timer.TimerModule

class StageModule(mapModule: MapModule, timerModule: TimerModule) : Module("Stages") {

    var stageManager: StageManager? = null
        private set

    private val stages = listOf(
        WaitingPlayerStage(this),
        ToStartStage(this, mapModule),
        GameStartedStage(timerModule)
    )

    override fun init() {
        if (GameSettings.canStart) {
            log("Carregando estágios...")
            stageManager = StageManager(stages)

            log("Registrando listeners...")
            game.listeners(FlagListener(this))
        }
    }

    override suspend fun onSecPassed() {
        if (!GameSettings.canStart) return

        stageManager?.actualStage?.run {
            onSecondPassed()
            operateTime()

            if (timeLeft <= 0)
                onTimeLeft()
        }
    }

}