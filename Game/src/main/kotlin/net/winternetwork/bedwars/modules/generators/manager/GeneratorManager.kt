package net.winternetwork.bedwars.modules.generators.manager

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.api.module.manager.Manager
import net.winternetwork.bedwars.modules.generators.model.Generator
import org.bukkit.Bukkit
import org.bukkit.entity.Item

class GeneratorManager(config: YamlConfig) : Manager<String, Generator>(config) {

    init {
        Bukkit.getWorlds().forEach { world ->
            world.getEntitiesByClass(Item::class.java).forEach {
                it.remove()
            }
        }
    }

    override fun loadAll() {
        val section = config.section("generators")

        val keys = section.getKeys(false)
        if (keys.isEmpty()) return

        keys.forEach {
            val generator = Generator.fromSection(section.getConfigurationSection(it))
            add(generator.name, generator)
        }
    }

    override fun unloadAll() {
        config.run {
            all {
                val prefix = "generators.${it.name}"

                set("${prefix}.drop_material", it.type.name)

                val block = it.block
                val blockPrefix = "${prefix}.block"

                set("${blockPrefix}.world", block.world.name)
                set("${blockPrefix}.x", block.x)
                set("${blockPrefix}.y", block.y)
                set("${blockPrefix}.z", block.z)
            }
            save()
        }
    }
}