package net.winternetwork.bedwars.game.modules;

import lombok.Getter;
import net.winternetwork.bedwars.api.module.Module;

import java.util.List;

public class ModuleManager {

    @Getter(lazy = true)
    private static final ModuleManager instance = new ModuleManager();

    private final Modules modules = Modules.getInstance();

    public void addAll(List<Module> moduleList) {
        for (Module module : moduleList) {
            modules.register(module.getClass(), module);

            module.init();
        }
    }

    public void disableAll() {
        modules.getAll().forEach(Module::disable);
    }
}
