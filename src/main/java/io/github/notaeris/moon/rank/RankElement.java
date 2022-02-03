package io.github.notaeris.moon.rank;

import io.github.notaeris.moon.element.Element;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.rank.grant.Grant;

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

    /**
     * Get the default rank for a {@link Profile}
     *
     * @param profile the profile
     */
    public void getDefaultRank(Profile profile) {
        if (this.findRank("Default") == null) {
            profile.addGrant(new Grant(profile, new Rank("Default")));
        }
    }
}
