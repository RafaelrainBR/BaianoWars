package net.winternetwork.bedwars.game.modules.shop.controller;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ShopController {

    private final Map<String, Villager> map = new LinkedHashMap<>();

    public Entity get(String id) {
        return map.get(id);
    }

    public void add(String id, Villager entity) {
        map.put(id, entity);
    }

    public void remove(String id) {
        map.remove(id);
    }

    public Collection<Villager> getAll() {
        return map.values();
    }

}
