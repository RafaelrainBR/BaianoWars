package net.winternetwork.bedwars.core.game.stage;

import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.GameSettings;
import net.winternetwork.bedwars.core.game.flag.Flags;

import java.util.Arrays;
import java.util.List;

public class ToStartStage extends Stage {


    public ToStartStage() {
        super("Começando.", 20);
    }

    @Override
    public List<Flag> getFlags() {
        return Arrays.asList(
                Flags.NO_PVP
        );
    }

    @Override
    public void onStageJoin() {
    }

    @Override
    public void onSecondPassed() {

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

        StageManager.getInstance().callNextStage();
    }

    @Override
    public void onStageExit() {
        broadcast("Começou!");
    }
}
