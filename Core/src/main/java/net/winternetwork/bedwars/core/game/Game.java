package net.winternetwork.bedwars.core.game;

import lombok.Getter;
import net.winternetwork.bedwars.core.Core;
import net.winternetwork.bedwars.core.game.map.MapManager;
import net.winternetwork.bedwars.core.game.stage.StageManager;

public class Game {

    private static Game game;

    @Getter
    private StageManager stageManager;
    @Getter
    private GameScheduler scheduler;

    private MapManager mapManager;

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

        mapManager = MapManager.getInstance();

        Core.getInstance().registerGameListeners();
    }

}
