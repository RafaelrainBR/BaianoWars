package net.winternetwork.bedwars.api.game.stage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.winternetwork.bedwars.api.game.flag.Flag;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@RequiredArgsConstructor
public abstract class Stage {

    private final String stageName;

    private final int Time;
    private final TimeUnit timeUnit;

    public abstract List<Flag> getFlags();

    public abstract void onStageJoin();

    public abstract void onSecondPassed();

    public abstract void onTimeLeft();

    public abstract void onStageExit();

    protected void broadcast(String... messages) {
        for (String message : messages) {
            Bukkit.broadcastMessage(message);
        }
    }

    protected int getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().size();
    }
}
