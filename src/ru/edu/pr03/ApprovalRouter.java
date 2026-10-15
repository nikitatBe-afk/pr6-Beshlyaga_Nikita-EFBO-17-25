package ru.edu.pr03;
public class ApprovalRouter {
    public static final long FINANCE_LIMIT = 500_000L;
    public static final long STANDARD_LIMIT = 50_000L;

    public ApprovalRoute route(long amount, boolean urgent, RiskLevel risk, boolean containsPersonalData) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "amount must be >= 0, got " + amount
            );
        }

        if (risk == null) {
            throw new IllegalArgumentException("risk is null");
        }

        if (containsPersonalData) {
            return ApprovalRoute.SECURITY_REVIEW;
        }

        if (risk == RiskLevel.HIGH) {
            return ApprovalRoute.DIRECTOR_REVIEW;
        }

        if (amount >= FINANCE_LIMIT) {
            return ApprovalRoute.FINANCE_REVIEW;
        }

        if (urgent && amount < FINANCE_LIMIT) {
            return ApprovalRoute.FAST_TRACK;
        }

        if (addStandardEligibility(amount, urgent, risk, containsPersonalData)) {
            return ApprovalRoute.STANDARD;
        }

        if (risk == RiskLevel.MEDIUM) {
            return ApprovalRoute.DIRECTOR_REVIEW;
        }

        return ApprovalRoute.FINANCE_REVIEW;
    }

    public boolean addStandardEligibility(long amount, boolean urgent, RiskLevel risk, boolean containsPersonalData) {
        return amount < STANDARD_LIMIT
                && !urgent
                && risk == RiskLevel.LOW
                && !containsPersonalData;
    }
}
