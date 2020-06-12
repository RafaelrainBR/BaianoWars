package net.winternetwork.bedwars.core.game.stage;

import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.Game;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitingPlayerStage extends Stage {

    private final int MIN_PLAYERS = 5;

    public WaitingPlayerStage() {
        super(
                "Esperando Jogadores.",
                30,
                TimeUnit.SECONDS
        );
    }

    @Override
    public List<Flag> getFlags() {
        return Arrays.asList(

        );
    }

    @Override
    public void onStageJoin() {
        final int online = getOnlinePlayers();

        broadcast(
                String.format("§eTemos %d jogadores online.", online),
                String.format("§ePara o jogo começar, precisamos de mais %d.", MIN_PLAYERS - online)
        );
    }

    @Override
    public void onSecondPassed() {
    }

    @Override
    public void onTimeLeft() {
        final int online = getOnlinePlayers();

        if (online >= MIN_PLAYERS) {
            Game.callNextStage();
        } else {
            // Resetar o tempo.
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
