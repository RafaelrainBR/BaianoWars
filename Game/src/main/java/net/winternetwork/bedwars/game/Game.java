package net.winternetwork.bedwars.game;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import lombok.Getter;
import net.winternetwork.bedwars.api.config.YamlConfig;
import net.winternetwork.bedwars.api.plugin.WinterPlugin;
import net.winternetwork.bedwars.game.listener.JoinListener;

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

        HologramsAPI.getHolograms(this).forEach(Hologram::delete);

        registerCommands(new JoinListener());
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

    public void registerCommands(Object... objects) {
        getFrame().register(objects);
    }

    @Override
    public void onPluginDies() {

    }
}
