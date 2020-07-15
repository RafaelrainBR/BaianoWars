package net.winternetwork.bedwars.game.settings

import com.google.common.collect.ImmutableList
import net.winternetwork.bedwars.game.modules.stage.model.GameStartedStage
import net.winternetwork.bedwars.game.modules.stage.model.ToStartStage
import net.winternetwork.bedwars.game.modules.stage.model.WaitingPlayerStage

object GameSettings {

    const val PLAYERS_TO_START = 4
    const val MAX_PLAYERS = 4

    val STAGE_ARRAY = ImmutableList.of(
            WaitingPlayerStage(),
            ToStartStage(),
            GameStartedStage()
    )
}