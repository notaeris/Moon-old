package io.github.notaeris.moon.profile;

import io.github.notaeris.moon.MoonPlugin;
import io.github.notaeris.moon.rank.Rank;
import io.github.notaeris.moon.rank.RankElement;
import io.github.notaeris.moon.rank.grant.Grant;
import io.github.notaeris.moon.tag.Tag;
import io.github.notaeris.moon.tag.grant.TagsGrant;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
public class Profile {

    private final UUID uuid;

    @Getter private static final Map<UUID, Profile> profileMap = new HashMap<>();

    private final RankElement rankElement = MoonPlugin.getPlugin(MoonPlugin.class)
            .getMoonBootstrap().getElementHandler()
            .findElement(RankElement.class);

    private final List<Grant> grants = new ArrayList<>();
    private final List<TagsGrant> tagsGrants = new ArrayList<>();

    private final Set<Profile> fly = new HashSet<>();

    /**
     * Constructor for creating a {@link Profile}
     *
     * @param uuid the uuid of the profile
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;

        profileMap.put(uuid, this);
        this.rankElement.getDefaultRank(this);
    }

    /**
     * Add a {@link Grant} to a {@link Profile} and sort them by weight
     *
     * @param grant the grant to add
     */
    public void addGrant(Grant grant) {
       this.grants.add(grant);
       this.grants.sort(Comparator.comparing(grant1 -> grant1.getRank().getWeight()));
       Collections.reverse(this.grants);
    }

    /**
     * Get a {@link Grant} from a {@link Profile}
     *
     * @return the grant found
     */
    public Rank getGrant() {
        return this.grants.get(0).getRank();
    }

    /**
     * Add a {@link TagsGrant} to a {@link Profile}
     *
     * @param tagsGrant the tagsGrant
     * @return the {@link TagsGrant} to add
     */
    public boolean addTag(TagsGrant tagsGrant) {
        this.tagsGrants.add(tagsGrant);
        return false;
    }

    public Tag getTagGrant() {
        return this.tagsGrants.get(0).getTag();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
