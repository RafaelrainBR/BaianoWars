package net.winternetwork.bedwars.game.modules.generators.command

import me.saiintbrisson.commands.Execution
import me.saiintbrisson.commands.annotations.Command
import net.winternetwork.bedwars.game.modules.generators.manager.GeneratorManager
import net.winternetwork.bedwars.game.modules.generators.model.Generator
import org.bukkit.Material

class GeneratorCommands(
        val generatorManager: GeneratorManager,
        val setupMap: MutableMap<String, String>
) {

    @Command(
            name = "generators",
            permission = "generators.use"
    )
    fun help(execution: Execution) {
        execution.sendMessage(
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
    fun create(execution: Execution, name: String) {
        val hand = execution.player.itemInHand
        if (hand == null || hand.type == Material.AIR) {
            execution.sendMessage("§cVocê não está segurando nenhum item na mão.")
            return
        }

        Generator(name, hand.type).run {
            generatorManager.add(this)
            execution.sendMessage(this.toString())
        }
    }

    @Command(
            name = "generators.setblock",
            permission = "generators.use",
            usage = "generators setblock <nome>"
    )
    fun setBlock(execution: Execution, name: String) {
        val generator = generatorManager[name]
        if (generator == null) {
            execution.sendMessage("não foi encontrado nenhum gerador com esse nome.")
            return
        }

        setupMap[execution.player.name] = name
        execution.sendMessage("§aClique em um bloco.")
    }
}