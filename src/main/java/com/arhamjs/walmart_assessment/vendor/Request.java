package com.arhamjs.walmart_assessment.vendor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return seatsRequested == request.seatsRequested && Objects.equals(identifier, request.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, seatsRequested);
    }
}
