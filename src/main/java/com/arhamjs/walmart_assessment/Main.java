package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.parser.DefaultRequestParser;
import com.arhamjs.walmart_assessment.parser.RequestParser;
import com.arhamjs.walmart_assessment.rules.AvailabilityRule;
import com.arhamjs.walmart_assessment.rules.SafetyRule;
import com.arhamjs.walmart_assessment.rules.SatisfactionRule;
import com.arhamjs.walmart_assessment.ticket.Ticket;
import com.arhamjs.walmart_assessment.vendor.Request;
import com.arhamjs.walmart_assessment.vendor.TicketVendor;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Main {
    private static final String OUTPUT_PATH = "output.txt";

    public static void main(String[] args) throws IOException {
        String completeInputPath = String.join(" ", args);
        String fileContent = Files.asCharSource(new File(completeInputPath), Charsets.UTF_8).read();

        RequestParser parser = DefaultRequestParser.create();
        List<Request> requests = parser.parse(fileContent);

        SeatingMap map = SeatingMap.empty(10, 20);
        Theatre theatre = Theatre.of(map);

        AvailabilityRule availabilityRule = AvailabilityRule.create();
        SafetyRule safetyRule = SafetyRule.builder()
                .distance(3)
                .rule(availabilityRule)
                .build();
        TicketVendor vendor = TicketVendor.with(SatisfactionRule.with(availabilityRule, safetyRule), safetyRule, availabilityRule);

        List<Ticket> tickets = new ArrayList<>();
        for (Request request : requests) {
            Optional<Ticket> ticket = vendor.vend(theatre, request);
            if (ticket.isPresent()) {
                tickets.add(ticket.get());
            } else {
                System.err.println("Unable to process request " + request.getIdentifier());
            }
        }

        File outputFile = new File(OUTPUT_PATH);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath()));

        for (Ticket ticket : tickets) {
            writer.write(ticket.toOutputString() + "\n");
        }

        writer.close();
        System.out.println("Outputted successful requests to \"" + outputFile.getAbsolutePath() + "\"");
    }
}
