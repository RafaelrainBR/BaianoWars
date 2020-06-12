package net.winternetwork.bedwars.api.game.flag;

import org.bukkit.event.Event;

public interface Flag<T extends Event> {

    Class<T> getEventClass();

    void execute(T event);

}
