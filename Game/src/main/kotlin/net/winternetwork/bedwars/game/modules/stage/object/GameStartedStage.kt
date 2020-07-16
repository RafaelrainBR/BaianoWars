package net.winternetwork.bedwars.game.modules.stage.`object`

import net.winternetwork.bedwars.api.game.stage.Stage
import net.winternetwork.bedwars.api.score.ReplaceableList
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.stage.flag.Flags

class GameStartedStage : Stage("Started", 60) {

    override val flags = listOf(
            Flags.NO_JOIN_FLAG
    )

    override val scoreboard = ReplaceableList(
            listOf(
                    "&1",
                    "&fTempo de jogo: &e<time>",
                    "&fJogadores vivos: &a<online>",
                    "&2",
                    "&f Kills: &a<kills>",
                    "&f Camas dormidas: &c<beds>",
                    "&3",
                    "&ewww.baianowars.kt"
            )
    ) { s, player ->
        s
                .replace("<time>", "${++game.timeElapsed}")
                .replace("<online>", "$onlinePlayers")
                .replace("<kills>", "${0}")
                .replace("<beds>", "${0}")

    }

    override fun onTimeLeft() {
        broadcast(
                "§eTemos %d jogadores vivos.".format(onlinePlayers)
        )
        timeLeft = time
    }
}