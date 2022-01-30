package io.github.notaeris.moon.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Help;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.RankElement;
import io.github.notaeris.moon.util.CC;

public class RankCommands {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final RankElement rankElement = this.plugin
            .getMoonBootstrap().getElementHandler()
            .findElement(RankElement.class);

    private final String right_arrow = "➤";

    @Help
    @Command(label = "rank", permission = "moon.command.rank")
    public void rank(BukkitCommandExecutor player) {
        this.plugin.getConfig().getStringList("command.rank.help")
                .forEach(string -> player.getPlayer().sendMessage(CC.translate(string)));
    }

    @Subcommand(parentLabel = "rank", label = "create", permission = "moon.command.rank")
    public void create(BukkitCommandExecutor player, String name) {
        if (this.rankElement.findRank(name) != null) {
            player.sendMessage(this.plugin.getConfig().getString("command.rank.already_exists"));
            return;
        }

        player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.create"))
                .replace("%right_arrow%", this.right_arrow)
                .replace("%rank%", new Rank(name).getName()));
    }

    @Subcommand(parentLabel = "rank", label = "delete", permission = "moon.command.rank")
    public void delete(BukkitCommandExecutor player, String name) {
        Rank rank = this.rankElement.findRank(name);

        if (rank == null) {
            player.getPlayer().sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
            return;
        }
        player.getPlayer().sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.delete"))
                .replace("%right_arrow%", this.right_arrow)
                .replace("%displayname%", rank.getDisplayname()));

        rank.delete(rank);
    }
}
