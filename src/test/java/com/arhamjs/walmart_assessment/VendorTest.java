package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.AvailabilityRule;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import com.arhamjs.walmart_assessment.vendor.Request;
import com.arhamjs.walmart_assessment.vendor.TicketVendor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public final class VendorTest {
    @Test
    public void Should_ProvideTicket_IfRequestIdentifierNotProcessed() {
        SeatingMap map = SeatingMap.empty(10, 20);
        Theatre theatre = Theatre.of(map);

        Request request = Request.of("R001", 1);
        TicketVendor vendor = TicketVendor.with(AvailabilityRule.create());

        Optional<Ticket> finalTicket = vendor.vend(theatre, request);

        Assertions.assertTrue(finalTicket.isPresent());
    }

    @Test
    public void Should_ProvideEmptyTicket_IfRequestIdentifierAlreadyProcessed() {
        SeatingMap map = SeatingMap.empty(10, 20);
        Theatre theatre = Theatre.of(map);

        Request request = Request.of("R001", 1);
        TicketVendor vendor = TicketVendor.with(AvailabilityRule.create());

        vendor.vend(theatre, request);
        Optional<Ticket> finalTicket = vendor.vend(theatre, request);

        Assertions.assertTrue(finalTicket.isEmpty());
    }

    @Test
    public void Should_ProvideEmptyTicket_IfDifferentRequestWithSameIdentifier() {
        SeatingMap map = SeatingMap.empty(10, 20);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(AvailabilityRule.create());

        vendor.vend(theatre, Request.of("R001", 1));
        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 5));

        Assertions.assertTrue(finalTicket.isEmpty());
    }
}
