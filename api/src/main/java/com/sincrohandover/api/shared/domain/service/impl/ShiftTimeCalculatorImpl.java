package com.sincrohandover.api.shared.domain.service.impl;

import com.sincrohandover.api.shared.domain.model.ShiftBoundaries;
import com.sincrohandover.api.shared.domain.service.ShiftTimeCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.*;

@Slf4j
@Component
public class ShiftTimeCalculatorImpl implements ShiftTimeCalculator {

    @Override
    public ShiftBoundaries calculateUtcBoundaries(String timezoneStr, LocalTime workStart, LocalTime workEnd){
        try {
            ZoneId zoneId = ZoneId.of(timezoneStr);
            ZonedDateTime nowLocal = ZonedDateTime.now(zoneId);
            ZonedDateTime startLocal = nowLocal.with(workStart);
            ZonedDateTime endLocal = nowLocal.with(workEnd);

            if(endLocal.isBefore(startLocal)){
                if(nowLocal.toLocalTime().isBefore(workEnd)){
                    startLocal = startLocal.minusDays(1);
                } else {
                    endLocal = endLocal.plusDays(1);
                }
            }

            log.debug("Calculo de turno completado para {}, Start UTC: {}, End UTC: {}", timezoneStr, startLocal.toInstant(), endLocal.toInstant());
            return new ShiftBoundaries(startLocal.toInstant(), endLocal.toInstant());
        } catch (DateTimeException e){
            log.error("Violacion detectada: Zona horaria invalida provista {}", timezoneStr);
            throw new IllegalArgumentException("La zona horaria proporcionada no es valida");
        }
    }

}



