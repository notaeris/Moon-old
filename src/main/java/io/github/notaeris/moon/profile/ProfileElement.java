package io.github.notaeris.moon.profile;

import io.github.notaeris.moon.element.Element;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileElement implements Element {

    @Getter private final Map<UUID, Profile> profileMap = new HashMap<>();

    /**
     * Find a {@link Profile} by the unique identifier
     *
     * @param uniqueId the unique identifier of the profile
     * @return the {@link Profile} found
     */
    public Profile findProfile(UUID uniqueId) {
        return this.profileMap.get(uniqueId);
    }
}
