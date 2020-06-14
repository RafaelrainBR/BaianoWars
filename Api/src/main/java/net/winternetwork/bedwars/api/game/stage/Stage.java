package net.winternetwork.bedwars.api.game.stage;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.score.ReplaceableList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Stage {

    private final String stageName;

    private final int time;

    @Setter(value = AccessLevel.PROTECTED)
    private int timeLeft;

    public Stage(String name, int time) {
        this.stageName = name;
        this.time = time;

        this.timeLeft = time;
    }

    public abstract List<Flag> getFlags();

    public abstract ReplaceableList getScoreboard();

    public abstract void onStageJoin();

    public abstract void onSecondPassed();

    public abstract void onTimeLeft();

    public abstract void onStageExit();

    public final void operateTime() {
        this.timeLeft = timeLeft - 1;
    }

    protected final void broadcast(String... messages) {
        for (String message : messages) {
            Bukkit.broadcastMessage(message);
        }
    }

    protected final int getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().size();
    }

    protected final List<Player> getAllPlayers() {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }

}
