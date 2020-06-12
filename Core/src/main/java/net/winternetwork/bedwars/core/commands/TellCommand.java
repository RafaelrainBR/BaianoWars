package net.winternetwork.bedwars.core.commands;

import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import me.saiintbrisson.commands.result.ResultType;
import org.bukkit.OfflinePlayer;

public class TellCommand {

    @Command(
            name = "tell",
            aliases = {"tell", "msg", "enviar"},
            description = "Envia uma mensagem para outro jogador.",
            usage = "tell <player> <message>"
    )
    public ResultType onCommand(Execution execution, OfflinePlayer target, String... messages) {
        if (target == null || !target.isOnline())
            return ResultType.INCORRECT_USAGE;

        execution.sendMessage("§eVocê enviou para %s: §f%s", target.getName(), String.join(" ", messages));

        target.getPlayer().sendMessage(
                String.format("§e%s te enviou: §f%s", execution.getPlayer().getName(), String.join(" ", messages))
        );

        return ResultType.NONE;
    }
}
