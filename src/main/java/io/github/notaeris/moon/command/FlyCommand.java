package io.github.notaeris.moon.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;

public class FlyCommand {

    private final MoonPlugin moonPlugin = MoonPlugin.getPlugin(MoonPlugin.class);

    @Command(label = "fly", aliases = { "flight" }, permission = "moon.command.fly", userOnly = true)
    public void fly(BukkitCommandExecutor player) {
        final ProfileElement profileElement = this.moonPlugin
                .getElementHandler().findElement(ProfileElement.class);
        Profile profile = profileElement.findProfile(player.getPlayer().getUniqueId());

        if (!profile.getFly().contains(profile)) {
            profile.getFly().add(profile);
            player.getPlayer().setAllowFlight(true);

            player.sendMessage(CC.translate(this.moonPlugin.getConfig().getString("command.fly.enabled"))
                    .replace("%star%", MoonConstants.PREFIX));
        } else {
            profile.getFly().remove(profile);
            player.getPlayer().setAllowFlight(false);

            player.sendMessage(CC.translate(this.moonPlugin.getConfig().getString("command.fly.disabled"))
                    .replace("%star%", MoonConstants.PREFIX));
        }
    }
}
