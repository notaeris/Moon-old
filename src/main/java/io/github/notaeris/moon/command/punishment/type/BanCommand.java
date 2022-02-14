package io.github.notaeris.moon.command.punishment.type;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.punishment.Punishment;
import io.github.notaeris.moon.punishment.type.PunishmentType;
import io.github.notaeris.moon.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BanCommand {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    @Command(label = "ban", permission = "moon.command.ban")
    public void ban(BukkitCommandExecutor player, Player target, String reason) {
        if (target == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("player_doesnt_exist")));
        } else {
            if (player == null) {
                new Punishment(target.getUniqueId(), "&4&lCONSOLE", target.getName(), reason, PunishmentType.BAN, System.currentTimeMillis())
                        .setActive(true);

                Bukkit.broadcastMessage(CC.translate(this.plugin.getConfig().getString("command.punishment.type.ban"))
                        .replace("%playerExecuted%", target.getName())
                        .replace("%executor%", "&4&lCONSOLE"));
            } else {
                new Punishment(target.getUniqueId(), player.getPlayer().getName(), target.getName(), reason, PunishmentType.BAN, System.currentTimeMillis())
                        .setActive(true);

                Bukkit.broadcastMessage(CC.translate(this.plugin.getConfig().getString("command.punishment.type.ban"))
                        .replace("%playerExecuted%", target.getName())
                        .replace("%executor%", player.getPlayer().getName()));
            }

            target.kickPlayer(reason);
        }
    }
}
