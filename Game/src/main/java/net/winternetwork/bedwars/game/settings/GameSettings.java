package net.winternetwork.bedwars.game.settings;

import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.game.module.stage.model.GameStartedStage;
import net.winternetwork.bedwars.game.module.stage.model.ToStartStage;
import net.winternetwork.bedwars.game.module.stage.model.WaitingPlayerStage;

public class GameSettings {

    public static final int PLAYERS_TO_START = 4;
    public static final int MAX_PLAYERS = 4;

    public static final Stage[] STAGE_ARRAY = {
            new WaitingPlayerStage(),
            new ToStartStage(),
            new GameStartedStage()
    };
}
