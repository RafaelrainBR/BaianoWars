package net.winternetwork.bedwars.core.game.stage;

import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.score.ReplaceableList;
import net.winternetwork.bedwars.core.game.GameSettings;
import net.winternetwork.bedwars.core.game.flag.Flags;
import net.winternetwork.bedwars.core.game.map.GameMap;
import net.winternetwork.bedwars.core.game.map.MapManager;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ToStartStage extends Stage {


    public ToStartStage() {
        super("Começando.", 20);
    }

    private boolean started = false;

    @Override
    public List<Flag> getFlags() {
        return Arrays.asList(
                Flags.NO_PVP,
                Flags.NO_BUILD
        );
    }

    @Override
    public ReplaceableList getScoreboard() {
        return new ReplaceableList(
                Arrays.asList(
                        "&1",
                        "&fMapa: &a<mapa>",
                        "&fComeçando em: &a<next>",
                        "&2",
                        "&fJogadores: &a<online>/<max>",
                        "&3",
                        "&ewww.baianowars.kt"
                ),
                (s, player) -> s
                        .replaceAll("<mapa>", "Void")
                        .replaceAll("<online>", getOnlinePlayers() + "")
                        .replaceAll("<max>", "15")
                        .replaceAll("<next>", String.format("%ss", getTimeLeft()))
        );
    }

    @Override
    public void onStageJoin() {
    }

    @Override
    public void onSecondPassed() {

        if (getTimeLeft() <= 0) {
            StageManager.getInstance().callNextStage();
        }

        if (getTimeLeft() <= 5) {
            broadcast(
                    String.format("Começando o jogo em %ss.", getTimeLeft())
            );
        }
    }

    @Override
    public void onTimeLeft() {
        if (getOnlinePlayers() < GameSettings.PLAYERS_TO_START) {
            broadcast("§eAinda não tem jogadores suficientes para começar.");

            StageManager.getInstance().callPreviousStage();
            return;
        }

        started = true;
        StageManager.getInstance().callNextStage();
    }

    @Override
    public void onStageExit() {
        if (!started) return;
        broadcast("Começou!");

        GameMap map = MapManager.getInstance().getAll().get(0);

        System.out.println(map.toString());

        for (int i = 0; i < getAllPlayers().size(); i++) {
            Player player = getAllPlayers().get(i);

            player.teleport(map.getSpawnLocations()[i]);
        }
    }
}
