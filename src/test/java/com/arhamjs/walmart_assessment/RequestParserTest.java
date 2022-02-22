package com.arhamjs.walmart_assessment;

import com.arhamjs.walmart_assessment.parser.FileRequestParser;
import com.arhamjs.walmart_assessment.parser.RequestParser;
import com.arhamjs.walmart_assessment.parser.StringRequestParser;
import com.arhamjs.walmart_assessment.vendor.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public final class RequestParserTest {
    @Test
    public void Should_HaveOneRequestFor2People() throws IOException {
        String input = "R001 2";

        RequestParser parser = StringRequestParser.with(input);

        List<Request> requests = parser.parse();
        Assertions.assertEquals(1, requests.size());

        Request expected = Request.of("R001", 2);

        Assertions.assertEquals(expected, requests.get(0));
    }

    @Test
    public void Should_Have2Requests_OneWith2People_OneWith4People() throws IOException {
        String input = "R001 2\nR002 4";

        RequestParser parser = StringRequestParser.with(input);

        List<Request> requests = parser.parse();
        Assertions.assertEquals(2, requests.size());

        List<Request> expected = List.of(
                Request.of("R001", 2),
                Request.of("R002", 4)
        );

        Assertions.assertEquals(expected, requests);
    }
}
