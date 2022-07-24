package io.github.notaeris.moon.tag;

import io.github.notaeris.moon.MoonPlugin;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Tag {

    private final String name;
    private final long creationDate;
    private String prefix = "";

    private final TagElement tagElement = MoonPlugin.getPlugin(MoonPlugin.class)
            .getElementHandler().findElement(TagElement.class);

    private final Set<String> permissions = new HashSet<>();

    /**
     * Constructor for creating a {@link Tag}
     *
     * @param name the name of the tag
     * @param creationDate the date the tag was created
     */
    public Tag(String name, long creationDate) {
        this.name = name;
        this.creationDate = creationDate;

        this.tagElement.getTags().add(this);
    }

    /**
     * Delete a {@link Tag}
     *
     * @param tag the tag to be deleted
     */
    public void deleteTag(Tag tag) {
        this.tagElement.getTags().remove(tag);
    }

    /**
     * Add a permission to a {@link Tag}
     *
     * @param permission the permission to add
     */
    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    /**
     * Remove a permission from a {@link Tag}
     *
     * @param permission the permission to remove
     */
    public void removePermission(String permission) {
        this.permissions.remove(permission);
    }
}
