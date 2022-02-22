package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.SafetyRule;
import com.arhamjs.walmart_assessment.rules.SatisfactionRule;
import com.arhamjs.walmart_assessment.rules.AvailabilityRule;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import com.arhamjs.walmart_assessment.vendor.Request;
import com.arhamjs.walmart_assessment.vendor.TicketVendor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public final class SafetySatisfactionAvailabilityTest {
    @Test
    public void Should_GroupTowardsMiddle_AndRespectSafety() {
        final int rows = 10;
        SeatingMap map = SeatingMap.empty(rows, 20);
        Theatre theatre = Theatre.of(map);

        AvailabilityRule availabilityRule = AvailabilityRule.create();
        SafetyRule safetyRule = SafetyRule.builder()
                .distance(3)
                .rule(availabilityRule)
                .build();
        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(availabilityRule, safetyRule), safetyRule, availabilityRule);

        vendor.vend(theatre, Request.of("R001", 2));
        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R002", 2));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(rows / 3, 5))
                .seat(SeatingAssignment.of(rows / 3, 6))
                .build();

        Assertions.assertEquals(expectedTicket, acquiredTicket);
    }

    @Test
    public void Should_GroupTowardsMiddle_AndRespectSafety_WithSeatsRequestedGreaterThanColumns() {
        final int rows = 10;
        SeatingMap map = SeatingMap.empty(rows, 7);
        Theatre theatre = Theatre.of(map);

        AvailabilityRule availabilityRule = AvailabilityRule.create();
        SafetyRule safetyRule = SafetyRule.builder()
                .distance(3)
                .rule(availabilityRule)
                .build();
        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(availabilityRule, safetyRule), safetyRule, availabilityRule);

        vendor.vend(theatre, Request.of("R001", 2));
        vendor.vend(theatre, Request.of("R002", 2));
        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R003", 9));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(rows / 3 + 1, 0))
                .seat(SeatingAssignment.of(rows / 3 + 1, 1))
                .seat(SeatingAssignment.of(rows / 3 + 1, 2))
                .seat(SeatingAssignment.of(rows / 3 + 1, 3))
                .seat(SeatingAssignment.of(rows / 3 + 1, 4))
                .seat(SeatingAssignment.of(rows / 3 + 1, 5))
                .seat(SeatingAssignment.of(rows / 3 + 1, 6))
                .seat(SeatingAssignment.of(rows / 3 + 2, 0))
                .seat(SeatingAssignment.of(rows / 3 + 2, 1))
                .build();

        Assertions.assertEquals(expectedTicket, acquiredTicket);
    }

    @Test
    public void Should_Have1InFront_Have1InMiddle_Have3InBack_If1ExistsInMiddleCenter_And4Request() {
        SeatingMap map = SeatingMap.builder(3, 3)
                .markOccupied(1, 1)
                .build();
        Theatre theatre = Theatre.of(map);

        AvailabilityRule availabilityRule = AvailabilityRule.create();
        SafetyRule safetyRule = SafetyRule.builder()
                .distance(3)
                .rule(availabilityRule)
                .build();
        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(availabilityRule, safetyRule), safetyRule, availabilityRule);
        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 4));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(2, 0))
                .seat(SeatingAssignment.of(2, 1))
                .seat(SeatingAssignment.of(2, 2))
                .seat(SeatingAssignment.of(0, 0))
                .build();

        Assertions.assertEquals(expectedTicket, acquiredTicket);
    }
}
