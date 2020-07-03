package net.winternetwork.bedwars.game.modules.shop;

import lombok.Getter;
import me.saiintbrisson.minecraft.InventoryFrame;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.ModulePriority;
import net.winternetwork.bedwars.game.Game;
import net.winternetwork.bedwars.game.modules.shop.command.ShopCommands;
import net.winternetwork.bedwars.game.modules.shop.controller.ShopController;
import net.winternetwork.bedwars.game.modules.shop.listener.VillagerListener;
import net.winternetwork.bedwars.game.modules.shop.model.menu.MenuView;
import org.bukkit.entity.Entity;

public class ShopModule extends Module {

    private final Game game = Game.getGame();
    @Getter(lazy = true)
    private final ShopController shopController = new ShopController();
    @Getter
    private MenuView view;

    public ShopModule() {
        super("Shop", ModulePriority.LOWEST);
    }

    @Override
    public void init() {
        log("Abrindo hook ao inventory-framework");
        view = new MenuView(game);
        new InventoryFrame(game).registerListener();

        log("Registrando comandos...");
        game.registerCommands(new ShopCommands());
        log("Registrando listeners...");
        game.registerListeners(new VillagerListener());
    }

    @Override
    public void disable() {
        getShopController().getAll().forEach(Entity::remove);
    }
}
