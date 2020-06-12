package net.winternetwork.bedwars.core.game;

import lombok.Getter;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.stage.WaitingPlayerStage;

public class Game {

    @Getter(lazy = true)
    private static final Game game = new Game();

    private static final Stage[] STAGE_ARRAY = {
            new WaitingPlayerStage(),

    };
    private int stageNumber = -1;
    @Getter
    private Stage actualStage;

    private Game() {
        startGame();
    }

    public static void callNextStage() {
        Game game = getGame();

        Stage stage = game.actualStage;

        if (stage != null) {
            stage.onStageExit();
        }

        game.actualStage = STAGE_ARRAY[++game.stageNumber];

        game.actualStage.onStageJoin();
    }

    private void startGame() {
        callNextStage();
    }

}
