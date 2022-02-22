package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;

public final class SeatingMap {
    public static class SeatingMapBuilder {
        private boolean[][] map;

        private SeatingMapBuilder(int rows, int seats) {
            map = new boolean[rows][seats];
        }

        private SeatingMapBuilder(SeatingMap map) {
            this.map = map.map.clone();
        }

        public SeatingMapBuilder markOccupied(int row, int seat) {
            map[row][seat] = true;
            return this;
        }

        public SeatingMapBuilder markOccupied(SeatingAssignment assignment) {
            map[assignment.getRow()][assignment.getSeat()] = true;
            return this;
        }

        public SeatingMapBuilder markOccupied(SeatingAssignment[] assignments) {
            for (SeatingAssignment assignment : assignments) {
                markOccupied(assignment);
            }
            return this;
        }

        public SeatingMap build() {
            return new SeatingMap(map);
        }
    }

    public static SeatingMap empty(int rows, int seats) {
        boolean[][] map = new boolean[rows][seats];
        return new SeatingMap(map);
    }

    public static SeatingMapBuilder builder(int rows, int seats) {
        return new SeatingMapBuilder(rows, seats);
    }

    public static SeatingMapBuilder builder(SeatingMap map) {
        return new SeatingMapBuilder(map);
    }

    private final boolean[][] map;

    private SeatingMap(boolean[][] map) {
        this.map = map;
    }

    public boolean hasAssignedAt(int row, int seat) {
        if (row < 0 || row >= map.length || seat < 0 || seat >= map[row].length) {
            throw new IllegalArgumentException("Invalid position check");
        }
        return map[row][seat];
    }

    public int getRows() {
        return map.length;
    }

    public int getSeats() {
        return map[0].length;
    }
}
