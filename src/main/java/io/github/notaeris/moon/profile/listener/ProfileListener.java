package io.github.notaeris.moon.profile.listener;

import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.util.CC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {

    private final ProfileElement profileElement = MoonPlugin.getPlugin(MoonPlugin.class)
            .getMoonBootstrap().getElementHandler()
            .findElement(ProfileElement.class);

    @EventHandler
    public void onPreJoin(PlayerPreLoginEvent event) {
        Profile.getProfileMap().computeIfAbsent(event.getUniqueId(), Profile::new);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat(CC.translate(this.profileElement.findProfile(event.getPlayer().getUniqueId()).getGrant().getPrefix()
                + event.getPlayer().getName() + "&7: &f" + event.getMessage()));
    }
}
