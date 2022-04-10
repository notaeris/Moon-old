package io.github.notaeris.moon.command.gamemode;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;
import org.bukkit.GameMode;

public class GameModeCommand {

    @Command(label = "gamemode", aliases = { "gm" }, permission = "moon.command.gamemode", userOnly = true)
    public void gameMode(BukkitCommandExecutor player, GameMode gameMode) {
        player.getPlayer().setGameMode(gameMode);

        player.sendMessage(CC.translate(MoonPlugin.getPlugin(MoonPlugin.class)
                .getConfig().getString("command.gamemode"))
                .replace("%star%", MoonConstants.PREFIX)
                .replace("%gameMode%", player.getPlayer().getGameMode().name().toUpperCase()));
    }
}
