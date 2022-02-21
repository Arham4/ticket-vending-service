package com.arhamjs.walmart_assessment.ensurer;

public final class SafetyEnsurer implements Ensurer {
    public static SafetyEnsurer empty() {
        return new SafetyEnsurer();
    }

    private SafetyEnsurer() {
    }
}
