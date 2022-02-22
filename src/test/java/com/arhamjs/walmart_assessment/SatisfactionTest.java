package com.arhamjs.walmart_assessment;

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
    public void Should_StartGroupingOnRow4_IfRowCountIs10() {
        SeatingMap map = SeatingMap.empty(10, 20);
        Theatre theatre = Theatre.of(map);

        TicketVendor vendor = TicketVendor.with(SatisfactionRule.create());

        Optional<Ticket> finalTicket = vendor.vend(theatre, Request.of("R001", 2));
        Assertions.assertTrue(finalTicket.isPresent());

        Ticket acquiredTicket = finalTicket.get();
        Ticket expectedTicket = Ticket.builder()
                .seat(SeatingAssignment.of(3, 0))
                .seat(SeatingAssignment.of(3, 1))
                .build();

        Assertions.assertEquals(expectedTicket, acquiredTicket);
    }
}