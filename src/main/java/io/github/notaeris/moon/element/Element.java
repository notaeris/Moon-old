package io.github.notaeris.moon.element;

import io.github.nosequel.command.CommandHandler;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.command.gamemode.adapter.GameModeTypeAdapter;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.adapter.ProfileTypeAdapter;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.adapter.RankTypeAdapter;
import io.github.notaeris.moon.util.adapter.ChatColorTypeAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.Listener;

import java.util.Arrays;

public interface Element {

    /**
     * Load an {@link Element}
     *
     * @param element the element
     */
    default void load(Element element) {

    }

    /**
     * Unload an {@link Element}
     *
     * @param element the element
     */
    default void unload(Element element) {

    }

    /**
     * Find a {@link Element} by type class
     *
     * @param clazz the class
     * @param <T> the type of class
     * @return the type of element class
     */
    default <T> T findElement(Class<T> clazz) {
        return null;
    }

    /**
     * Initialise all listed {@link Element}s
     *
     * @param elements the elements
     */
    default void initialiseElements(Element... elements) {
        Arrays.stream(elements)
                .forEach(this::load);
    }

    /**
     * Initialise all listed listeners
     *
     * @param listeners the listeners
     */
    default void initialiseListeners(Listener... listeners) {
        Arrays.stream(listeners)
                .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, MoonPlugin.getPlugin(MoonPlugin.class)));
    }

    /**
     * Initialise all listed commands
     *
     * @param objects the commands
     */
    default void initialiseCommands(CommandHandler commandHandler, Object... objects) {
        commandHandler.registerTypeAdapter(ChatColor.class, new ChatColorTypeAdapter());
        commandHandler.registerTypeAdapter(Profile.class, new ProfileTypeAdapter());
        commandHandler.registerTypeAdapter(Rank.class, new RankTypeAdapter());
        commandHandler.registerTypeAdapter(GameMode.class, new GameModeTypeAdapter());

        Arrays.stream(objects)
                .forEach(commandHandler::registerCommand);
    }
}
