package io.github.notaeris.moon.profile;

import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.grant.Grant;
import lombok.Getter;

import java.util.*;

@Getter
public class Profile {

    private final UUID uuid;

    @Getter private static final Map<UUID, Profile> profileMap = new HashMap<>();
    private final List<Grant> grants = new ArrayList<>();

    /**
     * Constructor for creating a {@link Profile}
     *
     * @param uuid the uuid of the profile
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;

        profileMap.put(uuid, this);
    }

    /**
     * Add a grant
     *
     * @param grant the grant {@link Grant}
     */
    public void addGrant(Grant grant) {
        grants.add(grant);
    }

    /**
     * Get a grant from a player
     *
     * @return the grant found
     */
    public Rank getGrant() {
        return grants.get(0).getRank();
    }
}
