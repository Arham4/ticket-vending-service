package com.arhamjs.walmart_assessment.ticket;

public final class Ticket {
    private final SeatingAssignment[] seatingAssignments;

    private Ticket(SeatingAssignment[] seatingAssignments) {
        this.seatingAssignments = seatingAssignments;
    }
}
