package net.winternetwork.bedwars.core.listener;

import net.winternetwork.bedwars.core.game.Game;
import net.winternetwork.bedwars.core.game.map.GameMap;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(
                String.format(
                        "§e%s entrou. §f(%d/%d)",

                        e.getPlayer().getDisplayName(),
                        Bukkit.getOnlinePlayers().size(),
                        15
                )
        );

        GameMap map = Game.getGame().getMapManager().getAll().get(0);
        if (map != null) {
            e.getPlayer().teleport(map.getLobbyLocation());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(
                String.format(
                        "§c%s saiu. §f(%d/%d)",

                        e.getPlayer().getDisplayName(),
                        Bukkit.getOnlinePlayers().size(),
                        15
                )
        );
    }
}
