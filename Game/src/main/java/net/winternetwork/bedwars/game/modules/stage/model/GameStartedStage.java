package net.winternetwork.bedwars.game.modules.stage.model;

import lombok.Getter;
import net.winternetwork.bedwars.api.game.flag.Flag;
import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.api.score.ReplaceableList;
import net.winternetwork.bedwars.game.modules.stage.flag.Flags;
import net.winternetwork.bedwars.game.net.winternetwork.bedwars.game.Game;

import java.util.Arrays;
import java.util.List;

public class GameStartedStage extends Stage {

    @Getter(lazy = true)
    private final Game game = Game.getGame();

    public GameStartedStage() {
        super("Jogo começou.", 60);
    }

    @Override
    public List<Flag> getFlags() {
        return Arrays.asList(
                Flags.NO_JOIN
        );
    }

    @Override
    public ReplaceableList getScoreboard() {
        return new ReplaceableList(
                Arrays.asList(
                        "&1",
                        "&fTempo de jogo: &e<time>",
                        "&fJogadores vivos: &a<online>",
                        "&2",
                        "&f Kills: &a<kills>",
                        "&f Camas dormidas: &c<beds>",
                        "&3",
                        "&ewww.baianowars.kt"
                ),
                (s, player) -> s
                        .replaceAll("<time>", getGame().getTimeElapsed() + "s")
                        .replaceAll("<online>", getOnlinePlayers() + "")
                        .replaceAll("<kills>", "0")
                        .replaceAll("<beds>", "0")
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
