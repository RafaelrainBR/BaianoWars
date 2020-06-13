package net.winternetwork.bedwars.core.listener;

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
                        30
                )
        );
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(
                String.format(
                        "§c%s saiu. §f(%d/%d)",

                        e.getPlayer().getDisplayName(),
                        Bukkit.getOnlinePlayers().size(),
                        30
                )
        );
    }
}
