package io.github.notaeris.moon.rank;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.*;

@Getter
@Setter
public class Rank {

    private final UUID uuid = UUID.randomUUID();
    private String name, displayName;
    private String prefix = "";
    private ChatColor color = ChatColor.WHITE;
    private int weight = 0;
    private final long creationDate;

    @Getter private static final List<Rank> ranks = new ArrayList<>();

    private final Set<String> permissions = new HashSet<>();

    /**
     * Constructor for creating a {@link Rank}
     *
     * @param name the rank name
     */
    public Rank(String name, long creationDate) {
        this.name = name;
        this.displayName = name;
        this.creationDate = creationDate;

        ranks.add(this);
    }

    /**
     * Sort the {@link Rank}s by weight
     *
     * @param rankComparator the rankComparator
     */
    public void sort(Comparator<Rank> rankComparator) {
        ranks.sort(rankComparator);
    }

    /**
     * Delete a {@link Rank}
     *
     * @param rank the deleted rank
     */
    public void deleteRank(Rank rank) {
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
