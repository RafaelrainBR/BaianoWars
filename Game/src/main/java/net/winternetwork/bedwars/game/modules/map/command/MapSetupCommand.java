package net.winternetwork.bedwars.game.modules.map.command;

import lombok.RequiredArgsConstructor;
import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import net.winternetwork.bedwars.game.modules.map.MapManager;
import net.winternetwork.bedwars.game.modules.map.object.GameMap;
import org.bukkit.Location;

@RequiredArgsConstructor
public class MapSetupCommand {

    private final MapManager mapManager;

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
            usage = "map setlocation <name> <number>"
    )
    public void setLocation(Execution execution, String name, int number) throws Exception {
        GameMap map = mapManager.get(name);

        if (map == null) throw new Exception("não foi encontrado nenhum mapa com este nome");

        if (number > map.getMaxPlayers()) {
            execution.sendMessage("§eEste mapa tem como limite %s jogadores.", map.getMaxPlayers());
            return;
        }

        map.setLocation(number, execution.getPlayer().getLocation());

        execution.sendMessage("Location setada com sucesso!");
    }
}
