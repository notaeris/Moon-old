package io.github.notaeris.moon.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.util.CC;

public class MoonCommand {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    @Command(label = "moon")
    public void moon(BukkitCommandExecutor player) {
        player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.moon"))
                .replace("%name%", this.plugin.getDescription().getName())
                .replace("%version%", this.plugin.getDescription().getVersion()));
    }
}
