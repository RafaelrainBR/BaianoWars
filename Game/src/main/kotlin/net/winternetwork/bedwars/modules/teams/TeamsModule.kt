package net.winternetwork.bedwars.modules.teams

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.teams.manager.TeamManager

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