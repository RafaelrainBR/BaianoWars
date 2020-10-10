package net.winternetwork.bedwars.modules.shop.model

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Villager

object VillagerModel {

    fun spawnVillager(location: Location): Villager {
        return (location.world.spawnEntity(location, EntityType.VILLAGER) as Villager)
                .apply {
                    isCustomNameVisible = true
                    customName = "§e§lLOJA"

                    setAi(false)
                }
    }

    private fun Entity.setAi(enabled: Boolean) {
        val handleMethod = javaClass.getMethod("getHandle")
        val entity = handleMethod.invoke(this)

        val tagMethod = entity.javaClass.getMethod("getNBTTag")
        val tag = tagMethod.invoke(entity)
                ?: Class.forName("net.minecraft.server.v1_8_R3.NBTTagCompound")
                        .newInstance()

        entity.javaClass.getMethod("c")
                .invoke(entity, tag)

        val value = if (enabled) 0 else 1
        tag.javaClass.getMethod("setInt")
                .invoke(tag, "NoAI", value)

        entity.javaClass.getMethod("f")
                .invoke(entity, tag)
    }
}