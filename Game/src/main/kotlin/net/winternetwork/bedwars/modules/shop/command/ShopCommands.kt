package net.winternetwork.bedwars.modules.shop.command

import me.saiintbrisson.minecraft.command.annotation.Command
import me.saiintbrisson.minecraft.command.command.Context
import me.saiintbrisson.minecraft.command.target.CommandTarget
import net.winternetwork.bedwars.api.util.inject
import org.bukkit.entity.Player

class ShopCommands {

    val module: _root_ide_package_.net.winternetwork.bedwars.modules.shop.ShopModule by inject()

    private val controller: _root_ide_package_.net.winternetwork.bedwars.modules.shop.controller.ShopController by lazy { module.controller }
    private val view: _root_ide_package_.net.winternetwork.bedwars.modules.shop.model.menu.MenuView by lazy { module.view }

    @Command(
            name = "summonshop",
            permission = "summonshop.use",
            target = CommandTarget.PLAYER
    )
    fun summon(ctx: Context<Player>, id: String) {
        val location = ctx.sender.location

        controller.add(
                id,
                _root_ide_package_.net.winternetwork.bedwars.modules.shop.model.VillagerModel.spawnVillager(location)
        )
    }

    @Command(name = "openshop", permission = "openshop.use")
    fun openShop(ctx: Context<Player>) {
        view.showInventory(ctx.sender)
    }
}