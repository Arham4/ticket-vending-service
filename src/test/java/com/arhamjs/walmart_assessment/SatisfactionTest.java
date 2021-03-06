package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.AvailabilityRule;
import com.arhamjs.walmart_assessment.rules.SatisfactionRule;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import com.arhamjs.walmart_assessment.vendor.Request;
import com.arhamjs.walmart_assessment.vendor.TicketVendor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public final class SatisfactionTest {
    @Test
    public void Should_PreferTopRows() {
        final int rows = 10;
        SeatingMap map = SeatingMap.empty(rows, 20);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(AvailabilityRule.create()));

        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 2));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(rows / 3 * 2, 9))
                .seat(SeatingAssignment.of(rows / 3 * 2, 10))
                .build();

        Assertions.assertEquals(expectedTicket, acquiredTicket);
    }

    @Test
    public void Should_PreferSeatingTogether_IfAvailableTowardsTop() {
        final int rows = 10;
        SeatingMap map = SeatingMap.builder(rows, 20)
                .markOccupied(rows / 3, 9)
                .build();
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(AvailabilityRule.create()));

        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 2));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(rows / 3 * 2, 9))
                .seat(SeatingAssignment.of(rows / 3 * 2, 10))
                .build();

        Assertions.assertEquals(expectedTicket, acquiredTicket);
    }

    @Test
    public void Should_PreferTopRow_IfSeatsRequestedGreaterThanColumns() {
        final int rows = 10;
        SeatingMap map = SeatingMap.empty(rows, 3);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(AvailabilityRule.create()));

        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 5));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(rows / 3 * 2, 1))
                .seat(SeatingAssignment.of(rows / 3 * 2, 0))
                .seat(SeatingAssignment.of(rows / 3 * 2, 2))
                .seat(SeatingAssignment.of(rows / 3 * 2 + 1, 1))
                .seat(SeatingAssignment.of(rows / 3 * 2 + 1, 0))
                .build();

        Assertions.assertEquals(expectedTicket, acquiredTicket);
    }
}
