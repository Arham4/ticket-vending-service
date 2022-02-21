package com.arhamjs.walmart_assessment;

public final class SeatingMap {
    public static class SeatingMapBuilder {
        private boolean[][] map;

        private SeatingMapBuilder(int rows, int seats) {
            map = new boolean[rows][seats];
        }

        public SeatingMapBuilder markOccupied(int row, int seat) {
            map[row][seat] = true;
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

    private final boolean[][] map;

    private SeatingMap(boolean[][] map) {
        this.map = map;
    }
}
