package io.github.notaeris.moon.tag;

import io.github.notaeris.moon.element.Element;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TagElement implements Element {

    @Getter private final List<Tag> tags = new ArrayList<>();

    /**
     * Find a {@link Tag} by {@link String} name
     *
     * @param name the tag name
     * @return the tag retrieved
     */
    public Tag findTag(String name) {
        return this.tags.stream()
                .filter(tag -> tag.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
