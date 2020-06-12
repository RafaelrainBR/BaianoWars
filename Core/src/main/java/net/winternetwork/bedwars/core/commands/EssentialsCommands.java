package net.winternetwork.bedwars.core.commands;

import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import me.saiintbrisson.commands.argument.Argument;
import org.bukkit.OfflinePlayer;

public class EssentialsCommands {

    @Command(
            name = "feed",
            usage = "feed <player>",
            description = "Curar a fome de um jogador.",
            permission = "command.essentials.feed"
    )
    public void feedCommand(Execution execution, @Argument(nullable = true) OfflinePlayer player) {

        if (player == null || !player.isOnline()) {
            execution.sendMessage("§eEste jogador não está online.");
            return;
        }

        player.getPlayer().setFoodLevel(20);

        execution.sendMessage("§4Fome saciada com sucesso. Viva ao comunismo!");
    }
}
