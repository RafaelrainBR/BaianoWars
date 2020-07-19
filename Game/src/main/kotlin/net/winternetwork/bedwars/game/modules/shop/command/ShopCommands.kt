package net.winternetwork.bedwars.game.modules.shop.command

import me.saiintbrisson.commands.Execution
import me.saiintbrisson.commands.annotations.Command
import net.winternetwork.bedwars.api.util.inject
import net.winternetwork.bedwars.game.modules.shop.ShopController
import net.winternetwork.bedwars.game.modules.shop.ShopModule
import net.winternetwork.bedwars.game.modules.shop.model.VillagerModel
import net.winternetwork.bedwars.game.modules.shop.model.menu.MenuView

class ShopCommands {

    val module: ShopModule by inject()

    private val controller: ShopController by lazy { module.controller }
    private val view: MenuView by lazy { module.view }

    @Command(name = "summonshop", permission = "summonshop.use")
    fun summon(execution: Execution, id: String) {
        val location = execution.player.location

        controller.add(
                id,
                VillagerModel.spawnVillager(location)
        )
    }

    @Command(name = "openshop", permission = "openshop.use")
    fun openShop(execution: Execution) {
        view.showInventory(execution.player)
    }
}