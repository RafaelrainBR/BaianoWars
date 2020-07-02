package net.winternetwork.bedwars.game.module.stage;

import lombok.Getter;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.game.settings.GameSettings;

public class StageManager {

    private int stageId = 0;
    @Getter
    private Stage actualStage;

    StageManager() {
        callFirstStage();
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
        stageId = stageId - 1;
        actualStage = GameSettings.STAGE_ARRAY[stageId];

        actualStage.onStageJoin();
    }
}
