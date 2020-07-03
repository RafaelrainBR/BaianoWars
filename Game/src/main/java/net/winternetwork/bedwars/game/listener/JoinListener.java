package net.winternetwork.bedwars.game.listener;

import net.winternetwork.bedwars.game.modules.Modules;
import net.winternetwork.bedwars.game.modules.map.MapModule;
import net.winternetwork.bedwars.game.modules.map.object.GameMap;
import net.winternetwork.bedwars.game.settings.GameSettings;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(
                String.format(
                        "§e%s entrou. §f(%d/%d)",

                        e.getPlayer().getDisplayName(),
                        Bukkit.getOnlinePlayers().size(),
                        GameSettings.MAX_PLAYERS
                )
        );

        GameMap map = Modules.getModule(MapModule.class).getMapManager().getAll().get(0);
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
                        GameSettings.MAX_PLAYERS
                )
        );
    }
}
