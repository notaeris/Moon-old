package io.github.notaeris.moon.profile.listener;

import io.github.notaeris.moon.profile.Profile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {

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
}
