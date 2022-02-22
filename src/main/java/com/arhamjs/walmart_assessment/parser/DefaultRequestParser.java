package com.arhamjs.walmart_assessment.parser;

import com.arhamjs.walmart_assessment.vendor.Request;

import java.util.ArrayList;
import java.util.List;

public final class DefaultRequestParser implements RequestParser {

    public static DefaultRequestParser create() {
        return new DefaultRequestParser();
    }

    private DefaultRequestParser() {
    }

    @Override
    public List<Request> parse(String input) {
        List<Request> result = new ArrayList<>();
        for (String line : input.split("\n")) {
            String[] split = line.split(" ");
            result.add(Request.of(split[0], Integer.parseInt(split[1])));
        }
        return result;
    }
}
