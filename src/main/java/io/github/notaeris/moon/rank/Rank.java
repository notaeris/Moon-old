package io.github.notaeris.moon.rank;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Rank {

    private final UUID uuid;
    private String name, displayname, prefix;
    private ChatColor color;
    private int weight;

    @Getter private static final List<Rank> ranks = new ArrayList<>();

    /**
     * Constructor for creating a {@link Rank}
     *
     * @param name the rank name
     */
    public Rank(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.displayname = name;
        this.prefix = "";
        this.color = ChatColor.WHITE;
        this.weight = 0;

        ranks.add(this);
    }

    /**
     * Delete a {@link Rank}
     *
     * @param rank the deleted rank
     */
    public void delete(Rank rank) {
        ranks.remove(rank);
    }
}
