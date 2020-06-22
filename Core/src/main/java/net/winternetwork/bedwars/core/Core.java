package net.winternetwork.bedwars.core;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import lombok.Getter;
import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.api.plugin.WinterPlugin;
import net.winternetwork.bedwars.core.commands.EssentialsCommands;
import net.winternetwork.bedwars.core.commands.GeneratorCommands;
import net.winternetwork.bedwars.core.commands.MapSetupCommand;
import net.winternetwork.bedwars.core.game.generators.GeneratorManager;
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
    private final Map<String, String> generatorsBlockSetMap = new HashMap<>();
    @Getter
    private YamlConfig generatorsConfig;

    @Override
    public void onPluginStart() {
        instance = this;

        HologramsAPI.getHolograms(this).forEach(Hologram::delete);

        setupConfig();

        MapManager.getInstance().loadAll(mapsConfig);
        GeneratorManager.getInstance().loadAll(generatorsConfig);

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
        GeneratorManager.getInstance().unloadAll(generatorsConfig);
    }

    private void setupConfig() {
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
