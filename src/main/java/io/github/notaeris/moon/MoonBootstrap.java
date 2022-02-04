package io.github.notaeris.moon;

import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.notaeris.moon.command.gamemode.GameModeCommand;
import io.github.notaeris.moon.command.MoonCommand;
import io.github.notaeris.moon.command.rank.GrantCommands;
import io.github.notaeris.moon.command.rank.RankCommands;
import io.github.notaeris.moon.element.ElementHandler;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.profile.listener.ProfileListener;
import io.github.notaeris.moon.rank.RankElement;
import lombok.Getter;

@Getter
public class MoonBootstrap {

    private final ElementHandler elementHandler = new ElementHandler();

    public void initialise() {
        this.initialiseElements();

        this.initialiseListeners();
        this.initialiseCommands();
    }

    private void initialiseElements() {
        this.elementHandler.initialiseElements(
                new ProfileElement(),
                new RankElement()
        );
    }

    private void initialiseListeners() {
        this.elementHandler.initialiseListeners(
                new ProfileListener()
        );
    }

    private void initialiseCommands() {
        this.elementHandler.initialiseCommands(new BukkitCommandHandler("moon"),
                new MoonCommand(),
                new RankCommands(),
                new GrantCommands(),
                new GameModeCommand()
        );
    }
}
