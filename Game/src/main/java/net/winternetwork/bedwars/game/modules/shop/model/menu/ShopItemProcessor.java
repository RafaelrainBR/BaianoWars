package net.winternetwork.bedwars.game.modules.shop.model.menu;

import me.saiintbrisson.minecraft.paginator.PaginatedItemConsumer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ShopItemProcessor implements PaginatedItemConsumer<ShopItem> {

    @Override
    public void process(Player player, ShopItem shopItem, InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        if (!containsAtLest(player.getInventory(), shopItem.getMaterial(), shopItem.getCost())) {
            player.sendMessage("§cVocê não tem os items necessários.");
            player.closeInventory();
            return;
        }

        remove(player.getInventory(), shopItem.getMaterial(), shopItem.getCost());
        player.getInventory().addItem(shopItem.getStack());

        player.closeInventory();
        player.sendMessage(
                String.format("§2Você comprou %s §2com sucesso!", shopItem.getName())
        );
    }

    private boolean containsAtLest(PlayerInventory inventory, Material material, int amount) {
        return inventory.containsAtLeast(new ItemStack(material), amount);
    }

    private void remove(PlayerInventory inventory, Material material, int amount) {
        for (int i = 0; i < inventory.getContents().length; i++) {
            ItemStack content = inventory.getContents()[i];
            if (content == null || content.getType() != material) continue;

            content.setAmount(content.getAmount() - amount);
            inventory.setItem(i, content);
            break;
        }
    }
}
