package net.winternetwork.bedwars.core.game.stage;

import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.game.flag.Flags;

import java.util.Arrays;
import java.util.List;

public class GameStartedStage extends Stage {

    public GameStartedStage() {
        super("Jogo começou.", 60);
    }


    @Override
    public List<Flag> getFlags() {
        return Arrays.asList(
                Flags.NO_JOIN,
                Flags.NO_BUILD
        );
    }

    @Override
    public void onStageJoin() {
        broadcast("Jogo rolando e pvp ativado.");
    }

    @Override
    public void onSecondPassed() {

    }

    @Override
    public void onTimeLeft() {
        broadcast(
                String.format("§eTemos %d jogadores vivos.", getOnlinePlayers())
        );
        setTimeLeft(getTime());
    }

    @Override
    public void onStageExit() {

    }
}
