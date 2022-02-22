package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.vendor.Request;

import java.util.List;

public interface SeatingRule {
    List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map, Request request);
    boolean orderMatters();
}
