package net.winternetwork.bedwars.core.game.stage;

import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.GameSettings;
import net.winternetwork.bedwars.core.game.flag.Flags;

import java.util.Arrays;
import java.util.List;

public class WaitingPlayerStage extends Stage {

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
                Flags.NO_BUILD
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

        if (online >= GameSettings.PLAYERS_TO_START) {
            StageManager.getInstance().callNextStage();
        } else {
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
