package net.winternetwork.bedwars.game.modules.shop.model.menu;

import me.saiintbrisson.minecraft.ItemBuilder;
import me.saiintbrisson.minecraft.paginator.PaginatedView;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class MenuView extends PaginatedView<ShopItem> {


    public MenuView(Plugin owner) {
        super(owner, "Menu de compra", new String[]{
                "OOOOOOOOO",
                "OXXXXXXXO",
                "OOOOOOOOO",
                "OOO<O>OOO",
        }, () -> Arrays.asList(
                new ShopItem(
                        "§fBlocos de lã",
                        new ItemBuilder(Material.WOOL, 16, 3).build(),
                        16,
                        Material.IRON_INGOT,
                        4
                )
        ));
        setItemProcessor(new ShopItemProcessor());
    }

}
