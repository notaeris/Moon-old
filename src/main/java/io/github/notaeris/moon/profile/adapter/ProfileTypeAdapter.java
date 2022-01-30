package io.github.notaeris.moon.profile.adapter;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.notaeris.moon.profile.Profile;

public class ProfileTypeAdapter implements TypeAdapter<Profile> {

    @Override
    public Profile convert(CommandExecutor commandExecutor, String source) {
        return null;
    }
}
