package br.redebaiana.baianowars.modules.teams.manager

import br.redebaiana.baianowars.api.config.YamlConfig
import br.redebaiana.baianowars.api.module.manager.Manager
import br.redebaiana.baianowars.modules.teams.model.Team

class TeamManager(config: YamlConfig) : Manager<String, Team>(config) {

    override fun loadAll() {
        val section = config.section("teams")

        val keys = section.getKeys(false)
        if (keys.isEmpty()) return

        keys.forEach {
            val team = Team.fromSection(section.getConfigurationSection(it))
            add(team.name, team)
        }
    }

    override fun unloadAll() {
        config.run {
            all {
                val prefix = "teams.${it.id}"

                set("${prefix}.name", it.name)
                set("${prefix}.chatColor", it.chatColor.name)
                set("${prefix}.leatherColor", it.leatherColorName)
            }
            save()
        }
    }
}