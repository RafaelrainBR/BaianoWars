package net.winternetwork.bedwars.core;

import lombok.Getter;
import net.winternetwork.bedwars.api.plugin.WinterPlugin;
import net.winternetwork.bedwars.core.commands.EssentialsCommands;
import net.winternetwork.bedwars.core.commands.TellCommand;
import net.winternetwork.bedwars.core.listener.FlagListener;

public class Core extends WinterPlugin {

    @Getter
    private static Core instance;

    @Override
    public void onPluginStart() {
        instance = this;

        getFrame().registerCommands(
                new TellCommand(),
                new EssentialsCommands()
        );
    }

    @Override
    public void onPluginDies() {

    }

    public void registerFlagListener() {
        registerListeners(
                new FlagListener()
        );
    }
}
