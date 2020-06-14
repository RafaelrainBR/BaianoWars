package net.winternetwork.bedwars.api.score;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReplaceableList {

    private final List<String> list;
    private final BiFunction<String, Player, String> function;

    public List<String> call(Player player) {
        return list.stream()
                .map(it -> function.apply(it, player))
                .collect(Collectors.toList());
    }
}
