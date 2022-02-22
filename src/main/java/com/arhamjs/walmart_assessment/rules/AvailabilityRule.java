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
        return new ArrayList<>();
    }

    @Override
    public boolean orderMatters() {
        return false;
    }
}
