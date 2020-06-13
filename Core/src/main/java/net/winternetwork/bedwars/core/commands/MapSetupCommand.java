package net.winternetwork.bedwars.core.commands;

import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import net.winternetwork.bedwars.core.game.map.GameMap;
import net.winternetwork.bedwars.core.game.map.MapManager;
import org.bukkit.Location;

public class MapSetupCommand {

    private final MapManager mapManager = MapManager.getInstance();

    @Command(
            name = "map",
            permission = "bedwars.command.map"
    )
    public void mapCommand(Execution execution) {
        execution.sendMessage(new String[]{
                "",
                "/map create <nome> - cria um mapa",
                "/map setlocation <nome> <number> - cria um mapa",
                ""
        });
    }

    @Command(
            name = "map.create",
            permission = "bedwars.command.map"
    )
    public void mapCreate(Execution execution, String name, int maxPlayers) {

        GameMap map = GameMap
                .builder()
                .name(name)
                .maxPlayers(maxPlayers)
                .lobbyLocation(execution.getPlayer().getLocation())
                .spawnLocations(new Location[maxPlayers])
                .build();

        execution.sendMessage(map.toString());

        mapManager.add(map);
    }

    @Command(
            name = "map.setlocation",
            permission = "bedwars.command.map",
            usage = "/map setlocation <name> <number>"
    )
    public void setLocation(Execution execution, String name, int number) throws Exception {
        GameMap map = mapManager.get(name);

        if (map == null) throw new Exception("não foi encontrado nenhum mapa com este nome");

        map.setLocation(number, execution.getPlayer().getLocation());

        execution.sendMessage("Location setada com sucesso!");
    }
}
