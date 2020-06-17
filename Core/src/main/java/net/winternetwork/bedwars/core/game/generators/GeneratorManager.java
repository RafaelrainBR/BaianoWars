package net.winternetwork.bedwars.core.game.generators;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GeneratorManager {

    @Getter(lazy = true)
    private static final GeneratorManager instance = new GeneratorManager();

    private final Map<String, Generator> CACHE = new LinkedHashMap<>();

    public Generator get(String name) {
        return CACHE.get(name);
    }

    public void put(Generator generator) {
        CACHE.put(generator.getName(), generator);
    }

    public void remove(String name) {
        CACHE.remove(name);
    }

    public Set<Generator> getAll() {
        return new LinkedHashSet<>(CACHE.values());
    }
}
