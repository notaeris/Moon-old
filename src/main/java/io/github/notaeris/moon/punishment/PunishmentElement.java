package io.github.notaeris.moon.punishment;

import io.github.notaeris.moon.element.Element;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PunishmentElement implements Element {

    @Getter private final List<Punishment> punishments = new ArrayList<>();

    /**
     * Find a {@link Punishment} by {@link UUID}
     *
     * @param uniqueId the unique identifier of the punishment
     * @return the punishment found
     */
    public Punishment findPunishment(UUID uniqueId) {
        return this.punishments.stream()
                .filter(punishment -> punishment.getUuid().equals(uniqueId))
                .findFirst().orElse(null);
    }
}
