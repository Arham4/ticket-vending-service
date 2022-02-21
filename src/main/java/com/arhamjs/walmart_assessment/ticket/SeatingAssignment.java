package com.arhamjs.walmart_assessment.ticket;

public final class SeatingAssignment {
    public static SeatingAssignment of(int row, int seat) {
        return new SeatingAssignment(row, seat);
    }

    private final int row;
    private final int seat;

    private SeatingAssignment(int row, int seat) {
        this.row = row;
        this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }
}
