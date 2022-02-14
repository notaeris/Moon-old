package io.github.notaeris.moon.punishment;

import io.github.notaeris.moon.punishment.type.PunishmentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Punishment {

    private final UUID uuid;
    private final String executor, playerExecuted, reason;
    private final PunishmentType type;
    private final long punishmentDate;
    private boolean active;

    @Getter private final static List<Punishment> punishments = new ArrayList<>();

    {
        punishments.add(this);
    }

    /**
     * Find a {@link Punishment} by {@link PunishmentType}
     *
     * @param type the type of punishment
     * @return the type found
     */
    public Punishment findPunishmentType(PunishmentType type) {
        return Punishment.getPunishments().stream()
                .filter(punishment -> punishment.getType().equals(type))
                .findFirst().orElse(null);
    }
}
