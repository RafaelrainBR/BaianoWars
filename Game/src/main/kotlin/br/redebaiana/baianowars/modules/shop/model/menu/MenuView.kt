package br.redebaiana.baianowars.modules.shop.model.menu

import me.saiintbrisson.minecraft.ItemBuilder
import me.saiintbrisson.minecraft.paginator.PaginatedView
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin
import java.util.function.Supplier

class MenuView(
    owner: Plugin
) : PaginatedView<ShopItem>(
    owner,
    "Menu de compra",
    arrayOf(
        "OOOOOOOOO",
        "OXXXXXXXO",
        "OOOOOOOOO",
        "OOO<O>OOO"
    ),
    Supplier {
        listOf(
            ShopItem(
                "§fBlocos de lã",
                ItemBuilder(Material.WOOL, 16, 3).build(),
                16,
                Material.IRON_INGOT,
                4
            )
        )
    }
) {
    init {
        setItemProcessor { player, item, event ->
            event.isCancelled = true

            with(item) {
                if (!player.inventory.contains(material, cost)) {
                    player.run {
                        sendMessage("§cVocê não tem os items necessários.")
                        closeInventory()
                        return@setItemProcessor
                    }
                }

                player.inventory.run {
                    remove(material, cost)
                    addItem(stack)
                }

                player.run {
                    closeInventory()
                    sendMessage(
                        "§2Você comprou §f%s §2com sucesso!".format(item.name)
                    )
                }
            }
        }
    }

    private fun Inventory.remove(material: Material, amount: Int) {
        for (i in contents.indices) {
            val content = contents[i]
            if (content == null || content.type != material) continue

            content.amount -= amount
            setItem(i, content)
            break
        }
    }
}