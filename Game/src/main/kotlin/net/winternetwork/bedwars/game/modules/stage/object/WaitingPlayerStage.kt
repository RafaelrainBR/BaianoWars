package net.winternetwork.bedwars.game.modules.stage.`object`

import net.winternetwork.bedwars.api.game.stage.Stage
import net.winternetwork.bedwars.api.score.ReplaceableList
import net.winternetwork.bedwars.game.modules.stage.StageModule
import net.winternetwork.bedwars.game.modules.stage.flag.Flags
import net.winternetwork.bedwars.game.settings.GameSettings

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

    ) { s, player ->
        s.replace("<waiting>", "${GameSettings.PLAYERS_TO_START - onlinePlayers}")
                .replace("<mapa>", "Gadolandia")
                .replace("<online>", "$onlinePlayers")
                .replace("<max>", "15")
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