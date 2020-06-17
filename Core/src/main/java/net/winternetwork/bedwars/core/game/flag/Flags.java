package net.winternetwork.bedwars.core.game.flag;

public class Flags {

    public static final NoJoinFlag NO_JOIN;
    public static final NoPvpFlag NO_PVP;
    public static final NoBreakFlag NO_BREAK;
    public static final NoBuildFlag NO_BUILD;

    static {
        NO_JOIN = new NoJoinFlag();
        NO_PVP = new NoPvpFlag();
        NO_BREAK = new NoBreakFlag();
        NO_BUILD = new NoBuildFlag();
    }
}
