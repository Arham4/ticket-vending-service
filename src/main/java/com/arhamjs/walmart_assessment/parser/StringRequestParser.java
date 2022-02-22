package com.arhamjs.walmart_assessment.parser;

import com.arhamjs.walmart_assessment.vendor.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class StringRequestParser implements RequestParser {
    public static StringRequestParser with(String input) {
        return new StringRequestParser(input);
    }

    private final String input;

    private StringRequestParser(String input) {
        this.input = input;
    }

    @Override
    public List<Request> parse() throws IOException {
        List<Request> result = new ArrayList<>();
        for (String line : input.split("\n")) {
            String[] split = line.split(" ");

            if (line.length() < 2) {
                continue;
            }

            result.add(Request.of(split[0], Integer.parseInt(split[1])));
        }
        return result;
    }
}
