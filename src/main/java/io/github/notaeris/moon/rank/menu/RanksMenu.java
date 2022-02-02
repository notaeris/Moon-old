package io.github.notaeris.moon.rank.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.ItemBuilder;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class RanksMenu extends PaginatedMenu {

    public RanksMenu(Player player) {
        super(player, "Ranks Menu", 27);
    }

    @Override
    public void tick() {
        for (int i = 0; i < Rank.getRanks().size(); i++) {
            AtomicInteger index = new AtomicInteger();

            Rank.getRanks().stream()
                    .sorted(Comparator.comparing(Rank::getWeight).reversed())
                    .map(rank -> new ItemBuilder(Material.INK_SACK)
                            .setName(CC.translate(rank.getDisplayname()))
                            .setLore(Arrays.asList(this.getLore(rank)))
                            .create())
                    .forEach(itemStack -> this.buttons[index.getAndIncrement()] = new Button(itemStack));
        }
    }

    private String[] getLore(Rank rank) {
        return new String[] {
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 25),
                CC.translate("&f✪ Date Created &90"),
                CC.translate("&f✪ Weight &9" + rank.getWeight()),
                "",
                CC.translate("   &9Display Information"),
                CC.translate("&f✪ Color " + rank.getColor() + rank.getColor().name().toUpperCase()),
                CC.translate("&f✪ Prefix " + rank.getPrefix()),
                CC.translate("&f✪ Display " + rank.getPrefix() + this.getPlayer().getName()),
                ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 25)
        };
    }
}
