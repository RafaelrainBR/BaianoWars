package net.winternetwork.bedwars.game.module.map;

import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.module.map.command.MapSetupCommand;

public class MapModule extends Module {

    @Getter
    private MapManager mapManager;

    public MapModule() {
        super("Mapa", ModulePriority.NORMAL);
    }

    @Override
    public void init() {
        mapManager = new MapManager();

        getLogger().info("Carregando mapas...");
        mapManager.loadAll(Core.getInstance().getMapsConfig());

        getLogger().info("Registrando comandos...");
        Core.getInstance()
                .registerCommands(new MapSetupCommand(mapManager));
    }
}
