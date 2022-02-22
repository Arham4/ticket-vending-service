package com.arhamjs.walmart_assessment.rules.compound;

import com.arhamjs.walmart_assessment.SeatingMap;

public final class AvailabilityRule implements CompoundingRule {
    public static AvailabilityRule create() {
        return new AvailabilityRule();
    }

    private AvailabilityRule() {
    }

    @Override
    public boolean abides(SeatingMap map, int row, int seat) {
        return map.hasAssignedAt(row, seat);
    }
}
