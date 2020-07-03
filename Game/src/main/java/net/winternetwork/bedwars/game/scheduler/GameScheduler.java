package net.winternetwork.bedwars.game.scheduler;

import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.Game;
import net.winternetwork.bedwars.game.modules.Modules;
import org.bukkit.Bukkit;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

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
        Queue<Module> queue = new ConcurrentLinkedDeque<>(modules.getAll());
        for (int i = 0; i < ModulePriority.values().length; i++) {
            for (Module module : queue) {
                if (module.getPriority().getId() != i) continue;

                module.getScheduler().run();
                queue.remove(module);
            }
        }
    }
}
