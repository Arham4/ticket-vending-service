package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;

import java.util.ArrayList;
import java.util.List;

import static com.arhamjs.walmart_assessment.util.CompoundingRuleUtils.abidesAll;

public final class SafetyRule implements SeatingRule {
    public static class SafetyRuleBuilder {
        int distance;
        final List<CompoundingRule> compoundingRules = new ArrayList<>();

        public SafetyRuleBuilder distance(int distance) {
            this.distance = distance;
            return this;
        }

        public SafetyRuleBuilder rule(CompoundingRule rule) {
            compoundingRules.add(rule);
            return this;
        }

        public SafetyRule build() {
            return new SafetyRule(distance, compoundingRules.toArray(new CompoundingRule[0]));
        }
    }

    public static SafetyRuleBuilder builder() {
        return new SafetyRuleBuilder();
    }

    private final int distance;
    private final CompoundingRule[] compoundingRules;

    private SafetyRule(int distance, CompoundingRule[] compoundingRules) {
        this.distance = distance;
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
        for (int i = 1; i <= distance; i++) {
            if (map.hasAssignedAt(row, seat - i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSafeFromRight(SeatingMap map, int row, int seat) {
        for (int i = 1; i <= distance; i++) {
            if (map.hasAssignedAt(row, seat + i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean orderMatters() {
        return false;
    }
}
