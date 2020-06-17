package net.winternetwork.bedwars.core.listener;

import net.winternetwork.bedwars.core.Core;
import net.winternetwork.bedwars.core.game.generators.GeneratorManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

public class GenSetupListener implements Listener {

    private final Map<String, String> setupMap = Core.getInstance().getGenBlockMap();

    private final GeneratorManager manager = GeneratorManager.getInstance();

    @EventHandler
    public void onBreak(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (!setupMap.containsKey(player.getName())) return;
        e.setCancelled(true);

        Block block = e.getClickedBlock();
        manager.get(setupMap.get(player.getName())).setBlock(block);

        player.sendMessage("§aVocê setou o bloco com sucesso!");

        setupMap.remove(player.getName());
    }
}
