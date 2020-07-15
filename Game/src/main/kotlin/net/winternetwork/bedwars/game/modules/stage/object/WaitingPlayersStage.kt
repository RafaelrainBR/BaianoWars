package net.winternetwork.bedwars.game.modules.stage.`object`

import net.winternetwork.bedwars.api.game.stage.Stage
import net.winternetwork.bedwars.api.score.ReplaceableList
import net.winternetwork.bedwars.api.util.inject
import net.winternetwork.bedwars.game.modules.stage.StageModule
import net.winternetwork.bedwars.game.modules.stage.flag.Flags
import net.winternetwork.bedwars.game.settings.GameSettings

class WaitingPlayersStage : Stage(
        "Aguardando jogadores",
        20
) {
    val stageModule: StageModule by inject()

    val stageManager = stageModule.stageManager

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


}