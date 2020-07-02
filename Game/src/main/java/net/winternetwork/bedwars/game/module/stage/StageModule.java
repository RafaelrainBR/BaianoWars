package net.winternetwork.bedwars.game.module.stage;

import lombok.Getter;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.Game;
import net.winternetwork.bedwars.game.module.stage.flag.listener.FlagListener;

public class StageModule extends Module {

    private final Game game = Game.getGame();

    @Getter
    private StageManager stageManager;

    public StageModule() {
        super(
                "Stages",
                ModulePriority.HIGHEST
        );
    }

    @Override
    public void init() {
        log("Iniciando sistema de estágios...");
        this.stageManager = new StageManager();

        log("Registrando listeners...");
        Game.getGame()
                .registerListeners(new FlagListener(stageManager));
    }

    @Override
    public Runnable getScheduler() {
        return () -> {
            ++game.timeElapsed;

            final Stage stage = stageManager.getActualStage();

            stage.onSecondPassed();
            stage.operateTime();

            if (stage.getTimeLeft() <= 0) {
                stage.onTimeLeft();
            }
        };
    }
}