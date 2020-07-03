package net.winternetwork.bedwars.game.modules.map.object.adapter;

import net.winternetwork.bedwars.api.storage.adapter.Adapter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter implements Adapter<Location, String> {

    @Override
    public Location from(String s) {
        String[] split = s.split(";");
        return new Location(
                Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3])
        );
    }

    @Override
    public String to(Location loc) {
        List<String> text = new ArrayList<>();

        text.add(loc.getWorld().getName());
        text.add(String.valueOf(loc.getX()));
        text.add(String.valueOf(loc.getY()));
        text.add(String.valueOf(loc.getZ()));

        return String.join(";", text);
    }
}
