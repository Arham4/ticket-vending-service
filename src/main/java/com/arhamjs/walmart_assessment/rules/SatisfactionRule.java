package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;

import java.util.ArrayList;
import java.util.List;

public final class SatisfactionRule implements SeatingRule {
    public static SatisfactionRule create() {
        return new SatisfactionRule();
    }

    private SatisfactionRule() {
    }

    @Override
    public List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map) {
        List<SeatingAssignment> result = new ArrayList<>();

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
