package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.parser.DefaultRequestParser;
import com.arhamjs.walmart_assessment.parser.RequestParser;
import com.arhamjs.walmart_assessment.vendor.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public final class RequestParserTest {
    @Test
    public void Should_HaveOneRequestFor2People() {
        String input = "R001 2";

        RequestParser parser = DefaultRequestParser.create();

        List<Request> requests = parser.parse(input);
        Assertions.assertEquals(1, requests.size());

        Request expected = Request.of("R001", 2);

        Assertions.assertEquals(expected, requests.get(0));
    }

    @Test
    public void Should_Have2Requests_OneWith2People_OneWith4People() {
        String input = "R001 2\nR002 4";

        RequestParser parser = DefaultRequestParser.create();

        List<Request> requests = parser.parse(input);
        Assertions.assertEquals(2, requests.size());

        List<Request> expected = List.of(
                Request.of("R001", 2),
                Request.of("R002", 4)
        );

        Assertions.assertEquals(expected, requests);
    }
}
