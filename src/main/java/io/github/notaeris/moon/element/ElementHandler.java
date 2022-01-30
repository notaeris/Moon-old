package io.github.notaeris.moon.element;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ElementHandler implements Element {

    private final Map<Class<? extends Element>, Element> elementMap = new HashMap<>();

    @Override
    public void load(Element element) {
        this.elementMap.put(element.getClass(), element);
    }

    @Override
    public <T> T findElement(Class<T> clazz) {
        return clazz.cast(elementMap.get(clazz));
    }
}
