package net.winternetwork.bedwars.game.module.stage.model;

import lombok.Getter;
import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.score.ReplaceableList;
import net.winternetwork.bedwars.game.module.Modules;
import net.winternetwork.bedwars.game.module.map.MapModule;
import net.winternetwork.bedwars.game.module.map.object.GameMap;
import net.winternetwork.bedwars.game.module.stage.StageManager;
import net.winternetwork.bedwars.game.module.stage.StageModule;
import net.winternetwork.bedwars.game.module.stage.flag.Flags;
import net.winternetwork.bedwars.game.settings.GameSettings;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ToStartStage extends Stage {

    @Getter(lazy = true)
    private final StageManager stageManager = Modules.getModule(StageModule.class).getStageManager();
    private boolean started = false;

    public ToStartStage() {
        super("Começando.", 20);
    }

    @Override
    public List<Flag> getFlags() {
        return Arrays.asList(
                Flags.NO_PVP,
                Flags.NO_BREAK,
                Flags.NO_BUILD,
                Flags.NO_GENERATOR
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
                        .replaceAll("<mapa>", "Gadolandia")
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
            getStageManager().callNextStage();
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

            getStageManager().callPreviousStage();
            return;
        }

        started = true;
        getStageManager().callNextStage();
    }

    @Override
    public void onStageExit() {
        if (!started) return;
        broadcast("Começou!");

        GameMap map = Modules.getModule(MapModule.class).getMapManager().getAll().get(0);

        System.out.println(map.toString());

        for (int i = 0; i < getAllPlayers().size(); i++) {
            Player player = getAllPlayers().get(i);

            player.teleport(map.getSpawnLocations()[i]);
        }
    }
}
