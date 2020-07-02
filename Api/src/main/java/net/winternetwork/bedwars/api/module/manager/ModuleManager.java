package net.winternetwork.bedwars.api.module.manager;

import com.google.common.collect.Maps;
import net.winternetwork.bedwars.api.module.Module;

import java.util.concurrent.ConcurrentMap;

public abstract class ModuleManager {

    protected final ConcurrentMap<Class<? extends Module>, Module> moduleMap = Maps.newConcurrentMap();

    public final void register(Class<? extends Module> clazz, Module module) {
        log("Registrando o módulo " + module.getName());
        moduleMap.put(clazz, module);
    }

    public final <T extends Module> T getRegistration(Class<T> clazz) {
        return (T) moduleMap.get(clazz);
    }

    public void log(String text) {
        System.out.println(
                String.format("[ModuleManager] %s", text)
        );
    }
}
