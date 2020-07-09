package net.winternetwork.bedwars.game.modules.shop.model;

import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class VillagerModel {

    @SneakyThrows
    public static Villager spawnVillager(Location location) {
        World world = location.getWorld();

        Villager villager = ((Villager) world.spawnEntity(location, EntityType.VILLAGER));
        villager.setCustomNameVisible(true);
        villager.setCustomName("§e§lLOJA");

        setAi(villager, false);

        return villager;
    }

    private static void setAi(Entity entity, boolean enabled) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Method handleMethod = entity.getClass().getMethod("getHandle");
        Object nmsEntity = handleMethod.invoke(entity);

        Method tagMethod = nmsEntity.getClass().getMethod("getNBTTag");
        Object tag = tagMethod.invoke(nmsEntity);

        if (tag == null)
            tag = Class.forName("net.minecraft.server.v1_8_R3.NBTTagCompound").newInstance();

        Method cMethod = nmsEntity.getClass().getMethod("c");
        cMethod.invoke(nmsEntity, tag);

        Method setIntMethod = tag.getClass().getMethod("setInt");
        int value = enabled ? 0 : 1;
        setIntMethod.invoke(tag, "NoAI", value);

        Method fMethod = nmsEntity.getClass().getMethod("f");
        fMethod.invoke(nmsEntity, tag);
    }
}
