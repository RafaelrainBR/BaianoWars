package net.winternetwork.bedwars.game;

import lombok.Getter;
import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.api.plugin.WinterPlugin;
import net.winternetwork.bedwars.game.listener.JoinListener;
import net.winternetwork.bedwars.game.modules.ModuleManager;
import net.winternetwork.bedwars.game.modules.build.BuildModule;
import net.winternetwork.bedwars.game.modules.generators.GeneratorsModule;
import net.winternetwork.bedwars.game.modules.map.MapModule;
import net.winternetwork.bedwars.game.modules.score.ScoreModule;
import net.winternetwork.bedwars.game.modules.shop.ShopModule;
import net.winternetwork.bedwars.game.modules.stage.StageModule;
import net.winternetwork.bedwars.game.scheduler.GameScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game extends WinterPlugin {

    @Getter
    private static Game game;

    @Getter
    public int timeElapsed = 0;

    @Getter
    private YamlConfig mapsConfig;
    @Getter
    private YamlConfig generatorsConfig;

    @Override
    public void onPluginStart() {
        game = this;

        setupConfigs();
        registerListeners(new JoinListener());

        initModules();
        new GameScheduler();
    }

    private void setupConfigs() {
        saveDefaultConfig();

        mapsConfig = new YamlConfig(
                this,
                getDataFolder(),
                "maps.yml"
        );

        generatorsConfig = new YamlConfig(
                this,
                getDataFolder(),
                "generators.yml"
        );
    }

    private void initModules() {
        ModuleManager moduleManager = ModuleManager.getInstance();

        List<Module> modules = Arrays.asList(
                new GeneratorsModule(),
                new MapModule(),
                new ScoreModule(),
                new StageModule(),
                new BuildModule(),
                new ShopModule()
        );

        for (int i = 0; i < ModulePriority.values().length; i++) {
            int id = i;

            moduleManager.addAll(
                    modules
                            .stream()
                            .filter(it -> it.getPriority().getId() == id)
                            .collect(Collectors.toList())
            );
        }
    }

    public void registerCommands(Object... objects) {
        getFrame().register(objects);
    }

    @Override
    public void onPluginDies() {
        ModuleManager.getInstance().disableAll();
    }
}
