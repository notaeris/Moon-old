package io.github.notaeris.moon.command.punishment;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.punishment.Punishment;
import io.github.notaeris.moon.punishment.PunishmentElement;
import io.github.notaeris.moon.punishment.menu.PunishmentsMenu;
import io.github.notaeris.moon.util.CC;
import org.bukkit.entity.Player;

public class PunishmentsCommand {

    private final MoonPlugin plugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final PunishmentElement punishmentElement = this.plugin
            .getMoonBootstrap().getElementHandler()
            .findElement(PunishmentElement.class);

    @Command(label = "punishments", aliases = { "history" }, permission = "moon.command.punishments")
    public void punishments(BukkitCommandExecutor player, Player target) {
        Punishment punishment = this.punishmentElement.findPunishment(target.getUniqueId());

        if (punishment == null) {
            player.sendMessage(CC.translate(this.plugin.getConfig().getString("command.punishment.couldnt_find")));
        } else {
            new PunishmentsMenu(player.getPlayer()).updateMenu();
        }
    }
}
