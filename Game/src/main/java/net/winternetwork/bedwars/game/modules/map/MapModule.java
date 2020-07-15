package net.winternetwork.bedwars.game.modules.map;

import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.modules.map.command.MapSetupCommand;
import net.winternetwork.bedwars.game.net.winternetwork.bedwars.game.Game;

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
