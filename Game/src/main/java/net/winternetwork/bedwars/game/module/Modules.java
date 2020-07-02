package net.winternetwork.bedwars.game.module;

import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;
import net.winternetwork.bedwars.api.module.manager.ModuleManager;

public class Modules extends ModuleManager {

    @Getter(lazy = true)
    private static final Modules instance = new Modules();

    public static <T extends Module> T getModule(Class<T> clazz) {
        return getInstance().getRegistration(clazz);
    }
}
