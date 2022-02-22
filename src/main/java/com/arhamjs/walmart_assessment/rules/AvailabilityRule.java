package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.vendor.Request;

import java.util.ArrayList;
import java.util.List;

public final class AvailabilityRule implements SeatingRule, CompoundingRule {
    public static AvailabilityRule create() {
        return new AvailabilityRule();
    }

    private AvailabilityRule() {
    }

    @Override
    public boolean abides(SeatingMap map, int row, int seat) {
        return !map.hasAssignedAt(row, seat);
    }

    @Override
    public List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map, Request request) {
        List<SeatingAssignment> assignments = new ArrayList<>();
        for (int row = 0; row < map.getRows(); row++) {
            for (int seat = 0; seat < map.getSeats(); seat++) {
                if (abides(map, row, seat)) {
                    assignments.add(SeatingAssignment.of(row, seat));
                }
            }
        }
        return assignments;
    }

    @Override
    public boolean orderMatters() {
        return false;
    }
}
