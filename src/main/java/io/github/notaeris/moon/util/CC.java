package io.github.notaeris.moon.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class CC {

    /**
     * Translate a {@link String}
     *
     * @param string the string to be translated
     * @return the translated string
     */
    public String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Convert a {@link ChatColor} to a {@link Byte}
     *
     * @param color the color
     * @return the byte
     */
    public byte getColor(ChatColor color) {
        switch (color) {
            case RED:
            case DARK_RED:
                return 1;

            case AQUA:
                return 12;

            case BLACK:
                return 0;

            case DARK_GREEN:
                return 2;

            case BLUE:
                return 4;

            case DARK_BLUE:
                return 6;

            case DARK_PURPLE:
                return 5;

            case GRAY:
                return 7;

            case DARK_GRAY:
                return 8;

            case LIGHT_PURPLE:
                return 9;

            case GREEN:
                return 10;

            case YELLOW:
                return 11;

            case GOLD:
                return 14;

            default:
                return 15;
        }
    }
}
