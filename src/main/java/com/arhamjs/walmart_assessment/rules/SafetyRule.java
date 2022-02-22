package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;

import java.util.ArrayList;
import java.util.List;

public final class SafetyRule implements Rule {
    public static SafetyRule create() {
        return new SafetyRule();
    }

    private SafetyRule() {
    }

    @Override
    public List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map) {
        return new ArrayList<>();
    }

    @Override
    public boolean orderMatters() {
        return false;
    }
}
