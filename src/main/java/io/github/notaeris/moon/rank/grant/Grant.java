package io.github.notaeris.moon.rank.grant;

import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.rank.Rank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Grant {

    private final UUID uuid = UUID.randomUUID();
    private final Rank rank;
    private final Profile target;
    private final String executor, reason;
    private final long startDate;
}
