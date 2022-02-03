package io.github.notaeris.moon.command.rank;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.RankElement;
import io.github.notaeris.moon.rank.menu.RanksMenu;
import io.github.notaeris.moon.util.CC;
import org.bukkit.ChatColor;

public class RankCommands {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final RankElement rankElement = this.plugin
            .getMoonBootstrap().getElementHandler()
            .findElement(RankElement.class);

    private final String right_arrow = "✪";

    @Command(label = "rank", permission = "moon.command.rank")
    public void rank(BukkitCommandExecutor player) {
        this.plugin.getConfig().getStringList("command.rank.help")
                .forEach(string -> player.sendMessage(CC.translate(string
                        .replace("%right_arrow%", this.right_arrow))));
    }

    @Subcommand(parentLabel = "rank", label = "create", permission = "moon.command.rank")
    public void create(BukkitCommandExecutor player, String name) {
        if (this.rankElement.findRank(name) != null) {
            player.sendMessage(this.plugin.getConfig().getString("command.rank.already_exists"));
        } else {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.create"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%rank%", new Rank(name).getName()));
        }
    }

    @Subcommand(parentLabel = "rank", label = "delete", permission = "moon.command.rank")
    public void delete(BukkitCommandExecutor player, String name) {
        Rank rank = this.rankElement.findRank(name);

        if (rank == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
        } else {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.delete"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%displayName%", rank.getDisplayname()));

            rank.delete(rank);
        }
    }

    @Subcommand(parentLabel = "rank", label = "list", permission = "moon.command.rank", userOnly = true)
    public void list(BukkitCommandExecutor player) {
        new RanksMenu(player.getPlayer()).updateMenu();
    }

    @Subcommand(parentLabel = "rank", label = "displayname", permission = "moon.command.rank")
    public void displayName(BukkitCommandExecutor player, String rankName, String displayName) {
        Rank rank = this.rankElement.findRank(rankName);

        if (rank == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
        } else {
            rank.setDisplayname(displayName);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.displayname"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%rank%", rank.getName())
                    .replace("%displayName%", CC.translate(rank.getDisplayname())));
        }
    }

    @Subcommand(parentLabel = "rank", label = "prefix", permission = "moon.command.rank")
    public void prefix(BukkitCommandExecutor player, String rankName, String prefix) {
        Rank rank = this.rankElement.findRank(rankName);

        if (rank == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
        } else {
            rank.setPrefix(prefix);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.prefix"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%displayName%", CC.translate(rank.getDisplayname()))
                    .replace("%prefix%", CC.translate(rank.getPrefix())));
        }
    }

    @Subcommand(parentLabel = "rank", label = "color", permission = "moon.command.rank")
    public void color(BukkitCommandExecutor player, String rankName, ChatColor color) {
        Rank rank = this.rankElement.findRank(rankName);

        if (rank == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
        } else {
            rank.setColor(color);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.color"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%displayName%", CC.translate(rank.getDisplayname()))
                    .replace("%color%", CC.translate(rank.getColor() + rank.getColor().name().toUpperCase())));
        }
    }

    @Subcommand(parentLabel = "rank", label = "weight", permission = "moon.command.rank")
    public void weight(BukkitCommandExecutor player, String rankName, Integer weight) {
        Rank rank = this.rankElement.findRank(rankName);

        if (rank == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
        } else {
            rank.setWeight(weight);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.weight"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%displayName%", CC.translate(rank.getDisplayname()))
                    .replace("%weight%", Integer.toString(rank.getWeight())));
        }
    }

    @Subcommand(parentLabel = "rank", label = "addpermission", permission = "moon.command.rank")
    public void addPermission(BukkitCommandExecutor player, String rankName, String permission) {
        Rank rank = this.rankElement.findRank(rankName);

        if (rank == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
        } else {
            rank.addPermission(permission);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.permission.add"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%permission%", permission)
                    .replace("%rank%", CC.translate(rank.getDisplayname())));
        }
    }

    @Subcommand(parentLabel = "rank", label = "removepermission", permission = "moon.command.rank")
    public void removePermission(BukkitCommandExecutor player, String rankName, String permission) {
        Rank rank = this.rankElement.findRank(rankName);

        if (rank == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.doesnt_exist")));
        } else {
            rank.removePermission(permission);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.rank.permission.remove"))
                    .replace("%right_arrow%", this.right_arrow)
                    .replace("%permission%", permission)
                    .replace("%rank%", CC.translate(rank.getDisplayname())));
        }
    }
}
