package br.redebaiana.baianowars.modules.generators.model

import com.gmail.filoghost.holographicdisplays.api.Hologram
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.configuration.ConfigurationSection

data class Generator(
    val name: String,
    val type: Material
) {

    lateinit var block: Block
    var time = 10

    lateinit var hologram: Hologram

    companion object {
        fun fromSection(section: ConfigurationSection): Generator {
            val generator = Generator(
                section.name,
                Material.matchMaterial(section.getString("drop_material"))
            )

            val block = section.getConfigurationSection("block")
            val world = Bukkit.getWorld(block.getString("world"))

            return generator.apply {
                this.block = world.getBlockAt(
                    block.getInt("x"),
                    block.getInt("y"),
                    block.getInt("z")
                )
            }
        }
    }
}