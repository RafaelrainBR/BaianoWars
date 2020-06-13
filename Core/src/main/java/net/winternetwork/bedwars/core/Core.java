package net.winternetwork.bedwars.core;

import lombok.Getter;
import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.api.plugin.WinterPlugin;
import net.winternetwork.bedwars.core.commands.EssentialsCommands;
import net.winternetwork.bedwars.core.commands.MapSetupCommand;
import net.winternetwork.bedwars.core.commands.TellCommand;
import net.winternetwork.bedwars.core.game.map.MapManager;
import net.winternetwork.bedwars.core.listener.FlagListener;
import net.winternetwork.bedwars.core.listener.GameListener;

public class Core extends WinterPlugin {

    @Getter
    private static Core instance;

    @Getter
    private YamlConfig mapsConfig;

    @Override
    public void onPluginStart() {
        instance = this;

        setupConfig();
        MapManager.getInstance().loadAll(mapsConfig);

        getFrame().registerCommands(
                new TellCommand(),
                new EssentialsCommands(),
                new MapSetupCommand()
        );
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

    public void registerGameListeners() {
        registerListeners(
                new FlagListener(),
                new GameListener()
        );
    }
}
