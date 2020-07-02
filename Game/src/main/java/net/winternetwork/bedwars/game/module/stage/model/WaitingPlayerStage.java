package net.winternetwork.bedwars.game.module.stage.model;

import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.score.ReplaceableList;
import net.winternetwork.bedwars.game.module.Modules;
import net.winternetwork.bedwars.game.module.stage.StageManager;
import net.winternetwork.bedwars.game.module.stage.StageModule;
import net.winternetwork.bedwars.game.module.stage.flag.Flags;
import net.winternetwork.bedwars.game.settings.GameSettings;

import java.util.Arrays;
import java.util.List;

public class WaitingPlayerStage extends Stage {

    private final StageManager stageManager = Modules.getModule(StageModule.class).getStageManager();

    public WaitingPlayerStage() {
        super(
                "Esperando Jogadores.",
                20
        );
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
                        "&2",
                        "&fAguardando...",
                        "&fJogadores: &a<online>/<max>",
                        "&3",
                        "&ewww.baianowars.kt"
                ),
                (s, player) -> s
                        .replaceAll("<waiting>", String.valueOf(GameSettings.PLAYERS_TO_START - getOnlinePlayers()))
                        .replaceAll("<mapa>", "Gadolandia")
                        .replaceAll("<online>", getOnlinePlayers() + "")
                        .replaceAll("<max>", "15")
        );
    }

    @Override
    public void onStageJoin() {
        final int online = getOnlinePlayers();

        broadcast(
                String.format("§eTemos %d jogador%s online.", online, online > 1 ? "es" : ""),
                String.format(
                        "§ePara o jogo começar, precisamos de mais %d.",
                        GameSettings.PLAYERS_TO_START - online
                )
        );
    }

    @Override
    public void onSecondPassed() {
    }

    @Override
    public void onTimeLeft() {
        final int online = getOnlinePlayers();

        if (online >= GameSettings.PLAYERS_TO_START)
            stageManager.callNextStage();
        else {
            setTimeLeft(getTime());
            onStageJoin();
        }
    }

    @Override
    public void onStageExit() {
        broadcast(
                "§eTemos jogadores suficientes.",
                String.format("§eIniciando o jogo em %d segundos.", getTime())
        );
    }
}
