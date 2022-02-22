package com.arhamjs.walmart_assessment;

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
        // todo make new map
        return Optional.empty();
    }

    public SeatingMap getMap() {
        return map;
    }
}
