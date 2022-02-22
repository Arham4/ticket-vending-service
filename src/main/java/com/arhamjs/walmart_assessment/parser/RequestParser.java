package com.arhamjs.walmart_assessment.parser;

import com.arhamjs.walmart_assessment.vendor.Request;

import java.util.List;

public interface RequestParser {
    List<Request> parse(String input);
}
