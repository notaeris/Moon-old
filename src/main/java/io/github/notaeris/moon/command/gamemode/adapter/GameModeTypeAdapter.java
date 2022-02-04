package io.github.notaeris.moon.command.gamemode.adapter;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import org.bukkit.GameMode;

public class GameModeTypeAdapter implements TypeAdapter<GameMode> {

    @Override
    public GameMode convert(CommandExecutor commandExecutor, String source)  {
        return GameMode.valueOf(source.toUpperCase());
    }
}
