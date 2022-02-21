package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;

public final class Theatre {
    public static Theatre of(SeatingMap map) {
        return new Theatre(map);
    }

    private SeatingMap map;

    private Theatre(SeatingMap map) {
        this.map = map;
    }

    public Ticket createTicket(SeatingAssignment[] assignments) {
        // todo make new map
        return null;
    }

    public SeatingMap getMap() {
        return map;
    }
}
