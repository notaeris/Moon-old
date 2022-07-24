package io.github.notaeris.moon.command.punishment.type;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.punishment.PunishmentElement;
import io.github.notaeris.moon.punishment.type.PunishmentType;
import io.github.notaeris.moon.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class UnbanCommand {

    private final MoonPlugin moonPlugin = MoonPlugin.getPlugin(MoonPlugin.class);

    @Command(label = "unban", permission = "moon.command.unban")
    public void unban(BukkitCommandExecutor player, OfflinePlayer target) {
        if (target == null) {
            player.sendMessage(CC.translate(this.moonPlugin.getConfig().getString("player_doesnt_exist")));
        } else {
            final PunishmentElement punishmentElement = this.moonPlugin.
                    getElementHandler().findElement(PunishmentElement.class);
            punishmentElement.findPunishment(target.getUniqueId())
                    .findPunishmentType(PunishmentType.BAN)
                    .setActive(false);

            if (player == null) {
                Bukkit.broadcastMessage(CC.translate(this.moonPlugin.getConfig().getString("command.punishment.type.unban"))
                        .replace("%playerExecuted%", target.getName())
                        .replace("%executor%", "&4&lCONSOLE"));
            } else {
                Bukkit.broadcastMessage(CC.translate(this.moonPlugin.getConfig().getString("command.punishment.type.unban"))
                        .replace("%playerExecuted%", target.getName())
                        .replace("%executor%", player.getPlayer().getName()));
            }
        }
    }
}
