package io.github.notaeris.moon.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;

public class FlyCommand {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final ProfileElement profileElement = this.plugin
            .getElementHandler()
            .findElement(ProfileElement.class);

    @Command(label = "fly", aliases = { "flight" }, permission = "moon.command.fly", userOnly = true)
    public void fly(BukkitCommandExecutor player) {
        Profile profile = this.profileElement.findProfile(player.getPlayer().getUniqueId());

        if (!profile.getFly().contains(profile)) {
            profile.getFly().add(profile);
            player.getPlayer().setAllowFlight(true);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.fly.enabled"))
                    .replace("%star%", MoonConstants.PREFIX));
        } else {
            profile.getFly().remove(profile);
            player.getPlayer().setAllowFlight(false);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.fly.disabled"))
                    .replace("%star%", MoonConstants.PREFIX));
        }
    }
}
