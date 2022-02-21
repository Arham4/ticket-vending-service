package com.arhamjs.walmart_assessment.vendor;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.Theatre;
import com.arhamjs.walmart_assessment.rules.Rule;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;

public final class TicketVendor {
    public static TicketVendor with(Rule... rules) {
        return new TicketVendor(rules);
    }

    private Rule[] rules;

    private TicketVendor(Rule... rules) {
        this.rules = rules;
    }

    public Ticket vend(Theatre theatre, Request request) {
        SeatingMap currentSeatingMap = theatre.getMap();
        SeatingAssignment[] seatingAssignments = new SeatingAssignment[request.getSeatsRequested()];
        return theatre.createTicket(seatingAssignments);
    }
}
