package br.redebaiana.baianowars.modules.stage.model

import br.redebaiana.baianowars.api.game.stage.Stage
import br.redebaiana.baianowars.api.score.ReplaceableList
import br.redebaiana.baianowars.game.settings.GameSettings
import br.redebaiana.baianowars.modules.stage.StageModule
import br.redebaiana.baianowars.modules.stage.flag.Flags

class WaitingPlayerStage(stageModule: StageModule) : Stage(
    "Aguardando jogadores",
    20
) {
    private val stageManager = stageModule.stageManager

    override val flags = listOf(
        Flags.NO_PVP_FLAG,
        Flags.NO_BREAK_FLAG,
        Flags.NO_BUILD_FLAG,
        Flags.NO_GENERATOR_FLAG
    )

    override val scoreboard = ReplaceableList(
        listOf(
            "&1",
            "&fMapa: &a<mapa>",
            "&2",
            "&fAguardando...",
            "&fJogadores: &a<online>/<max>",
            "&3",
            "&ewww.baianowars.kt"
        )

    ) {
        mapOf(
            "<waiting>" to "${GameSettings.PLAYERS_TO_START - onlinePlayers}",
            "<mapa>" to "Gadolandia",
            "<online>" to "$onlinePlayers",
            "<max>" to "15"
        )
    }

    override fun onStageJoin() {
        broadcast(
            "§eTemos %d jogador%s online.".format(
                onlinePlayers, if (onlinePlayers > 1) "es" else ""
            ),
            "§ePara o jogo começar, precisamos de mais %d.".format(
                GameSettings.PLAYERS_TO_START - onlinePlayers
            )
        )
    }

    override fun onTimeLeft() {
        if (onlinePlayers >= GameSettings.PLAYERS_TO_START)
            stageManager!!.next()
        else {
            timeLeft = time
            onStageJoin()
        }
    }

}