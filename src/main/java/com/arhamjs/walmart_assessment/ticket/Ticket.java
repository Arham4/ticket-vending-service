package com.arhamjs.walmart_assessment.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Ticket {
    public static class TicketBuilder {
        private String requestIdentifier;
        private final List<SeatingAssignment> assignments = new ArrayList<>();

        public TicketBuilder requestIdentifier(String requestIdentifier) {
            this.requestIdentifier = requestIdentifier;
            return this;
        }

        public TicketBuilder seat(SeatingAssignment assignment) {
            assignments.add(assignment);
            return this;
        }

        public Ticket build() {
            return new Ticket(requestIdentifier, assignments.toArray(new SeatingAssignment[0]));
        }
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public static Ticket of(String requestIdentifier, SeatingAssignment[] assignments) {
        return new Ticket(requestIdentifier, assignments);
    }

    private final String requestIdentifier;
    private final SeatingAssignment[] seatingAssignments;

    private Ticket(String requestIdentifier, SeatingAssignment[] seatingAssignments) {
        this.requestIdentifier = requestIdentifier;
        this.seatingAssignments = seatingAssignments;
    }

    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Arrays.equals(seatingAssignments, ticket.seatingAssignments);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(seatingAssignments);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ticket{");
        sb.append("requestIdentifier=").append(requestIdentifier).append(",");
        sb.append("seatingAssignments=").append(Arrays.toString(seatingAssignments));
        sb.append('}');
        return sb.toString();
    }
}
