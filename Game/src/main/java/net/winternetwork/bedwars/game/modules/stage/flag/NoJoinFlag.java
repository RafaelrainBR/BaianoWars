package net.winternetwork.bedwars.game.modules.stage.flag;

import net.winternetwork.bedwars.api.game.flag.Flag;
import org.bukkit.event.player.PlayerLoginEvent;

public class NoJoinFlag implements Flag<PlayerLoginEvent> {

    @Override
    public Class<PlayerLoginEvent> getEventClass() {
        return PlayerLoginEvent.class;
    }

    @Override
    public void execute(PlayerLoginEvent event) {
        event.disallow(
                PlayerLoginEvent.Result.KICK_WHITELIST,
                "§cO jogo já está rolando. Aguarde ele acabar."
        );
    }
}
