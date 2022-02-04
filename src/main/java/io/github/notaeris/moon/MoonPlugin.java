package io.github.notaeris.moon;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class MoonPlugin extends JavaPlugin {

    private final MoonBootstrap moonBootstrap = new MoonBootstrap();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.moonBootstrap.initialise();
    }
}
