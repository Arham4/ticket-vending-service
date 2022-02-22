package com.arhamjs.walmart_assessment.util;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.rules.CompoundingRule;

public final class CompoundingRuleUtils {
    private CompoundingRuleUtils() {
    }

    public static boolean abidesAll(CompoundingRule[] rules, SeatingMap map, int row, int seat) {
        for (CompoundingRule rule : rules) {
            if (!rule.abides(map, row, seat)) {
                return false;
            }
        }
        return true;
    }
}
