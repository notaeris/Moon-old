package io.github.notaeris.moon.tag.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.profile.Profile;
import io.github.notaeris.moon.profile.ProfileElement;
import io.github.notaeris.moon.tag.Tag;
import io.github.notaeris.moon.tag.grant.TagsGrant;
import io.github.notaeris.moon.util.CC;
import io.github.notaeris.moon.util.MoonConstants;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Objects;

public class TagsMenu extends PaginatedMenu {

    private final ProfileElement profileElement = MoonPlugin.getPlugin(MoonPlugin.class)
            .getMoonBootstrap().getElementHandler()
            .findElement(ProfileElement.class);

    public TagsMenu(Player player) {
        super(player, CC.translate("&2Tags Menu"), 54);
    }

    @Override
    public void tick() {
        for (int i = 0; i < Tag.getTags().size(); i++) {
            Tag tag = Tag.getTags().get(i);
            Profile profile = this.profileElement.findProfile(this.getPlayer().getUniqueId());
            String tagHandler = Objects.equals(tag.getPrefix(), "") ? "None" : tag.getPrefix();

            this.buttons[36] = new Button(Material.SKULL_ITEM)
                    .setDisplayName(CC.translate("&6" + this.getPlayer().getName()))
                    .setLore(new String[] {
                            "&f" + MoonConstants.PREFIX + " &eTag Selected: &f" + tagHandler
                    });

            this.buttons[i] = new Button(Material.NAME_TAG)
                    .setDisplayName(CC.translate("&a" + tag.getName()))
                    .setLore(this.getLore(tag))
                    .setClickAction(event -> event.setCancelled(profile.addTag(new TagsGrant(tag, profile))));
        }
    }

    /**
     * Get the lore of a {@link Tag} in the {@link TagsMenu}
     *
     * @param tag the tag
     * @return the lore of the tag
     */
    private String[] getLore(Tag tag) {
        String tagHandler = Objects.equals(tag.getPrefix(), "") ? "None" : tag.getPrefix();
        String permission = Objects.equals(String.valueOf(tag.getPermissions()), "") ? "None" : String.valueOf(tag.getPermissions());

        return new String[] {
                CC.translate(StringUtils.repeat("&7&m-", 25)),
                CC.translate("&a+ " + new Date(System.currentTimeMillis())),
                "",
                CC.translate("&f" + MoonConstants.PREFIX + " &7Preview: &f" + tagHandler),
                CC.translate("&f" + MoonConstants.PREFIX + " &7Permissions: &f" + tag.getPermissions().size() + " " + permission),
                "",
                "&fClick to &aselect &ftag &a" + tag.getName(),
                CC.translate(StringUtils.repeat("&7&m-", 25))
        };
    }
}
