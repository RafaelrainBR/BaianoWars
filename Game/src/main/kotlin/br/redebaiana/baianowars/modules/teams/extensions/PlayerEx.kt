package br.redebaiana.baianowars.modules.teams.extensions

import br.redebaiana.baianowars.modules.teams.controller.TeamUserController
import org.bukkit.entity.Player

fun Player.asTeamUser() = TeamUserController.get(name)