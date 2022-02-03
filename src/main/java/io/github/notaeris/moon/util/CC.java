package io.github.notaeris.moon.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Color;

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

    /**
     * Convert a color to a byte
     *
     * @param color the color
     * @return the byte
     */
    public byte getColor(ChatColor color) {
        switch (color) {
            case GOLD:
                return 1;

            case BLUE:
                return 3;

            case YELLOW:
                return 4;

            case GREEN:
                return 5;

            case DARK_PURPLE:
            case LIGHT_PURPLE:
                return 2;

            case RED:
            case DARK_RED:
                return 14;

            case GRAY:
                return 8;

            case DARK_GRAY:
                return 7;

            case DARK_AQUA:
                return 9;

            case DARK_BLUE:
                return 11;

            default:
                return 0;
        }
    }
}
