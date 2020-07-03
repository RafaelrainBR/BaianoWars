package net.winternetwork.bedwars.game.modules.shop.model;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;


public class VillagerModel {

    public static Villager spawnVillager(Location location) {
        World world = location.getWorld();

        Villager villager = ((Villager) world.spawnEntity(location, EntityType.VILLAGER));
        villager.setCustomNameVisible(true);
        villager.setCustomName("§e§lLOJA");

        setAi(((CraftVillager) villager), false);

        return villager;
    }

    private static void setAi(CraftEntity entity, boolean enabled) {
        Entity nmsEntity = entity.getHandle();

        NBTTagCompound tag = nmsEntity.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);

        int value = enabled ? 0 : 1;
        tag.setInt("NoAI", value);
        nmsEntity.f(tag);
    }
}
