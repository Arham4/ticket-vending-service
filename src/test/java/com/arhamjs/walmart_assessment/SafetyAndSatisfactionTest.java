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

public final class SafetyAndSatisfactionTest {
    @Test
    public void Should_GroupTowardsMiddle_AndRespectSafety() {
        final int rows = 10;
        SeatingMap map = SeatingMap.empty(rows, 20);
        Theatre theatre = Theatre.of(map);

        SafetyRule safetyRule = SafetyRule.builder()
                .distance(3)
                .rule(AvailabilityRule.create())
                .build();
        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(AvailabilityRule.create(), safetyRule), safetyRule);

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
}
