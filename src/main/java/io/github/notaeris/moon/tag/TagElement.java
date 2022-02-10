package io.github.notaeris.moon.tag;

import io.github.notaeris.moon.element.Element;

public class TagElement implements Element {

    /**
     * Find a {@link Tag} by name
     *
     * @param name the name of the tag
     * @return the tag found
     */
    public Tag findTag(String name) {
        return Tag.getTags().stream()
                .filter(tag -> tag.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
