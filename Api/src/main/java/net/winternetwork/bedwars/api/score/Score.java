package net.winternetwork.bedwars.api.score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class Score {

    private final String title;

    private final List<TableLine> lines = Collections.synchronizedList(new ArrayList<>());

    public void show(Player player) {
        Scoreboard score = player.getScoreboard();

        if (score == null)
            score = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = getObjective(player);

        obj.setDisplayName(c(title));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        updateLines(player);
        player.setScoreboard(score);
    }

    public void setLine(int line, String text) {
        lines.add(new TableLine(line, text, null));
    }

    private void updateLines(Player player) {
        Objective objective = getObjective(player);

        for (int i = lines.size(); i > 0; i--) {
            TableLine line = lines.get(i - 1);
            String text = c(line.getText());

            String score = scoreEntry(objective, line.getLine());
            if (score != null) {
                if (score.equalsIgnoreCase(text)) continue;
                objective.getScoreboard().resetScores(score);
            }
            objective.getScore(text).setScore(line.getLine());
        }
    }

    private String scoreEntry(Objective o, int score) {
        return o.getScoreboard()
                .getEntries()
                .stream()
                .filter(it -> o.getScore(it).getScore() == score)
                .findFirst()
                .orElse(null);
    }

    private Objective getObjective(Player player) {
        Scoreboard score = player.getScoreboard();

        Objective objective;

        if ((objective = score.getObjective(player.getName())) == null) {
            objective = score.registerNewObjective(player.getName(), "dummy");
        }

        return objective;
    }

    private String c(String c) {
        return ChatColor.translateAlternateColorCodes('&', c);
    }

    @Getter
    @AllArgsConstructor
    private static class TableLine {

        private final int line;
        private final String text;
        private final Function<Player, String> function;
    }
}
