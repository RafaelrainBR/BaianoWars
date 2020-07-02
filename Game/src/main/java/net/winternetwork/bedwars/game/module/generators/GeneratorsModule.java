package net.winternetwork.bedwars.game.module.generators;

import lombok.Getter;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.Game;
import net.winternetwork.bedwars.game.module.Modules;
import net.winternetwork.bedwars.game.module.generators.command.GeneratorCommands;
import net.winternetwork.bedwars.game.module.generators.listener.GenSetupListener;
import net.winternetwork.bedwars.game.module.generators.object.Generator;
import net.winternetwork.bedwars.game.module.stage.StageManager;
import net.winternetwork.bedwars.game.module.stage.StageModule;
import net.winternetwork.bedwars.game.module.stage.flag.Flags;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GeneratorsModule extends Module {

    private StageManager stageManager;

    @Getter
    private final Map<String, String> getSetupMap = new HashMap<>();

    @Getter
    private GeneratorManager manager;

    public GeneratorsModule() {
        super("Geradores", ModulePriority.LOW);
    }

    @Override
    public void init() {
        stageManager = Modules.getModule(StageModule.class).getStageManager();

        log("Carregando geradores...");

        manager = new GeneratorManager();
        manager.loadAll(Game.getGame().getGeneratorsConfig());

        log("Registrando comandos...");
        Game.getGame()
                .registerCommands(new GeneratorCommands());

        log("Registrando listeners...");
        Game.getGame()
                .registerListeners(new GenSetupListener());
    }

    @Override
    public void disable() {
        log("Salvando geradores...");
        manager.unloadAll(Game.getGame().getGeneratorsConfig());
    }

    @Override
    public Runnable getScheduler() {
        return () -> {
            final Stage stage = stageManager.getActualStage();

            if (stage.getFlags().contains(Flags.NO_GENERATOR)) return;

            for (Generator generator : manager.getAll()) {
                if (generator.getTime() <= 0) {
                    Block block = generator.getBlock();
                    block.getWorld().dropItem(
                            block.getLocation().add(0, 0.7, 0),
                            new ItemStack(generator.getItemType())
                    );

                    generator.setTime(10);
                    continue;
                }
                generator.setTime(generator.getTime() - 1);
            }

        };
    }
}
