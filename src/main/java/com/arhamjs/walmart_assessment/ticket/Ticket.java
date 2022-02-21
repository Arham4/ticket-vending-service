package com.arhamjs.walmart_assessment.ticket;

import java.util.ArrayList;
import java.util.List;

public final class Ticket {
    public static class TicketBuilder {
        private final List<SeatingAssignment> assignments = new ArrayList<>();

        public TicketBuilder seat(SeatingAssignment assignment) {
            assignments.add(assignment);
            return this;
        }

        public Ticket build() {
            return new Ticket(assignments.toArray(new SeatingAssignment[0]));
        }
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public static Ticket of(SeatingAssignment[] assignments) {
        return new Ticket(assignments);
    }

    private final SeatingAssignment[] seatingAssignments;

    private Ticket(SeatingAssignment[] seatingAssignments) {
        this.seatingAssignments = seatingAssignments;
    }
}
