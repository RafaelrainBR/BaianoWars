package net.winternetwork.bedwars.game.modules.stage

import net.winternetwork.bedwars.api.game.stage.Stage
import net.winternetwork.bedwars.game.settings.GameSettings

class StageManager {

    var actualStage: Stage?
        private set

    private var i = 0

    init {
        actualStage = GameSettings.STAGE_ARRAY[0]
        actualStage!!.onStageJoin()
    }

    fun next() {
        val stage = actualStage

        stage?.onStageExit()

        actualStage = GameSettings.STAGE_ARRAY[++i]
        actualStage?.onStageJoin()
    }

    fun previous() {
        actualStage = GameSettings.STAGE_ARRAY[--i]
        actualStage?.onStageJoin()
    }
}