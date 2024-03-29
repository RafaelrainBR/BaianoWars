package br.redebaiana.baianowars.modules.stage.model

import br.redebaiana.baianowars.api.game.stage.Stage
import br.redebaiana.baianowars.api.score.ReplaceableList
import br.redebaiana.baianowars.game.settings.GameSettings
import br.redebaiana.baianowars.modules.maps.MapModule
import br.redebaiana.baianowars.modules.stage.StageModule
import br.redebaiana.baianowars.modules.stage.flag.Flags

class ToStartStage(
    stageModule: StageModule,
    mapModule: MapModule
) : Stage("Starting", 20) {

    private val stageManager = stageModule.stageManager

    private val mapManager = mapModule.manager

    private var started = false

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
            "&fComeçando em: &a<next>",
            "&2",
            "&fJogadores: &a<online>/<max>",
            "&3",
            "&ewww.baianowars.kt"
        )
    ) {
        mapOf(

            "<mapa>" to "Gadolandia",
            "<online>" to "$onlinePlayers",
            "<max>" to "${GameSettings.MAX_PLAYERS}",
            "<next>" to "${timeLeft}s"
        )
    }

    override suspend fun onSecondPassed() {
        when {
            timeLeft <= 0 -> {
                stageManager!!.next()
                return
            }
            timeLeft <= 5 -> broadcast("§eComeçando o jogo em ${timeLeft}s")
        }
    }

    override fun onTimeLeft() {
        if (onlinePlayers < GameSettings.PLAYERS_TO_START) {
            broadcast(
                "§eAinda não tem jogadores suficientes para começar."
            )
            stageManager!!.previous()
            return
        }

        started = true
        stageManager!!.next()
    }

    override fun onStageExit() {
        if (!started) return
        broadcast("§2Começou!")

        val map = mapManager.all().first()

        allPlayers.forEachIndexed { i, p ->
            p.teleport(map.locations[i])
        }
    }

}