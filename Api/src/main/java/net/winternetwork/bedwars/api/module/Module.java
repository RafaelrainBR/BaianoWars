package net.winternetwork.bedwars.api.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@RequiredArgsConstructor
public abstract class Module {

    private final String name;
    private final ModulePriority priority;

    @Getter(lazy = true)
    private final Logger logger = LoggerFactory.getLogger(name);

    public void init() {
    }

    public void disable() {
    }

    public Runnable getScheduler() {
        return () -> {
        };
    }

}
