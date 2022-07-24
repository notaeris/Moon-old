package io.github.notaeris.moon.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportCommands {

    private final MoonPlugin moonPlugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final ProfileElement profileElement = this.moonPlugin
            .getElementHandler()
            .findElement(ProfileElement.class);

    @Command(label = "teleport", aliases = { "tp" }, permission = "moon.command.teleport", userOnly = true)
    public void teleport(BukkitCommandExecutor player, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if (targetPlayer == null) {
            player.sendMessage(CC.translate(this.moonPlugin.getConfig().getString("player_doesnt_exist")));
        } else {
            player.getPlayer().teleport(targetPlayer);

            player.sendMessage(CC.translate(this.moonPlugin.getConfig().getString("command.teleport"))
                    .replace("%star%", MoonConstants.PREFIX)
                    .replace("%target%", this.profileElement
                            .findProfile(targetPlayer.getUniqueId()).getGrant().getColor() + targetPlayer.getName()));
        }
    }

    @Command(label = "teleporthere", aliases = { "tphere" }, permission = "moon.command.teleporthere", userOnly = true)
    public void teleportHere(BukkitCommandExecutor player, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if (targetPlayer == null) {
            player.sendMessage(CC.translate(this.moonPlugin.getConfig().getString("player_doesnt_exist")));
        } else {
            player.getPlayer().teleport(targetPlayer);

            player.sendMessage(CC.translate(this.moonPlugin.getConfig().getString("command.teleportHere"))
                    .replace("%star%", MoonConstants.PREFIX)
                    .replace("%target%", this.profileElement
                            .findProfile(targetPlayer.getUniqueId()).getGrant().getColor() + targetPlayer.getName())
                    .replace("%player%", this.profileElement
                            .findProfile(targetPlayer.getUniqueId()).getGrant().getColor() + targetPlayer.getName()));
        }
    }
}
