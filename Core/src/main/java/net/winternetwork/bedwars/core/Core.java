package net.winternetwork.bedwars.core;

import lombok.Getter;
import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.api.plugin.WinterPlugin;
import net.winternetwork.bedwars.core.commands.EssentialsCommands;
import net.winternetwork.bedwars.core.commands.GeneratorCommands;
import net.winternetwork.bedwars.core.commands.MapSetupCommand;
import net.winternetwork.bedwars.core.game.map.MapManager;
import net.winternetwork.bedwars.core.listener.FlagListener;
import net.winternetwork.bedwars.core.listener.GameListener;
import net.winternetwork.bedwars.core.listener.GenSetupListener;

import java.util.HashMap;
import java.util.Map;

public class Core extends WinterPlugin {

    @Getter
    private static Core instance;

    @Getter
    private YamlConfig mapsConfig;

    @Getter
    private final Map<String, String> genBlockMap = new HashMap<>();

    @Override
    public void onPluginStart() {
        instance = this;

        setupConfig();
        MapManager.getInstance().loadAll(mapsConfig);

        getFrame().registerCommands(
                new EssentialsCommands(),
                new MapSetupCommand(),
                new GeneratorCommands()
        );

        registerCoreListeners();
    }

    @Override
    public void onPluginDies() {
        MapManager.getInstance().unloadAll(mapsConfig);
    }

    private void setupConfig() {
        saveDefaultConfig();

        mapsConfig = new YamlConfig(
                this,
                getDataFolder(),
                "maps.yml"
        );
    }

    private void registerCoreListeners() {
        registerListeners(
                new GenSetupListener()
        );
    }

    public void registerGameListeners() {
        registerListeners(
                new FlagListener(),
                new GameListener()
        );
    }
}
