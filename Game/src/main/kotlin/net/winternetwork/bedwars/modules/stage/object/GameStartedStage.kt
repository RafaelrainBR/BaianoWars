package net.winternetwork.bedwars.modules.stage.`object`

import net.winternetwork.bedwars.api.game.stage.Stage
import net.winternetwork.bedwars.api.score.ReplaceableList
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.modules.stage.flag.Flags

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
    ) {
        mapOf(
            "<time>" to "${++game.timeElapsed}",
            "<online>" to "$onlinePlayers",
            "<kills>" to "${0}",
            "<beds>" to "${0}"
        )
    }

    override fun onTimeLeft() {
        broadcast(
            "§eTemos %d jogadores vivos.".format(onlinePlayers)
        )
        timeLeft = time
    }
}