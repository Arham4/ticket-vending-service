package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.ensurer.SafetyEnsurer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SafetyTest {

    @Test
    public void Should_AssignGroups3SeatsAway_IfDifferentGroupsRequest() {
        SeatingMap map = SeatingMap.empty(10, 20);
        TicketVendor vendor = TicketVendor.with(SafetyEnsurer.empty());

        SeatingMap newMap = vendor.vend(map, Request.of("R001", 2));
        newMap = vendor.vend(newMap, Request.of("R002", 2));

        SeatingMap expected = SeatingMap.builder(10, 20)
                .markOccupied(0, 0)
                .markOccupied(0, 1)
                .markOccupied(0, 5)
                .markOccupied(0, 6)
                .build();

        Assertions.assertEquals(newMap, expected);
    }

    @Test
    public void Should_AssignGroupInNewRow_IfRowFull() {
        SeatingMap map = SeatingMap.builder(10, 7)
                .markOccupied(0, 0)
                .markOccupied(0, 1)
                .markOccupied(0, 5)
                .markOccupied(0, 6)
                .build();
        TicketVendor vendor = TicketVendor.with(SafetyEnsurer.empty());

        SeatingMap newMap = vendor.vend(map, Request.of("R001", 1));

        SeatingMap expected = SeatingMap.builder(10, 7)
                .markOccupied(0, 0)
                .markOccupied(0, 1)
                .markOccupied(0, 5)
                .markOccupied(0, 6)
                .markOccupied(1, 0)
                .build();

        Assertions.assertEquals(newMap, expected);
    }
}
