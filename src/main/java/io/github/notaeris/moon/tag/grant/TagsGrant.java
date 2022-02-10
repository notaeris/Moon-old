package io.github.notaeris.moon.tag.grant;

import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.tag.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagsGrant {

    private final Tag tag;
    private final Profile profile;
}
