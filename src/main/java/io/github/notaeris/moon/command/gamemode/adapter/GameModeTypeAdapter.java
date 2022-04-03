package io.github.notaeris.moon.command.gamemode.adapter;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import org.bukkit.GameMode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameModeTypeAdapter implements TypeAdapter<GameMode> {

    private final Map<String, GameMode> gamemodeMap = new HashMap<String, GameMode>() {{
        for (String key : Arrays.asList("s", "0", "survival")) {
            this.put(key, GameMode.SURVIVAL);
        }

        for (String key : Arrays.asList("c", "1", "creative")) {
            this.put(key, GameMode.CREATIVE);
        }

        for (String key : Arrays.asList("a", "2", "adventure")) {
            this.put(key, GameMode.ADVENTURE);
        }

        for (String key : Arrays.asList("sp", "3", "spectator")) {
            this.put(key, GameMode.SPECTATOR);
        }
    }};

    @Override
    public GameMode convert(CommandExecutor commandExecutor, String source)  {
        return gamemodeMap.get(source);
    }
}
