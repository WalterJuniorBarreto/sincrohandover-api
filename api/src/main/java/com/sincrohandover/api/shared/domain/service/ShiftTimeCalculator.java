package com.sincrohandover.api.shared.domain.service;

import com.sincrohandover.api.shared.domain.model.ShiftBoundaries;

import java.time.LocalTime;

/**
 * Puerto de entrada para cálculos temporales complejos.
 */
public interface ShiftTimeCalculator {
    ShiftBoundaries calculateUtcBoundaries(String timezoneStr, LocalTime workStart, LocalTime workEnd);
}



