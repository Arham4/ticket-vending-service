package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.vendor.Request;
import com.google.common.base.Preconditions;

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

        int middleStartingIndex = map.getRows() / 3;
        for (int row = middleStartingIndex; row < map.getRows(); row++) {
            for (int seat = 0; seat < map.getSeats(); seat++) {
                if (immediateSeatsAvailable(request.getSeatsRequested(), map, row, seat)) {
                    result.add(SeatingAssignment.of(row, seat));
                }
            }
        }

        for (int row = middleStartingIndex - 1; row >= 0; row--) {
            for (int seat = 0; seat < map.getSeats(); seat++) {
                if (immediateSeatsAvailable(request.getSeatsRequested(), map, row, seat)) {
                    result.add(SeatingAssignment.of(row, seat));
                }
            }
        }

        return result;
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

        int middleStartingIndex = map.getRows() / 3;
        for (int row = middleStartingIndex; row < map.getRows(); row++) {
            for (int seat = 0; seat < map.getSeats(); seat++) {
                result.add(SeatingAssignment.of(row, seat));
            }
        }

        for (int row = middleStartingIndex - 1; row >= 0; row--) {
            for (int seat = 0; seat < map.getSeats(); seat++) {
                result.add(SeatingAssignment.of(row, seat));
            }
        }

        return result;
    }

    @Override
    public boolean orderMatters() {
        return true;
    }
}
