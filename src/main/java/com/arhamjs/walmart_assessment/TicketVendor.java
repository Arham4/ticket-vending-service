package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.ensurer.Ensurer;

public final class TicketVendor {
    public static TicketVendor with(Ensurer... ensurers) {
        return new TicketVendor(ensurers);
    }

    private Ensurer[] ensurers;

    private TicketVendor(Ensurer... ensurers) {
        this.ensurers = ensurers;
    }

    public SeatingMap vend(SeatingMap map, Request request) {
        return null;
    }
}
