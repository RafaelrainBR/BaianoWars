package net.winternetwork.bedwars.core.game;

import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.stage.GameStartedStage;
import net.winternetwork.bedwars.core.game.stage.ToStartStage;
import net.winternetwork.bedwars.core.game.stage.WaitingPlayerStage;

public class GameSettings {

    public static final int PLAYERS_TO_START = 4;

    public static final Stage[] STAGE_ARRAY = {
            new WaitingPlayerStage(),
            new ToStartStage(),
            new GameStartedStage()
    };
}
