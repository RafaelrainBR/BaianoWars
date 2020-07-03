package net.winternetwork.bedwars.game.modules;

import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.manager.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class Modules extends ModuleManager {

    @Getter(lazy = true)
    private static final Modules instance = new Modules();

    public static <T extends Module> T getModule(Class<T> clazz) {
        return getInstance().getRegistration(clazz);
    }

    public List<Module> getAll() {
        return new ArrayList<>(moduleMap.values());
    }
}
