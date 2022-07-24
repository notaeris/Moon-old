package io.github.notaeris.moon.rank;

import io.github.notaeris.moon.element.Element;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.rank.grant.Grant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RankElement implements Element {

    @Getter private final List<Rank> ranks = new ArrayList<>();

    /**
     * Find a {@link Rank} by name
     *
     * @param rankName the rank name
     * @return the {@link Rank} retrieved
     */
    public Rank findRank(String rankName) {
        return this.ranks.stream()
                .filter(rank -> rank.getName().equalsIgnoreCase(rankName))
                .findFirst().orElse(null);
    }

    /**
     * Get the default {@link Rank} for a {@link Profile}
     *
     * @param profile the profile
     */
    public void getDefaultRank(Profile profile) {
        if (this.findRank("Default") == null) {
            profile.addGrant(new Grant(new Rank("Default", System.currentTimeMillis()), profile, "&4&lCONSOLE", "First time joining", System.currentTimeMillis()));
        } else {
            profile.addGrant(new Grant(this.findRank("Default"), profile, "&4&LCONSOLE", "First time joining", System.currentTimeMillis()));
        }
    }
}
