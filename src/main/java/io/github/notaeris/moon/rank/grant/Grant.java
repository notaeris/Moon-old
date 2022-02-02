package io.github.notaeris.moon.rank.grant;

import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.rank.Rank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Grant {

    private final Profile profile;
    private final Rank rank;
}
