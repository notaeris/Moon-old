package io.github.notaeris.moon.profile.listener;

import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.punishment.Punishment;
import io.github.notaeris.moon.punishment.PunishmentElement;
import io.github.notaeris.moon.punishment.type.PunishmentType;
import io.github.notaeris.moon.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Objects;

public class ProfileListener implements Listener {

    private final MoonPlugin moonPlugin = MoonPlugin.getPlugin(MoonPlugin.class);

    private final ProfileElement profileElement = this.moonPlugin
            .getElementHandler()
            .findElement(ProfileElement.class);

    private final PunishmentElement punishmentElement = this.moonPlugin
            .getElementHandler()
            .findElement(PunishmentElement.class);

    @Deprecated
    @EventHandler
    public void onPreJoin(PlayerPreLoginEvent event) {
        this.profileElement.getProfileMap().computeIfAbsent(event.getUniqueId(), Profile::new);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        try {
            Punishment punishment = this.punishmentElement.findPunishment(player.getUniqueId());

            if (punishment.findPunishmentType(PunishmentType.BAN).isActive()) {
                player.kickPlayer(punishment.getReason());
            }
        } catch (NullPointerException ignored) {
        }

        Profile profile = this.profileElement.findProfile(player.getUniqueId());
        PermissionAttachment permissionAttachment = player.addAttachment(this.moonPlugin);

        for (String permission : profile.getGrant().getPermissions()) {
            permissionAttachment.setPermission(permission, true);
        }

        player.recalculatePermissions();
        Bukkit.broadcast(CC.translate(this.moonPlugin.getConfig().getString("staff.join"))
                        .replace("%profile%", this.profileElement.findProfile(player.getUniqueId()).getGrant().getDisplayName())
                        .replace("%server%", this.moonPlugin.getServer().getName()), "moon.staff");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Bukkit.broadcast(CC.translate(this.moonPlugin.getConfig().getString("staff.quit"))
                        .replace("%profile%", this.profileElement.findProfile(event.getPlayer().getUniqueId()).getGrant().getDisplayName())
                        .replace("%server%", this.moonPlugin.getServer().getName()), "moon.staff");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Profile profile = this.profileElement.findProfile(player.getUniqueId());

        try {
            String tagHandler = Objects.equals(profile.getTagGrant().getPrefix(), "") ? "" : profile.getTagGrant().getPrefix();

            event.setFormat(CC.translate(tagHandler + " &f" + profile.getGrant().getPrefix()
                    + player.getName() + "&7: &f" + event.getMessage()));
        } catch (IndexOutOfBoundsException exception) {
            event.setFormat(CC.translate(profile.getGrant().getPrefix()
                    + player.getName() + "&7: &f" + event.getMessage()));
        }
    }
}
