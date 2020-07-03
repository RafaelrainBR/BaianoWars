package net.winternetwork.bedwars.game.modules.shop.model.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.saiintbrisson.minecraft.ItemBuilder;
import me.saiintbrisson.minecraft.paginator.PaginatedItem;
import me.saiintbrisson.minecraft.paginator.PaginatedViewHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class ShopItem implements PaginatedItem {

    private final String name;
    private final ItemStack stack;
    private final int amount;

    private final Material material;
    private final int cost;

    @Override
    public ItemStack toItemStack(Player player, PaginatedViewHolder paginatedViewHolder) {
        return new ItemBuilder(stack)
                .name(name)
                .lore(
                        "",
                        "§eMaterial: §f" + material.name().toLowerCase(),
                        "§eCusto: §f" + cost,
                        ""
                )
                .amount(amount)
                .build();
    }
}
