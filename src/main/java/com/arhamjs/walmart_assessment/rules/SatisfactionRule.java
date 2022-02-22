package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.vendor.Request;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.arhamjs.walmart_assessment.util.CompoundingRuleUtils.abidesAll;

public final class SatisfactionRule implements SeatingRule {
    public static SatisfactionRule create() {
        return new SatisfactionRule(new CompoundingRule[]{});
    }

    public static SatisfactionRule with(CompoundingRule... rules) {
        return new SatisfactionRule(rules);
    }

    private final CompoundingRule[] rules;

    private SatisfactionRule(CompoundingRule[] rules) {
        this.rules = rules;
    }

    @Override
    public List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map, Request request) {
        Set<SeatingAssignment> result = new LinkedHashSet<>();

        result.addAll(findSeatsTogether(map, request));
        result.addAll(findSeatsFromMiddle(map));

        return new ArrayList<>(result);
    }

    private Set<SeatingAssignment> findSeatsTogether(SeatingMap map, Request request) {
        Set<SeatingAssignment> result = new LinkedHashSet<>();

        int topStartingIndex = map.getRows() / 3 * 2;
        int startingColumn = map.getSeats() / 2 - request.getSeatsRequested() / 2;

        for (int row = topStartingIndex; row < map.getRows(); row++) {
            addCenterSeatsTogether(result, map, request, row, startingColumn);
        }

        for (int row = topStartingIndex - 1; row >= 0; row--) {
            addCenterSeatsTogether(result, map, request, row, startingColumn);
        }

        return result;
    }

    private void addCenterSeatsTogether(Set<SeatingAssignment> result, SeatingMap map, Request request, int row, int startingColumn) {
        for (int offset = 0; offset < map.getSeats() / 2 + 2; offset++) {
            int left = startingColumn - offset;
            int right = startingColumn + offset;

            if (left >= 0) {
                for (int seatStart = left; seatStart < left + request.getSeatsRequested(); seatStart++) {
                    addImmediateSeatsIfAvailable(result, map, request, row, seatStart);
                }
            }

            if (right + request.getSeatsRequested() < map.getSeats()) {
                for (int seatStart = right; seatStart < right + request.getSeatsRequested(); seatStart++) {
                    addImmediateSeatsIfAvailable(result, map, request, row, seatStart);
                }
            }
        }
    }

    private void addImmediateSeatsIfAvailable(Set<SeatingAssignment> result, SeatingMap map, Request request, int row, int seatStart) {
        if (immediateSeatsAvailable(request.getSeatsRequested(), map, row, seatStart)) {
            for (int seat = seatStart; seat < seatStart + request.getSeatsRequested(); seat++) {
                result.add(SeatingAssignment.of(row, seat));
            }
        }
    }

    private boolean immediateSeatsAvailable(int amount, SeatingMap map, int row, int seat) {
        for (int nextSeat = seat; nextSeat < seat + amount; nextSeat++) {
            if (!abidesAll(rules, map, row, nextSeat)) {
                return false;
            }
        }
        return true;
    }

    private Set<SeatingAssignment> findSeatsFromMiddle(SeatingMap map) {
        Set<SeatingAssignment> result = new LinkedHashSet<>();

        int topStartingIndex = map.getRows() / 3 * 2;
        int startingColumn = map.getSeats() / 2;

        for (int row = topStartingIndex; row < map.getRows(); row++) {
            addCenterSeats(result, map, row, startingColumn);
        }

        for (int row = topStartingIndex - 1; row >= 0; row--) {
            addCenterSeats(result, map, row, startingColumn);
        }

        return result;
    }

    private void addCenterSeats(Set<SeatingAssignment> result, SeatingMap map, int row, int startingColumn) {
        for (int offset = 0; offset < map.getSeats() / 2 + 2; offset++) {
            int left = startingColumn - offset;
            int right = startingColumn + offset;

            if (left >= 0) {
                result.add(SeatingAssignment.of(row, left));
            }

            if (right < map.getSeats()) {
                result.add(SeatingAssignment.of(row, right));
            }
        }
    }

    @Override
    public boolean orderMatters() {
        return true;
    }
}
