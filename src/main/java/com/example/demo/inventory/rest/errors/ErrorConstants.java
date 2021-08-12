package com.example.demo.inventory.rest.errors;

import java.net.URI;

public final class ErrorConstants {
    public static final String PROBLEM_BASE_URL = "http://localhost/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE =
            URI.create(PROBLEM_BASE_URL + "/constraint-violation");

    public static final String ERR_VALIDATION = "error.validation";
    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";

    private ErrorConstants() {
        throw new IllegalStateException("Constant class");
    }
}
