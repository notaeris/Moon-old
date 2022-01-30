package io.github.notaeris.moon.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class CC {

    /**
     * Translate a string
     *
     * @param string the string to be translated
     * @return the translated string
     */
    public String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
