package net.winternetwork.bedwars.game.modules.build;

import com.google.common.collect.Sets;
import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.Game;
import net.winternetwork.bedwars.game.modules.build.listener.BuildListener;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.io.File;
import java.util.Set;

public class BuildModule extends Module {

    private final Game plugin = Game.getGame();

    @Getter
    private final Set<Block> blockSet = Sets.newHashSet();

    public BuildModule() {
        super("Construção", ModulePriority.LOWEST);
    }

    @Override
    public void init() {

        log("Registrando listeners...");
        plugin.registerListeners(new BuildListener(plugin));
    }

    @Override
    public void disable() {
        log("Resetando mapa...");
        for (Block block : blockSet) {
            block.removeMetadata("putted", plugin);
            block.setType(Material.AIR);
        }

        File playerData = new File("world/playerdata");

        if (playerData.exists()) {
            playerData.delete();
        }
    }
}
