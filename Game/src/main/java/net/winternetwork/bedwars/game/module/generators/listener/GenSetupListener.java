package net.winternetwork.bedwars.game.module.generators.listener;

import net.winternetwork.bedwars.game.module.Modules;
import net.winternetwork.bedwars.game.module.generators.GeneratorManager;
import net.winternetwork.bedwars.game.module.generators.GeneratorsModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

public class GenSetupListener implements Listener {

    private final GeneratorsModule module = Modules.getModule(GeneratorsModule.class);

    private final GeneratorManager manager = module.getManager();
    private final Map<String, String> setupMap = module.getGetSetupMap();

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
