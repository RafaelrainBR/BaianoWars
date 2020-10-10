package net.winternetwork.bedwars.modules.stage

import net.winternetwork.bedwars.api.game.stage.Stage
import net.winternetwork.bedwars.game.settings.GameSettings

class StageManager(
        private val stages: List<Stage>
) {
    lateinit var actualStage: Stage
        private set

    private var i = 0

    init {
        if (GameSettings.canStart) {
            actualStage = stages[0]
            actualStage.onStageJoin()
        }
    }

    fun next() {
        val stage = actualStage

        stage.onStageExit()

        actualStage = stages[++i]
        actualStage.onStageJoin()
    }

    fun previous() {
        actualStage = stages[--i]
        actualStage.onStageJoin()
    }
}