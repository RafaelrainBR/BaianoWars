package net.winternetwork.bedwars.game.modules.generators

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.modules.generators.command.GeneratorCommands
import net.winternetwork.bedwars.game.modules.generators.manager.GeneratorManager
import net.winternetwork.bedwars.game.modules.stage.StageModule
import net.winternetwork.bedwars.game.modules.stage.flag.Flags
import net.winternetwork.bedwars.game.settings.GameSettings
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class GeneratorsModule(
        stageModule: StageModule
) : Module("Geradores") {

    private val stageManager = stageModule.stageManager

    val generatorManager = GeneratorManager()
    val setupMap = hashMapOf<String, String>()

    private val config: YamlConfig by lazy {
        YamlConfig(
                game,
                dataFolder,
                "generators.yml"
        )
    }

    override fun init() {
        log("Carregando geradores...")
        generatorManager.loadAll(config)

        log("Registrando comandos...")
        game.commands(
                GeneratorCommands(
                        generatorManager,
                        setupMap
                )
        )

        log("Registrando listeners...")
        game.listeners(ModuleListener())
    }

    override fun disable() {
        log("Salvando geradores...")
        generatorManager.unloadAll(config)
    }

    override fun onSecPassed() {
        if (!GameSettings.canStart) return

        val stage = stageManager!!.actualStage
        if (stage.flags.contains(Flags.NO_GENERATOR_FLAG)) return

        generatorManager.all().forEach {
            if (it.time <= 0) {
                val world = it.block.world

                world.dropItem(
                        it.block.location.add(0.0, 1.0, 0.0),
                        ItemStack(it.type)
                )

                it.time = 10
            } else {
                it.time--
            }
        }
    }

    private inner class ModuleListener : Listener {
        @EventHandler
        fun onInteract(e: PlayerInteractEvent) {
            if (!setupMap.contains(e.player.name)) return
            e.isCancelled = true

            val block = e.clickedBlock
            generatorManager[setupMap[e.player.name]!!]!!.block = block

            e.player.sendMessage("§aBloco setado com sucesso!")
            setupMap.remove(e.player.name)
        }

    }
}