package net.winternetwork.bedwars.game.modules.shop.command;

import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import net.winternetwork.bedwars.game.modules.Modules;
import net.winternetwork.bedwars.game.modules.shop.ShopModule;
import net.winternetwork.bedwars.game.modules.shop.controller.ShopController;
import net.winternetwork.bedwars.game.modules.shop.model.VillagerModel;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

public class ShopCommands {

    private final ShopController controller = Modules.getModule(ShopModule.class).getShopController();

    @Command(name = "summonshop", permission = "summonshop.use")
    public void summon(Execution execution, String id) {
        Location location = execution.getPlayer().getLocation();

        Villager villager = VillagerModel.spawnVillager(location);

        controller.add(id, villager);
    }
}
