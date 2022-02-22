package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.SeatingMap.SeatingMapBuilder;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;

import java.util.Optional;

public final class Theatre {
    public static Theatre of(SeatingMap map) {
        return new Theatre(map);
    }

    private SeatingMap map;

    private Theatre(SeatingMap map) {
        this.map = map;
    }

    public Optional<Ticket> createTicket(SeatingAssignment[] assignments) {
        map = SeatingMap.builder(map)
                .markOccupied(assignments)
                .build();

        return Optional.of(Ticket.of(assignments));
    }

    public SeatingMap getMap() {
        return map;
    }
}
