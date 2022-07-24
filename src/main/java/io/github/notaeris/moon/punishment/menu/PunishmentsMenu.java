package io.github.notaeris.moon.punishment.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.punishment.Punishment;
import io.github.notaeris.moon.punishment.PunishmentElement;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Date;

public class PunishmentsMenu extends PaginatedMenu {

    public PunishmentsMenu(Player player) {
        super(player, CC.translate("&cPunishments Menu"), 27);
    }

    @Override
    public void tick() {
        final PunishmentElement punishmentElement = MoonPlugin.getPlugin(MoonPlugin.class)
                .getElementHandler().findElement(PunishmentElement.class);

        for (int i = 0; i < punishmentElement.getPunishments().size(); i++) {
            Punishment punishment = punishmentElement.getPunishments().get(i);

            this.buttons[i] = new Button(Material.WOOL)
                    .setDisplayName(CC.translate("&8#" + punishment.getUuid().toString().substring(0, 8)))
                    .setLore(this.getLore(punishment))
                    .setData((byte) this.isActive(punishment))
                    .setClickAction(event -> event.setCancelled(true));
        }
    }

    private int isActive(Punishment punishment) {
        if (punishment.isActive()) {
            return 5;
        } else {
            return 14;
        }
    }

    private String[] getLore(Punishment punishment) {
        return new String[] {
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 25),
                CC.translate("&a+ " + new Date(punishment.getPunishmentDate())),
                "",
                CC.translate("&f" + MoonConstants.PREFIX + " &cTarget&7: &f" + punishment.getPlayerExecuted()),
                CC.translate("&f" + MoonConstants.PREFIX + " &cExecutor&7: &f" + punishment.getExecutor()),
                CC.translate("&f" + MoonConstants.PREFIX + " &cType&7: &f" + punishment.getType()),
                CC.translate("&f" + MoonConstants.PREFIX + " &cReason&7: &f" + punishment.getReason()),
                CC.translate("&f" + MoonConstants.PREFIX + " &cActive&7: &f" + punishment.isActive()),
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 25)
        };
    }
}
