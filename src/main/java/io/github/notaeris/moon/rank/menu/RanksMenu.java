package io.github.notaeris.moon.rank.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.RankElement;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Objects;

public class RanksMenu extends PaginatedMenu {

    public RanksMenu(Player player) {
        super(player, "Ranks Menu", 27);
    }

    @Override
    public void tick() {
        final RankElement rankElement = MoonPlugin.getPlugin(MoonPlugin.class)
                .getElementHandler().findElement(RankElement.class);

        for (int i = 0; i < rankElement.getRanks().size(); i++) {
            Rank rank = rankElement.getRanks().get(i);

            this.buttons[i] = new Button(Material.INK_SACK)
                    .setDisplayName(CC.translate(rank.getDisplayName()))
                    .setLore(this.getLore(rank))
                    .setData(CC.getColor(rank.getColor()))
                    .setClickAction(event -> event.setCancelled(true));
        }
    }

    private String[] getLore(Rank rank) {
        String prefixHandler = Objects.equals(rank.getPrefix(), "") ? "None" : rank.getPrefix();

        return new String[] {
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 25),
                CC.translate("&a+ " + new Date(rank.getCreationDate())),
                "",
                CC.translate("&f" + MoonConstants.PREFIX + " &eWeight&7: &f" + rank.getWeight()),
                CC.translate("&f" + MoonConstants.PREFIX + " &eColor&7: &f" + rank.getColor() + rank.getColor().name().toUpperCase()),
                CC.translate("&f"+ MoonConstants.PREFIX + " &ePrefix&7: &f" + prefixHandler),
                "",
                CC.translate("&f✦ &eDisplay&7: &f" + rank.getPrefix() + this.getPlayer().getName()),
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 25)
        };
    }
}
