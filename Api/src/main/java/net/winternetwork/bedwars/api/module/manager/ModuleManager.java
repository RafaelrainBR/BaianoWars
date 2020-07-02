package net.winternetwork.bedwars.api.module.manager;

import com.google.common.collect.Maps;
import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;

public abstract class ModuleManager {

    @Getter
    private final Logger logger = LoggerFactory.getLogger("ModuleManager");

    private final ConcurrentMap<Class<? extends Module>, Module> moduleMap = Maps.newConcurrentMap();

    public final <T extends Module> void register(Class<T> clazz, T module) {
        logger.info("Registrando o módulo " + module.getName());
        moduleMap.put(clazz, module);
    }

    public final <T extends Module> T getRegistration(Class<T> clazz) {
        return (T) moduleMap.get(clazz);
    }
}
