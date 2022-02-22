package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;

import java.util.ArrayList;
import java.util.List;

public final class SatisfactionRule implements Rule {
    public static SatisfactionRule create() {
        return new SatisfactionRule();
    }

    private SatisfactionRule() {
    }

    @Override
    public List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map) {
        return new ArrayList<>();
    }

    @Override
    public boolean orderMatters() {
        return true;
    }
}
