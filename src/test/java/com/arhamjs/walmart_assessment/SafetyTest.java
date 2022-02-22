package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.SafetyRule;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import com.arhamjs.walmart_assessment.vendor.Request;
import com.arhamjs.walmart_assessment.vendor.TicketVendor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public final class SafetyTest {

    @Test
    public void Should_AssignGroups3SeatsAway_IfDifferentGroupsRequest() {
        SeatingMap map = SeatingMap.empty(10, 20);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SafetyRule.create());

        vendor.vend(theatre, Request.of("R001", 2));
        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R002", 2));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(0, 5))
                .seat(SeatingAssignment.of(0, 6))
                .build();

        Assertions.assertEquals(acquiredTicket, expectedTicket);
    }

    @Test
    public void Should_AssignGroupInNewRow_IfRowFull() {
        SeatingMap map = SeatingMap.empty(10, 7);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SafetyRule.create());

        vendor.vend(theatre, Request.of("R001", 2));
        vendor.vend(theatre, Request.of("R002", 2));
        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R003", 1));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(1, 0))
                .build();

        Assertions.assertEquals(acquiredTicket, expectedTicket);
    }
}
