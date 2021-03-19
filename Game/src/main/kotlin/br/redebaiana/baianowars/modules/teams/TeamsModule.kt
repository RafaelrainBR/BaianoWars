package br.redebaiana.baianowars.modules.teams

import br.redebaiana.baianowars.api.config.YamlConfig
import br.redebaiana.baianowars.api.module.Module
import br.redebaiana.baianowars.game.game
import br.redebaiana.baianowars.modules.teams.manager.TeamManager

class TeamsModule : Module("Teams") {

    private val config: YamlConfig by lazy {
        YamlConfig(
            game,
            dataFolder,
            "teams.yml"
        )
    }

    override val manager = TeamManager(config)

    override fun init() {

    }
}