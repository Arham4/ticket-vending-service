package com.arhamjs.walmart_assessment.rules;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;

import java.util.List;

public interface Rule {
    List<SeatingAssignment> findViableSeatingAssignments(SeatingMap map);
    boolean orderMatters();
}
