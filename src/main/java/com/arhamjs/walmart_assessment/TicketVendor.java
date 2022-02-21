package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.rules.Rule;

public final class TicketVendor {
    public static TicketVendor with(Rule... ensurers) {
        return new TicketVendor(ensurers);
    }

    private Rule[] ensurers;

    private TicketVendor(Rule... ensurers) {
        this.ensurers = ensurers;
    }

    public SeatingMap vend(SeatingMap map, Request request) {
        return null;
    }
}
