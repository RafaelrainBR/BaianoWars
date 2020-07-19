package net.winternetwork.bedwars.game.modules.stage.`object`

import net.winternetwork.bedwars.api.game.stage.Stage
import net.winternetwork.bedwars.api.score.ReplaceableList
import net.winternetwork.bedwars.game.modules.maps.MapModule
import net.winternetwork.bedwars.game.modules.stage.StageModule
import net.winternetwork.bedwars.game.modules.stage.flag.Flags
import net.winternetwork.bedwars.game.settings.GameSettings

class ToStartStage(
        stageModule: StageModule,
        mapModule: MapModule
) : Stage("Starting", 20) {

    private val stageManager = stageModule.stageManager

    private val mapManager = mapModule.mapManager

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
    ) { s, player ->
        s
                .replace("<mapa>", "Gadolandia")
                .replace("<online>", "$onlinePlayers")
                .replace("<max>", "${GameSettings.MAX_PLAYERS}")
                .replace("<next>", "${timeLeft}s")
    }

    override fun onSecondPassed() {
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

        val map = mapManager.all()[0]

        allPlayers.forEachIndexed { i, p ->
            p.teleport(map.locations[i])
        }
    }

}