package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.SafetyRule;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SafetyTest {

    @Test
    public void Should_AssignGroups3SeatsAway_IfDifferentGroupsRequest() {
        SeatingMap map = SeatingMap.empty(10, 20);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SafetyRule.create());

        Ticket ticket = vendor.vend(theatre, Request.of("R001", 2));
        ticket = vendor.vend(theatre, Request.of("R002", 2));

        SeatingMap expected = SeatingMap.builder(10, 20)
                .markOccupied(0, 0)
                .markOccupied(0, 1)
                .markOccupied(0, 5)
                .markOccupied(0, 6)
                .build();

        Assertions.assertEquals(theatre.getMap(), expected);
    }

    @Test
    public void Should_AssignGroupInNewRow_IfRowFull() {
        SeatingMap map = SeatingMap.empty(10, 7);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SafetyRule.create());

        Ticket ticket = vendor.vend(theatre, Request.of("R001", 2));
        ticket = vendor.vend(theatre, Request.of("R002", 2));

        SeatingMap expected = SeatingMap.builder(10, 20)
                .markOccupied(0, 0)
                .markOccupied(0, 1)
                .markOccupied(0, 5)
                .markOccupied(0, 6)
                .markOccupied(1, 0)
                .build();

        Assertions.assertEquals(theatre.getMap(), expected);
    }
}
