package net.winternetwork.bedwars.game.modules.stage

import net.winternetwork.bedwars.api.game.stage.Stage
class StageManager(
        private val stages: List<Stage>
) {
    var actualStage: Stage?
        private set

    private var i = 0

    init {
        actualStage = stages[0]
        actualStage!!.onStageJoin()
    }

    fun next() {
        val stage = actualStage

        stage?.onStageExit()

        actualStage = stages[++i]
        actualStage?.onStageJoin()
    }

    fun previous() {
        actualStage = stages[--i]
        actualStage?.onStageJoin()
    }
}