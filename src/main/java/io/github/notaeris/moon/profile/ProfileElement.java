package io.github.notaeris.moon.profile;

import io.github.notaeris.moon.element.Element;

import java.util.UUID;

public class ProfileElement implements Element {

    /**
     * Find a {@link Profile} by uuid
     *
     * @param uuid the uuid of the profile
     * @return the profile found
     */
    public Profile findProfile(UUID uuid) {
        return Profile.getProfileMap().get(uuid);
    }
}
