package com.arhamjs.walmart_assessment.ticket;

import java.util.Objects;

public final class SeatingAssignment implements Comparable<SeatingAssignment> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatingAssignment that = (SeatingAssignment) o;
        return row == that.row && seat == that.seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SeatingAssignment{");
        sb.append("row=").append(row);
        sb.append(", seat=").append(seat);
        sb.append('}');
        return sb.toString();
    }

    public String toOutputString() {
        return rowAsciiRepresentation() + seat;
    }

    private String rowAsciiRepresentation() {
        return ((char) ('A' + row)) + "";
    }

    @Override
    public int compareTo(SeatingAssignment o) {
        if (row == o.row) {
            return Integer.compare(seat, o.seat);
        }
        return Integer.compare(row, o.row);
    }
}
