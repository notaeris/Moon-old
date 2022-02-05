package io.github.notaeris.moon.rank.grant.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.rank.grant.Grant;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrantsMenu extends PaginatedMenu {

    private final ProfileElement profileElement = MoonPlugin.getPlugin(MoonPlugin.class)
            .getMoonBootstrap().getElementHandler()
            .findElement(ProfileElement.class);

    private final Profile target;

    public GrantsMenu(Player player, Profile target) {
        super(player, "Viewing grants of " + target.getGrant().getColor() + target.getPlayer().getName(), 18);

        this.target = target;
    }

    @Override
    public void tick() {
        List<Grant> grants = new ArrayList<>(this.profileElement
                .findProfile(target.getPlayer().getUniqueId())
                .getGrants());

        for (int i = 0; i < grants.size(); i++) {
            Grant grant = grants.get(i);

            this.buttons[i] = new Button(Material.BOOK)
                    .setDisplayName(CC.translate("&8#" + grant.getUuid().toString().substring(0, 8)))
                    .setLore(this.getLore(grant))
                    .setClickAction(event -> event.setCancelled(true));
        }
    }

    private String[] getLore(Grant grant) {
        return new String[] {
                CC.translate("&a+ " + new Date(grant.getStartDate())),
                "",
                CC.translate("&f" + MoonConstants.PREFIX + " &eTarget&7: &f" + grant.getTarget().getGrant().getColor() + grant.getTarget().getPlayer().getName()),
                CC.translate("&f" + MoonConstants.PREFIX + " &eRank&7: &f" + grant.getRank().getDisplayName()),
                "",
                CC.translate("&f" + MoonConstants.PREFIX + " &eIssued By&7: &f" + grant.getExecutor()),
                CC.translate("&f" + MoonConstants.PREFIX + " &eIssued Reason&7: &f" + grant.getReason())
        };
    }
}
