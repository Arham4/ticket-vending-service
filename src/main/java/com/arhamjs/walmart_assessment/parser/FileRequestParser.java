package com.arhamjs.walmart_assessment.parser;

import com.arhamjs.walmart_assessment.vendor.Request;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileRequestParser implements RequestParser {

    public static FileRequestParser with(File file) {
        return new FileRequestParser(file);
    }

    private final File file;

    private FileRequestParser(File file) {
        this.file = file;
    }

    @Override
    public List<Request> parse() throws IOException {
        String input = Files.asCharSource(file, Charsets.UTF_8).read();
        return StringRequestParser.with(input).parse();
    }
}
