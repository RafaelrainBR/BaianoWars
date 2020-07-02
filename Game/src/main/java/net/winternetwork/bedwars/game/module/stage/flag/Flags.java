package net.winternetwork.bedwars.game.module.stage.flag;

public class Flags {

    public static final NoJoinFlag NO_JOIN;
    public static final NoPvpFlag NO_PVP;
    public static final NoBreakFlag NO_BREAK;
    public static final NoBuildFlag NO_BUILD;
    public static final NoGeneratorFlag NO_GENERATOR;

    static {
        NO_JOIN = new NoJoinFlag();
        NO_PVP = new NoPvpFlag();
        NO_BREAK = new NoBreakFlag();
        NO_BUILD = new NoBuildFlag();
        NO_GENERATOR = new NoGeneratorFlag();
    }
}
