package net.winternetwork.bedwars.core.game;

import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.score.Score;
import net.winternetwork.bedwars.core.Core;
import net.winternetwork.bedwars.core.game.stage.StageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class GameScheduler {

    private final Game game;
    private final StageManager stageManager;

    public GameScheduler(Game game) {
        this.game = game;
        this.stageManager = game.getStageManager();

        Bukkit.getScheduler().runTaskTimer(
                Core.getInstance(),
                this::scheduleGame,
                0,
                20
        );
    }

    private void scheduleGame() {
        scheduleTime();
    }

    private void scheduleTime() {
        ++game.timeElapsed;

        final Stage stage = stageManager.getActualStage();

        System.out.println(stage.getTimeLeft());

        stage.onSecondPassed();
        stage.operateTime();

        scheduleScore();

        if (stage.getTimeLeft() <= 0) {
            stage.onTimeLeft();
        }
    }

    private void scheduleScore() {
        final Stage stage = stageManager.getActualStage();

        final Score score = new Score("&e&lBAIANO WARS");

        for (Player player : Bukkit.getOnlinePlayers()) {
            List<String> lines = stage.getScoreboard().call(player);

            Collections.reverse(lines);

            for (int i = lines.size(); i > 0; i--) {
                int id = i - 1;
                score.setLine(id, lines.get(id));
            }

            score.show(player);
        }
    }
}
