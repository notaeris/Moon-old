package io.github.notaeris.moon.profile;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;

    @Getter private static final Map<UUID, Profile> profileMap = new HashMap<>();

    /**
     * Constructor for creating a {@link Profile}
     *
     * @param uuid the uuid of the profile
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;

        profileMap.put(uuid, this);
    }
}
