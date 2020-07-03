package net.winternetwork.bedwars.game.modules.stage.flag;

import net.winternetwork.bedwars.api.game.flag.Flag;
import org.bukkit.event.Event;

public class NoGeneratorFlag implements Flag<Event> {

    @Override
    public Class<Event> getEventClass() {
        return Event.class;
    }

    @Override
    public void execute(Event event) {

    }
}
