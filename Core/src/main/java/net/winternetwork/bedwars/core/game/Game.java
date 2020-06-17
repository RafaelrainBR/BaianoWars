package net.winternetwork.bedwars.core.game;

import lombok.Getter;
import net.winternetwork.bedwars.core.Core;
import net.winternetwork.bedwars.core.game.generators.GeneratorScheduler;
import net.winternetwork.bedwars.core.game.map.MapManager;
import net.winternetwork.bedwars.core.game.stage.StageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

public class Game {

    private static Game game;

    @Getter
    private StageManager stageManager;
    @Getter
    private GameScheduler scheduler;
    @Getter
    private MapManager mapManager;
    @Getter
    private GeneratorScheduler generatorScheduler;

    @Getter
    int timeElapsed = 0;

    public static Game getGame() {
        return game == null ? game = new Game() : game;
    }

    private Game() {
        startGame();
    }

    private void startGame() {

        this.stageManager = StageManager.getInstance();
        this.scheduler = new GameScheduler(this);
        this.generatorScheduler = new GeneratorScheduler();

        mapManager = MapManager.getInstance();

        Core.getInstance().registerGameListeners();

        Bukkit.getServicesManager()
                .register(
                        Game.class,
                        this,
                        Core.getInstance(),
                        ServicePriority.Highest
                );
    }

}
