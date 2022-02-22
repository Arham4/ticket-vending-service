package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.AvailabilityRule;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import com.arhamjs.walmart_assessment.vendor.Request;
import com.arhamjs.walmart_assessment.vendor.TicketVendor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public final class AvailabilityTest {
    @Test
    public void Should_ProvideEmptyTicket_IfTheatreIsFull() {
        SeatingMap map = SeatingMap.builder(1, 1)
                .markOccupied(0, 0)
                .build();
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(AvailabilityRule.create());

        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 1));

        Assertions.assertTrue(finalTicket.isEmpty());
    }

    @Test
    public void Should_ProvideEmptyTicket_IfRequestExceedsTheatre() {
        SeatingMap map = SeatingMap.builder(1, 3)
                .markOccupied(0, 0)
                .build();
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(AvailabilityRule.create());

        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 3));

        Assertions.assertTrue(finalTicket.isEmpty());
    }

    @Test
    public void Should_ProvideTicket_IfSpotAvailable() {
        SeatingMap map = SeatingMap.empty(1, 1);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(AvailabilityRule.create());

        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 1));

        Assertions.assertTrue(finalTicket.isPresent());
    }
}
