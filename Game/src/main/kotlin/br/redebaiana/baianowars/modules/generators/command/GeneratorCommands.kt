package br.redebaiana.baianowars.modules.generators.command

import br.redebaiana.baianowars.modules.generators.manager.GeneratorManager
import br.redebaiana.baianowars.modules.generators.model.Generator
import me.saiintbrisson.minecraft.command.annotation.Command
import me.saiintbrisson.minecraft.command.command.Context
import me.saiintbrisson.minecraft.command.target.CommandTarget
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GeneratorCommands(
    private val generatorManager: GeneratorManager,
    private val setupMap: MutableMap<String, String>
) {

    @Command(
        name = "generators",
        permission = "generators.use",
        target = CommandTarget.PLAYER
    )
    fun help(ctx: Context<CommandSender>) {
        ctx.sendMessage(
            arrayOf(
                "",
                "§e§l/generators §fcreate §e<nome> §6- segurando item na mão.",
                "§e§l/generators §fsetblock §e<nome> §6- e depois clica no bloco",
                ""
            )
        )
    }

    @Command(
        name = "generators.create",
        permission = "generators.use",
        usage = "generators create <nome>"
    )
    fun create(ctx: Context<Player>, name: String) {
        val hand = ctx.sender.itemInHand
        if (hand == null || hand.type == Material.AIR) {
            ctx.sendMessage("§cVocê não está segurando nenhum item na mão.")
            return
        }

        Generator(name, hand.type).run {
            generatorManager.add(this.name, this)
            ctx.sendMessage(this.toString())
        }
    }

    @Command(
        name = "generators.setblock",
        permission = "generators.use",
        usage = "generators setblock <nome>"
    )
    fun setBlock(ctx: Context<Player>, name: String) {
        val generator = generatorManager[name]
        if (generator == null) {
            ctx.sendMessage("não foi encontrado nenhum gerador com esse nome.")
            return
        }

        setupMap[ctx.sender.name] = name
        ctx.sendMessage("§aClique em um bloco.")
    }
}