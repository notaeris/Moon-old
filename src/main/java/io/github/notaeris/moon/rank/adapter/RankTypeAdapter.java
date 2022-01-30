package io.github.notaeris.moon.rank.adapter;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.notaeris.moon.rank.Rank;

public class RankTypeAdapter implements TypeAdapter<Rank> {

    @Override
    public Rank convert(CommandExecutor commandExecutor, String source) {
        return null;
    }
}
