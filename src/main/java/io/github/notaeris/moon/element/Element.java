package io.github.notaeris.moon.element;

public interface Element {

    /**
     * Load an {@link Element}
     *
     * @param element the element
     */
    default void load(Element element) {

    }

    /**
     * Unload an {@link Element}
     *
     * @param element the element
     */
    default void unload(Element element) {

    }
}
