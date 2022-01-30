package io.github.notaeris.moon.rank;

import io.github.notaeris.moon.element.Element;

public class RankElement implements Element {

    /**
     * Find a {@link Rank} by name
     *
     * @param name the rank name
     * @return the rank found
     */
    public Rank findRank(String name) {
        return Rank.getRanks().stream()
                .filter(rank -> rank.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
