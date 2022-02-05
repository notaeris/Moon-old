package io.github.notaeris.moon.command.rank;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.RankElement;
import io.github.notaeris.moon.rank.grant.Grant;
import io.github.notaeris.moon.rank.grant.menu.GrantsMenu;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;
import org.bukkit.entity.Player;

public class GrantCommands {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final RankElement rankElement = this.plugin
            .getMoonBootstrap().getElementHandler()
            .findElement(RankElement.class);

    private final ProfileElement profileElement = this.plugin
            .getMoonBootstrap().getElementHandler()
            .findElement(ProfileElement.class);

    @Command(label = "grant", permission = "moon.command.grant", userOnly = true)
    public void grant(BukkitCommandExecutor player, Player target, String name) {
        Rank rank = this.rankElement.findRank(name);
        Profile targetProfile = this.profileElement.findProfile(target.getUniqueId());

        if (rank == null) {
            player.getPlayer().sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
            return;
        }

        targetProfile.addGrant(new Grant(rank, targetProfile, this.profileElement.findProfile(player.getPlayer().getUniqueId()).getPlayer().getName(), "lol", System.currentTimeMillis()));

        player.getPlayer().sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.grant"))
                .replace("%star%", MoonConstants.PREFIX)
                .replace("%rank%", CC.translate(rank.getDisplayName()))
                .replace("%profile%", this.profileElement.findProfile(player.getPlayer().getUniqueId()).getGrant().getColor() + player.getPlayer().getName()));
    }

    @Command(label = "grants", permission = "moon.command.grants", userOnly = true)
    public void grants(BukkitCommandExecutor player, Player target) {
        new GrantsMenu(player.getPlayer(), this.profileElement.findProfile(target.getUniqueId())).updateMenu();
    }
}
