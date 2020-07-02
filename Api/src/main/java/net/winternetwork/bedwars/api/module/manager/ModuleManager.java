package net.winternetwork.bedwars.api.module.manager;

import net.winternetwork.bedwars.api.module.Module;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ModuleManager {

    protected final Map<Class<? extends Module>, Module>
            moduleMap = Collections.synchronizedMap(new HashMap<>());

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
