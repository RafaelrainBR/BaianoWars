package net.winternetwork.bedwars.api.plugin;

import lombok.Getter;
import me.saiintbrisson.commands.CommandFrame;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class WinterPlugin extends JavaPlugin {

    @Getter
    private CommandFrame frame;

    @Override
    public final void onEnable() {
        setupCommandFrame();

        onPluginStart();
    }

    @Override
    public final void onDisable() {
        onPluginDies();
    }


    private void setupCommandFrame() {
        this.frame = new CommandFrame(this, true);

        frame.setUsageMessage("§cUso errado, utilize: §f/{usage}§c.");
        frame.setLackPermMessage("§cVocê não tem permissão para executar este comando.");
        frame.setIncorrectTargetMessage("§cEste comando só pode ser utilizado em jogo.");
        frame.setErrorMessage("§cErro ao executar este comando. Contate um administrador.");

        frame.registerType(OfflinePlayer.class, Bukkit::getOfflinePlayer);
    }

    public void registerListeners(Listener... listeners) {
        final PluginManager manager = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            manager.registerEvents(listener, this);
        }
    }

    public abstract void onPluginStart();

    public abstract void onPluginDies();
}
