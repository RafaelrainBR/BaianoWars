package net.winternetwork.bedwars.modules.teams.extensions

import net.winternetwork.bedwars.modules.teams.controller.TeamUserController
import org.bukkit.entity.Player

fun Player.asTeamUser() = TeamUserController.get(name)