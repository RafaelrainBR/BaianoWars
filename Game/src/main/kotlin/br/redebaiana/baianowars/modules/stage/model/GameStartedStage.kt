package br.redebaiana.baianowars.modules.stage.model

import br.redebaiana.baianowars.api.game.stage.Stage
import br.redebaiana.baianowars.api.score.ReplaceableList
import br.redebaiana.baianowars.modules.stage.flag.Flags
import br.redebaiana.baianowars.modules.timer.TimerModule

class GameStartedStage(private val timer: TimerModule) : Stage("Started", 60) {

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
            "<time>" to "${timer.timeElapsed}",
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