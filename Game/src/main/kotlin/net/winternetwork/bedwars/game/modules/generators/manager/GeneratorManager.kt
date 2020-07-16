package net.winternetwork.bedwars.game.modules.generators.manager

import net.winternetwork.bedwars.api.config.YamlConfig
import net.winternetwork.bedwars.game.modules.generators.model.Generator
import org.bukkit.Bukkit
import org.bukkit.entity.Item

class GeneratorManager {

    val generators = linkedMapOf<String, Generator>()

    init {
        Bukkit.getWorlds().forEach { world ->
            world.getEntitiesByClass(Item::class.java).forEach {
                it.remove()
            }
        }
    }

    fun loadAll(config: YamlConfig) {
        val section = config.section("generators")

        val keys = section.getKeys(false)
        if (keys.isEmpty()) return

        keys.forEach {
            add(Generator.fromSection(section.getConfigurationSection(it)))
        }
    }

    fun unloadAll(config: YamlConfig) {
        config.run {
            all().forEach {
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

    operator fun get(name: String) = generators[name]

    fun add(generator: Generator) = generators.put(generator.name, generator)

    fun remove(name: String) = generators.remove(name)

    fun all() = ArrayList(generators.values)
}