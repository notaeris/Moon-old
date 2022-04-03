package io.github.notaeris.moon;

import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.notaeris.moon.command.FlyCommand;
import io.github.notaeris.moon.command.MoonCommand;
import io.github.notaeris.moon.command.TagCommands;
import io.github.notaeris.moon.command.TeleportCommands;
import io.github.notaeris.moon.command.gamemode.GameModeCommand;
import io.github.notaeris.moon.command.punishment.PunishmentsCommand;
import io.github.notaeris.moon.command.punishment.type.BanCommand;
import io.github.notaeris.moon.command.punishment.type.KickCommand;
import io.github.notaeris.moon.command.punishment.type.UnbanCommand;
import io.github.notaeris.moon.command.rank.GrantCommands;
import io.github.notaeris.moon.command.rank.RankCommands;
import io.github.notaeris.moon.element.ElementHandler;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.profile.listener.ProfileListener;
import io.github.notaeris.moon.punishment.PunishmentElement;
import io.github.notaeris.moon.rank.RankElement;
import io.github.notaeris.moon.tag.TagElement;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class MoonPlugin extends JavaPlugin {

    private final ElementHandler elementHandler = new ElementHandler();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.initialiseElements();

        this.initialiseListeners();
        this.initialiseCommands();
    }

    private void initialiseElements() {
        this.elementHandler.initialiseElements(
                new ProfileElement(),
                new RankElement(),
                new TagElement(),
                new PunishmentElement()
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
                new GameModeCommand(),
                new TeleportCommands(),
                new FlyCommand(),
                new TagCommands(),
                new KickCommand(),
                new BanCommand(),
                new UnbanCommand(),
                new PunishmentsCommand()
        );
    }
}
