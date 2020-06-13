package net.winternetwork.bedwars.core.game.flag;

public class Flags {

    public static final NoJoinFlag NO_JOIN;
    public static final NoPvpFlag NO_PVP;

    static {
        NO_JOIN = new NoJoinFlag();
        NO_PVP = new NoPvpFlag();
    }
}
