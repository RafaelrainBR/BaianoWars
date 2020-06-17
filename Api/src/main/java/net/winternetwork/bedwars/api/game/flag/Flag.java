package net.winternetwork.bedwars.api.game.flag;

import org.bukkit.event.Event;


public interface Flag<E extends Event> {

    Class<E> getEventClass();

    void execute(E event);

}
