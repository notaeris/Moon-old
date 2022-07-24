package io.github.notaeris.moon.element;

import io.github.nosequel.command.CommandHandler;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.command.gamemode.adapter.GameModeTypeAdapter;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.adapter.ProfileTypeAdapter;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.adapter.RankTypeAdapter;
import io.github.notaeris.moon.util.adapter.ChatColorTypeAdapter;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ElementHandler {

    private final Map<Class<? extends Element>, Element> elementMap = new HashMap<>();

    public void load(Element element) {
        this.elementMap.put(element.getClass(), element);
    }

    public <T> T findElement(Class<T> clazz) {
        return clazz.cast(elementMap.get(clazz));
    }

    /**
     * Initialise all {@link Element}s
     *
     * @param elements the elements
     */
    public void initialiseElements(Element... elements) {
        Arrays.stream(elements)
                .forEach(this::load);
    }

    /**
     * Initialise all {@link Listener}s
     *
     * @param listeners the listeners
     */
    public void initialiseListeners(Listener... listeners) {
        Arrays.stream(listeners)
                .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, MoonPlugin.getPlugin(MoonPlugin.class)));
    }


    /**
     * Initialise all listed commands using the {@link CommandHandler}
     *
     * @param objects the commands
     */
    public void initialiseCommands(CommandHandler commandHandler, Object... objects) {
        commandHandler.registerTypeAdapter(ChatColor.class, new ChatColorTypeAdapter());
        commandHandler.registerTypeAdapter(Profile.class, new ProfileTypeAdapter());
        commandHandler.registerTypeAdapter(Rank.class, new RankTypeAdapter());
        commandHandler.registerTypeAdapter(GameMode.class, new GameModeTypeAdapter());

        Arrays.stream(objects)
                .forEach(commandHandler::registerCommand);
    }
}
