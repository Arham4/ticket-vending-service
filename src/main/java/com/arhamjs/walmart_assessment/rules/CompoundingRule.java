package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;

public interface CompoundingRule {
    boolean abides(SeatingMap map, int row, int seat);
}
