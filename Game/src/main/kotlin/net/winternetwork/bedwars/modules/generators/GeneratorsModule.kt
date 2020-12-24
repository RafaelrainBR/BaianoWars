package net.winternetwork.bedwars.modules.generators

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.module.Module
import net.winternetwork.bedwars.api.util.applyMeta
import net.winternetwork.bedwars.game.game
import net.winternetwork.bedwars.game.settings.GameSettings
import net.winternetwork.bedwars.modules.generators.command.GeneratorCommands
import net.winternetwork.bedwars.modules.generators.manager.GeneratorManager
import net.winternetwork.bedwars.modules.stage.StageModule
import net.winternetwork.bedwars.modules.stage.flag.Flags
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class GeneratorsModule(
    stageModule: StageModule
) : Module("Geradores") {

    private val stageManager = stageModule.stageManager

    val setupMap = hashMapOf<String, String>()

    private val config: YamlConfig by lazy {
        YamlConfig(
            game,
            dataFolder,
            "generators.yml"
        )
    }

    override val manager = GeneratorManager(config)

    override fun init() {
        log("Registrando comandos...")
        game.commands(
            GeneratorCommands(
                manager,
                setupMap
            )
        )

        log("Registrando listeners...")
        game.listeners(ModuleListener())
    }

    override fun onSecPassed() {
        if (!GameSettings.canStart) return

        val stage = stageManager!!.actualStage
        if (stage.flags.contains(Flags.NO_GENERATOR_FLAG)) return

        manager.all {
            if (it.time <= 0) {
                val world = it.block.world

                world.dropItem(
                    it.block.location.add(0.0, 1.0, 0.0),
                    ItemStack(it.type).applyMeta { item ->
                        item.displayName = it.name
                    }
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

            val player = e.player.name
            manager[setupMap[player]!!]!!.block = e.clickedBlock

            e.player.sendMessage("§aBloco setado com sucesso!")
            setupMap.remove(player)
        }

    }
}