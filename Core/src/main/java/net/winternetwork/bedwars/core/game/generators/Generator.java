package net.winternetwork.bedwars.core.game.generators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Material;
import org.bukkit.block.Block;

@Getter
@ToString
@RequiredArgsConstructor
public class Generator {

    private final String name;

    private final Material itemType;

    @Setter
    private Block block;

    @Setter
    private int time = 10;
}
