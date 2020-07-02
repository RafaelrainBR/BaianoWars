package net.winternetwork.bedwars.game.module;

import lombok.Getter;

public class ModuleManager {

    @Getter(lazy = true)
    private final ModuleManager instance = new ModuleManager();

    private final Modules modules = Modules.getInstance();
}
