package net.winternetwork.bedwars.modules.shop.model.menu

import me.saiintbrisson.minecraft.ItemBuilder
import me.saiintbrisson.minecraft.paginator.PaginatedItem
import me.saiintbrisson.minecraft.paginator.PaginatedViewHolder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

data class ShopItem(
        val name: String,
        val stack: ItemStack,
        val amount: Int,
        val material: Material,
        val cost: Int
) : PaginatedItem {

    override fun toItemStack(viewer: Player, holder: PaginatedViewHolder): ItemStack {
        return ItemBuilder(stack)
                .name(name)
                .lore(
                        "",
                        "§eMaterial: §f" + material.name.toLowerCase(),
                        "§eCusto: §f$cost",
                        ""
                )
                .amount(amount)
                .build()
    }

}