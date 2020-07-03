package net.winternetwork.bedwars.game.modules.generators.command;

import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import net.winternetwork.bedwars.game.modules.Modules;
import net.winternetwork.bedwars.game.modules.generators.GeneratorManager;
import net.winternetwork.bedwars.game.modules.generators.GeneratorsModule;
import net.winternetwork.bedwars.game.modules.generators.object.Generator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class GeneratorCommands {

    private final GeneratorsModule module = Modules.getModule(GeneratorsModule.class);

    private final GeneratorManager manager = module.getManager();
    private final Map<String, String> setupMap = module.getGetSetupMap();

    @Command(
            name = "generators",
            permission = "generators.use"
    )
    public void sendHelp(Execution execution) {
        execution.sendMessage(new String[]{
                "",
                "/generators create <nome> - segurando o item na mão.",
                "/generators setBlock <nome> - e depois clica no bloco",
                ""
        });
    }

    @Command(
            name = "generators.create",
            permission = "generators.use",
            usage = "generators create <nome>"
    )
    public void create(Execution execution, String name) {
        ItemStack hand = execution.getPlayer().getItemInHand();

        if (hand == null || hand.getType() == Material.AIR) {
            execution.sendMessage("Você não está segurando nenhum item na mão!");
            return;
        }

        Generator generator = new Generator(name, hand.getType());

        manager.put(generator);

        execution.sendMessage(
                generator.toString()
        );
    }

    @Command(
            name = "generators.setblock",
            permission = "generators.use",
            usage = "generators setblock <nome>"
    )
    public void setBlock(Execution execution, String name) {
        Generator generator = manager.get(name);

        if (generator == null) {
            execution.sendMessage("não foi encontrado nenhum gerador com esse nome.");
            return;
        }

        setupMap.put(execution.getPlayer().getName(), name);

        execution.sendMessage("§eClique em um bloco.");
    }

}
