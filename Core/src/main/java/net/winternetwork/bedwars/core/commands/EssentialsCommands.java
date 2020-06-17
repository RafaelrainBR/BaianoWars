package net.winternetwork.bedwars.core.commands;

import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import me.saiintbrisson.commands.result.ResultType;
import net.winternetwork.bedwars.core.game.Game;
import org.bukkit.OfflinePlayer;

public class EssentialsCommands {

    @Command(
            name = "feed",
            usage = "feed <player>",
            description = "Curar a fome de um jogador.",
            permission = "command.essentials.feed"
    )
    public void feedCommand(Execution execution, OfflinePlayer player) {

        if (player == null || !player.isOnline()) {
            execution.sendMessage("§eEste jogador não está online.");
            return;
        }

        player.getPlayer().setFoodLevel(20);

        execution.sendMessage("§4Fome saciada com sucesso. Viva ao comunismo!");
    }

    @Command(
            name = "start",
            permission = "bedwars.admin"
    )
    public void startCommand(Execution execution) {
        execution.sendMessage("§eChamando classe game...");

        Game.getGame();
    }

    @Command(
            name = "tell",
            aliases = {"tell", "msg", "enviar"},
            description = "Envia uma mensagem para outro jogador.",
            usage = "tell <player> <message>"
    )
    public ResultType tellCommand(Execution execution, OfflinePlayer target, String... messages) {
        if (target == null || !target.isOnline())
            return ResultType.INCORRECT_USAGE;

        execution.sendMessage("§eVocê enviou para %s: §f%s", target.getName(), String.join(" ", messages));

        target.getPlayer().sendMessage(
                String.format("§e%s te enviou: §f%s", execution.getPlayer().getName(), String.join(" ", messages))
        );

        return ResultType.NONE;
    }
}
