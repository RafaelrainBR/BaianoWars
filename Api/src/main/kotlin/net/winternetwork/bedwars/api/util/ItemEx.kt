package net.winternetwork.bedwars.api.util

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta


typealias Item = ItemStack
typealias MetaBlock = (ItemMeta) -> Unit

fun Item.applyMeta(block: MetaBlock) = apply {
    this.itemMeta = itemMeta.apply(block)
}