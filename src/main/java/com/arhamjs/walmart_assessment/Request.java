package com.arhamjs.walmart_assessment;

public final class Request {
    public static Request of(String identifier, int seatsRequested) {
        return new Request(identifier, seatsRequested);
    }

    private final String identifier;
    private final int seatsRequested;

    private Request(String identifier, int seatsRequested) {
        this.identifier = identifier;
        this.seatsRequested = seatsRequested;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getSeatsRequested() {
        return seatsRequested;
    }
}
