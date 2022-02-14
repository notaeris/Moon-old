package io.github.notaeris.moon.punishment;

import io.github.notaeris.moon.element.Element;

import java.util.UUID;

public class PunishmentElement implements Element {

    /**
     * Find a {@link Punishment} by {@link UUID}
     *
     * @param uuid the uuid of the punishment
     * @return the punishment found
     */
    public Punishment findPunishment(UUID uuid) {
        return Punishment.getPunishments().stream()
                .filter(punishment -> punishment.getUuid().equals(uuid))
                .findFirst().orElse(null);
    }
}
