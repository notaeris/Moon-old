package io.github.notaeris.moon.tag;

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

    private final Set<String> permissions = new HashSet<>();

    @Getter private static final List<Tag> tags = new ArrayList<>();

    /**
     * Constructor for creating a {@link Tag}
     *
     * @param name the name of the tag
     * @param creationDate the date the tag was created
     */
    public Tag(String name, long creationDate) {
        this.name = name;
        this.creationDate = creationDate;

        tags.add(this);
    }

    /**
     * Delete a {@link Tag}
     *
     * @param tag the tag to be deleted
     */
    public void deleteTag(Tag tag) {
        tags.remove(tag);
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
