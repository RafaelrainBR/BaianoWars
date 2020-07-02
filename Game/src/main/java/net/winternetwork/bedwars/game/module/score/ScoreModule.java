package net.winternetwork.bedwars.game.module.score;

import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.api.score.Score;
import net.winternetwork.bedwars.game.module.Modules;
import net.winternetwork.bedwars.game.module.stage.StageManager;
import net.winternetwork.bedwars.game.module.stage.StageModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ScoreModule extends Module {

    private final StageManager stageManager = Modules.getModule(StageModule.class).getStageManager();

    public ScoreModule() {
        super("Score", ModulePriority.HIGH);
    }

    @Override
    public Runnable getScheduler() {
        return () -> {
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
        };
    }
}
