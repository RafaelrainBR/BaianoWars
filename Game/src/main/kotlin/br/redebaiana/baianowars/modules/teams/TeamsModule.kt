package br.redebaiana.baianowars.modules.teams

import br.redebaiana.baianowars.api.module.Module
import br.redebaiana.baianowars.game.game
import br.redebaiana.baianowars.modules.teams.manager.TeamManager

class TeamsModule : Module("Teams") {

    private val config by config(game, name = "teams.yml")

    override val manager = TeamManager(config)

    override fun init() {

    }
}