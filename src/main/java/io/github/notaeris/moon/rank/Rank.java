package io.github.notaeris.moon.rank;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.*;

@Getter
@Setter
public class Rank {

    private final UUID uuid;
    private String name, displayname, prefix;
    private ChatColor color;
    private int weight;

    @Getter private static final List<Rank> ranks = new ArrayList<>();

    private final Set<String> permissions = new HashSet<>();

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

    /**
     * Add a permission to a {@link Rank}
     *
     * @param permission the permission to add
     */
    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    /**
     * Remove a permission from a {@link Rank}
     *
     * @param permission the permission to remove
     */
    public void removePermission(String permission) {
        this.permissions.remove(permission);
    }
}
