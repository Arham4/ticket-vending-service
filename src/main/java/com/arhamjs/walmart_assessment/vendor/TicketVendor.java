package com.arhamjs.walmart_assessment.vendor;

import com.arhamjs.walmart_assessment.SeatingMap;
import com.arhamjs.walmart_assessment.Theatre;
import com.arhamjs.walmart_assessment.rules.SeatingRule;
import com.arhamjs.walmart_assessment.ticket.SeatingAssignment;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.Collectors;

import static com.arhamjs.walmart_assessment.util.ListUtils.retainAllOrdered;

public final class TicketVendor {
    public static TicketVendor with(SeatingRule... rules) {
        Preconditions.checkArgument(rules.length > 0);
        return new TicketVendor(rules);
    }

    private final SeatingRule[] rules;
    private final Set<String> requestsProcessed;

    private TicketVendor(SeatingRule... rules) {
        this.rules = rules;
        this.requestsProcessed = new HashSet<>();
    }

    public Optional<Ticket> vend(Theatre theatre, Request request) {
        SeatingMap currentSeatingMap = theatre.getMap();

        if (requestsProcessed.contains(request.getIdentifier())) {
            return Optional.empty();
        }

        Optional<SeatingAssignment[]> seatingAssignments = findViableSeatingAssignments(currentSeatingMap, request);
        if (seatingAssignments.isEmpty()) {
            return Optional.empty();
        }

        requestsProcessed.add(request.getIdentifier());
        return Optional.of(theatre.createTicket(request.getIdentifier(), seatingAssignments.get()));
    }

    private Optional<SeatingAssignment[]> findViableSeatingAssignments(SeatingMap map, Request request) {
        List<SeatingRule> orderedRules = Arrays.stream(rules).filter(SeatingRule::orderMatters).collect(Collectors.toList());

        List<SeatingAssignment> viableSeatingAssignments;
        if (orderedRules.isEmpty()) {
            viableSeatingAssignments = rules[0].findViableSeatingAssignments(map, request);
        } else {
            viableSeatingAssignments = orderedRules.get(0).findViableSeatingAssignments(map, request);

            for (int i = 1; i < orderedRules.size(); i++) {
                List<SeatingAssignment> ruleAssignments = orderedRules.get(i).findViableSeatingAssignments(map, request);
                retainAllOrdered(viableSeatingAssignments, ruleAssignments);
            }
        }

        for (int i = 1; i < rules.length; i++) {
            SeatingRule rule = rules[i];
            viableSeatingAssignments.retainAll(rule.findViableSeatingAssignments(map, request));
        }

        if (viableSeatingAssignments.size() < request.getSeatsRequested()) {
            return Optional.empty();
        }

        SeatingAssignment[] assignment = viableSeatingAssignments.stream()
                .limit(request.getSeatsRequested())
                .toArray(SeatingAssignment[]::new);
        return Optional.of(assignment);
    }
}
