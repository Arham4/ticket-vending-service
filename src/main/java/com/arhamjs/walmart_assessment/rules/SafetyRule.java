package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.rules.compound.CompoundingRule;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.util.CompoundingRuleUtils;

import java.util.ArrayList;
import java.util.List;

import static com.arhamjs.walmart_assessment.util.CompoundingRuleUtils.abidesAll;

public final class SafetyRule implements Rule {

    public static SafetyRule with(CompoundingRule... compoundingRule) {
        return new SafetyRule(compoundingRule);
    }

    private final CompoundingRule[] compoundingRules;

    private SafetyRule(CompoundingRule[] compoundingRules) {
        this.compoundingRules = compoundingRules;
    }

    @Override
    public List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map) {
        List<SeatingAssignment> result = new ArrayList<>();
        for (int row = 0; row < map.getRows(); row++) {
            for (int seat = 0; seat < map.getSeats(); seat++) {
                if (!abidesAll(compoundingRules, map, row, seat)) {
                    continue;
                }

                if (isSafeFromLeft(map, row, seat) && isSafeFromRight(map, row, seat)) {
                    result.add(SeatingAssignment.of(row, seat));
                }
            }
        }
        return result;
    }

    private boolean isSafeFromLeft(SeatingMap map, int row, int seat) {
        return seat <= 2 || !map.hasAssignedAt(row, seat - 3);
    }

    private boolean isSafeFromRight(SeatingMap map, int row, int seat) {
        return seat >= map.getSeats() - 3 || !map.hasAssignedAt(row, seat + 3);
    }

    @Override
    public boolean orderMatters() {
        return false;
    }
}
