package io.github.notaeris.moon.rank;

import io.github.notaeris.moon.MoonPlugin;
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

    private final RankElement rankElement = MoonPlugin.getPlugin(MoonPlugin.class)
            .getElementHandler().findElement(RankElement.class);

    private final Set<String> permissions = new HashSet<>();

    /**
     * Constructor for creating a {@link Rank}
     *
     * @param rankName the name of the {@link Rank} being created
     */
    public Rank(String rankName, long creationDate) {
        this.name = rankName;
        this.displayName = rankName;
        this.creationDate = creationDate;

        this.rankElement.getRanks().add(this);
    }

    /**
     * Sort the {@link Rank}s by weight
     *
     * @param rankComparator the rankComparator
     */
    public void sort(Comparator<Rank> rankComparator) {
        this.rankElement.getRanks().sort(rankComparator);
    }

    /**
     * Delete a {@link Rank}
     *
     * @param rank the deleted rank
     */
    public void deleteRank(Rank rank) {
        this.rankElement.getRanks().remove(rank);
    }

    /**
     * Add a permission to a {@link Rank} by {@link String}
     *
     * @param permission the permission to add
     */
    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    /**
     * Remove a permission from a {@link Rank} by {@link String}
     *
     * @param permission the permission to remove
     */
    public void removePermission(String permission) {
        this.permissions.remove(permission);
    }
}
