package com.sincrohandover.api.shared.domain.model;

import java.time.Instant;

/**
 * Record inmutable que transporta los límites temporales absolutos (UTC) de un turno.
 */
public record ShiftBoundaries(
        Instant startInstant,
        Instant endInstant
) {
}

