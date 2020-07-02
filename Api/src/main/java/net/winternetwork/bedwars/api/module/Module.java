package net.winternetwork.bedwars.api.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Module {

    private final String name;
    private final ModulePriority priority;

    public void init() {
    }

    public void disable() {
    }

    public Runnable getScheduler() {
        return () -> {
        };
    }

    public void log(String text) {
        System.out.println(
                String.format("[%s] %s", getName(), text)
        );
    }

}
