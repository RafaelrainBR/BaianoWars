package net.winternetwork.bedwars.game.module.map;

import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.Game;
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

        log("Carregando mapas...");
        mapManager.loadAll(Game.getGame().getMapsConfig());

        log("Registrando comandos...");
        Game.getGame()
                .registerCommands(new MapSetupCommand(mapManager));
    }

    @Override
    public void disable() {
        log("Salvando os mapas...");
        mapManager.unloadAll(Game.getGame().getMapsConfig());
    }
}
