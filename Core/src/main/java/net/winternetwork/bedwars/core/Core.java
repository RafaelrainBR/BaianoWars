package net.winternetwork.bedwars.core;

import net.winternetwork.bedwars.api.plugin.WinterPlugin;
import net.winternetwork.bedwars.core.commands.EssentialsCommands;
import net.winternetwork.bedwars.core.commands.TellCommand;

public class Core extends WinterPlugin {

    @Override
    public void onPluginStart() {

        getFrame().registerCommands(
                new TellCommand(),
                new EssentialsCommands()
        );
    }

    @Override
    public void onPluginDies() {

    }
}
