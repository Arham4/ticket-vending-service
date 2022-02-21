package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.Rule;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;

public final class TicketVendor {
    public static TicketVendor with(Rule... ensurers) {
        return new TicketVendor(ensurers);
    }

    private Rule[] ensurers;

    private TicketVendor(Rule... ensurers) {
        this.ensurers = ensurers;
    }

    public Ticket vend(Theatre theatre, Request request) {
        SeatingMap currentSeatingMap = theatre.getMap();
        SeatingAssignment[] seatingAssignments = new SeatingAssignment[request.getSeatsRequested()];
        return theatre.createTicket(seatingAssignments);
    }
}
