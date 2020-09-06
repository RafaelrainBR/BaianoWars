package net.winternetwork.bedwars.modules.stage

import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.settings.GameSettings

class StageModule(mapModule: _root_ide_package_.net.winternetwork.bedwars.modules.maps.MapModule) : Module("Stages") {

    var stageManager: _root_ide_package_.net.winternetwork.bedwars.modules.stage.StageManager? = null
        private set

    private val stages = listOf(
            _root_ide_package_.net.winternetwork.bedwars.modules.stage.`object`.WaitingPlayerStage(this),
            _root_ide_package_.net.winternetwork.bedwars.modules.stage.`object`.ToStartStage(this, mapModule),
            _root_ide_package_.net.winternetwork.bedwars.modules.stage.`object`.GameStartedStage()
    )

    override fun init() {
        if (GameSettings.canStart) {
            log("Carregando estágios...")
            stageManager = _root_ide_package_.net.winternetwork.bedwars.modules.stage.StageManager(stages)

            log("Registrando listeners...")
            game.listeners(_root_ide_package_.net.winternetwork.bedwars.modules.stage.listener.FlagListener(this))
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