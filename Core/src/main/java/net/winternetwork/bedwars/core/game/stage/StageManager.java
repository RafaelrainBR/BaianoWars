package net.winternetwork.bedwars.core.game.stage;

import lombok.Getter;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.GameSettings;

public class StageManager {

    private static StageManager instance;
    private int stageId = 0;
    @Getter
    private Stage actualStage;

    private StageManager() {
        callFirstStage();
    }

    public static StageManager getInstance() {
        return instance == null ? instance = new StageManager() : instance;
    }

    private void callFirstStage() {
        actualStage = GameSettings.STAGE_ARRAY[0];
        actualStage.onStageJoin();
    }

    public void callNextStage() {
        final Stage stage = actualStage;

        if (stage != null) {
            stage.onStageExit();
        }

        actualStage = GameSettings.STAGE_ARRAY[++stageId];
        actualStage.onStageJoin();
    }

    public void callPreviousStage() {
        actualStage = GameSettings.STAGE_ARRAY[--stageId];

        actualStage.onStageJoin();
    }
}
