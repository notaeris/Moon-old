package io.github.notaeris.moon.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.tag.Tag;
import io.github.notaeris.moon.tag.TagElement;
import io.github.notaeris.moon.tag.menu.TagsMenu;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;

public class TagCommands {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final TagElement tagElement = this.plugin
            .getMoonBootstrap().getElementHandler()
            .findElement(TagElement.class);

    @Command(label = "tag", permission = "moon.command.tag")
    public void tag(BukkitCommandExecutor player) {
        this.plugin.getConfig().getStringList("command.tag.help")
                .forEach(string -> player.sendMessage(CC.translate(string
                        .replace("%star%", MoonConstants.PREFIX))));
    }

    @Subcommand(parentLabel = "tag", label = "create", permission = "moon.command.tag")
    public void create(BukkitCommandExecutor player, String tag) {
        if (this.tagElement.findTag(tag) != null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.already_exists")));
        } else {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.create"))
                    .replace("%star%", MoonConstants.PREFIX)
                    .replace("%tag%", new Tag(tag, System.currentTimeMillis()).getName()));
        }
    }

    @Subcommand(parentLabel = "tag", label = "delete", permission = "moon.command.tag")
    public void delete(BukkitCommandExecutor player, String tagName) {
        Tag tag = this.tagElement.findTag(tagName);

        if (tag == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.doesnt_exist")));
        } else {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.delete"))
                    .replace("%star%", MoonConstants.PREFIX)
                    .replace("%tag%", tag.getName()));

            tag.deleteTag(tag);
        }
    }

    @Subcommand(parentLabel = "tag", label = "prefix", permission = "moon.command.tag")
    public void prefix(BukkitCommandExecutor player, String tagName, String prefix) {
        Tag tag = this.tagElement.findTag(tagName);

        if (tag == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.doesnt_exist")));
        } else {
            tag.setPrefix(prefix);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.prefix"))
                    .replace("%star%", MoonConstants.PREFIX)
                    .replace("%tag%", tag.getName())
                    .replace("%prefix%", tag.getPrefix()));
        }
    }

    @Subcommand(parentLabel = "tag", label = "addpermission", permission = "moon.command.tag")
    public void addPermission(BukkitCommandExecutor player, String tagName, String permission) {
        Tag tag = this.tagElement.findTag(tagName);

        if (tag == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.doesnt_exist")));
        } else {
            tag.addPermission(permission);

            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.permission.add"))
                    .replace("%star%", MoonConstants.PREFIX)
                    .replace("%tag%", tag.getName())
                    .replace("%permission%", permission));
        }
    }

    @Subcommand(parentLabel = "tag", label = "removepermission", permission = "moon.command.tag")
    public void removePermission(BukkitCommandExecutor player, String tagName, String permission) {
        Tag tag = this.tagElement.findTag(tagName);

        if (tag == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.doesnt_exist")));
        } else {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.tag.permission.remove"))
                    .replace("%star%", MoonConstants.PREFIX)
                    .replace("%tag%", tag.getName())
                    .replace("%permission%", permission));
        }
    }

    @Command(label = "tags", userOnly = true)
    public void tags(BukkitCommandExecutor player) {
        new TagsMenu(player.getPlayer()).updateMenu();
    }
}
