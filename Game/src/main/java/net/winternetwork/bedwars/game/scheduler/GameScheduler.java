package net.winternetwork.bedwars.game.scheduler;

import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.game.Game;
import net.winternetwork.bedwars.game.module.Modules;
import org.bukkit.Bukkit;

public class GameScheduler {

    private final Modules modules = Modules.getInstance();

    public GameScheduler() {
        Bukkit.getScheduler()
                .runTaskTimer(
                        Game.getGame(),
                        this::schedule,
                        0,
                        20L
                );
    }

    private void schedule() {
        for (Module module : modules.getAll()) {
            module.getScheduler().run();
        }
    }
}
