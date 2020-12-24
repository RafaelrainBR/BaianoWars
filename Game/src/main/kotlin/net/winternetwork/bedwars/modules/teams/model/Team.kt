package net.winternetwork.bedwars.modules.teams.model

import com.google.common.base.Enums
import net.md_5.bungee.api.ChatColor
import org.bukkit.configuration.ConfigurationSection

data class Team(
    val id: String,
    val name: String,
    val chatColor: ChatColor,
    val leatherColorName: String
) {
    companion object {
        fun fromSection(section: ConfigurationSection): Team {
            return with(section) {
                Team(
                    name,
                    getString("name"),
                    Enums.getIfPresent(ChatColor::class.java, getString("chatColor")).get(),
                    getString("leatherColor")
                )
            }
        }
    }
}