package ru.edu.pr03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SelfCheck {
    public static void main(String[] args) {
        int passedUnit = 0;

        // 1. Personal приоритетнее urgent
        boolean ok1 = false;
        try {
            ok1 = new ApprovalRouter().route(
                    10_000, true, RiskLevel.LOW, true
            ) == ApprovalRoute.SECURITY_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 1 exception " + e.getMessage());
        }
        if (ok1) passedUnit++;
        System.out.println(ok1 ? "PASS 1 Personal > urgent" : "FAIL 1 Personal > urgent");

        // 2. HIGH приоритетнее urgent
        boolean ok2 = false;
        try {
            ok2 = new ApprovalRouter().route(
                    10_000, true, RiskLevel.HIGH, false
            ) == ApprovalRoute.DIRECTOR_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 2 exception " + e.getMessage());
        }
        if (ok2) passedUnit++;
        System.out.println(ok2 ? "PASS 2 HIGH > urgent" : "FAIL 2 HIGH > urgent");

        // 3. Personal приоритетнее HIGH
        boolean ok3 = false;
        try {
            ok3 = new ApprovalRouter().route(
                    10_000, false, RiskLevel.HIGH, true
            ) == ApprovalRoute.SECURITY_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 3 exception " + e.getMessage());
        }
        if (ok3) passedUnit++;
        System.out.println(ok3 ? "PASS 3 Personal > HIGH" : "FAIL 3 Personal > HIGH");

        // 4. Сумма приоритетнее urgent
        boolean ok4 = false;
        try {
            ok4 = new ApprovalRouter().route(
                    600_000, true, RiskLevel.LOW, false
            ) == ApprovalRoute.FINANCE_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 4 exception " + e.getMessage());
        }
        if (ok4) passedUnit++;
        System.out.println(ok4 ? "PASS 4 Finance > urgent" : "FAIL 4 Finance > urgent");

        // 5. FAST_TRACK
        boolean ok5 = false;
        try {
            ok5 = new ApprovalRouter().route(
                    10_000, true, RiskLevel.LOW, false
            ) == ApprovalRoute.FAST_TRACK;
        } catch (Exception e) {
            System.out.println("FAIL 5 exception " + e.getMessage());
        }
        if (ok5) passedUnit++;
        System.out.println(ok5 ? "PASS 5 FAST_TRACK" : "FAIL 5 FAST_TRACK");

        // 6. FINANCE_REVIEW на границе 500000
        boolean ok6 = false;
        try {
            ok6 = new ApprovalRouter().route(
                    500_000, false, RiskLevel.LOW, false
            ) == ApprovalRoute.FINANCE_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 6 exception " + e.getMessage());
        }
        if (ok6) passedUnit++;
        System.out.println(ok6 ? "PASS 6 FINANCE_REVIEW boundary" : "FAIL 6 FINANCE_REVIEW boundary");

        // 7. STANDARD
        boolean ok7 = false;
        try {
            ok7 = new ApprovalRouter().route(
                    10_000, false, RiskLevel.LOW, false
            ) == ApprovalRoute.STANDARD;
        } catch (Exception e) {
            System.out.println("FAIL 7 exception " + e.getMessage());
        }
        if (ok7) passedUnit++;
        System.out.println(ok7 ? "PASS 7 STANDARD" : "FAIL 7 STANDARD");

        // 8. По варианту 4 MEDIUM не подходит для STANDARD
        boolean ok8 = false;
        try {
            ok8 = new ApprovalRouter().route(
                    10_000, false, RiskLevel.MEDIUM, false
            ) == ApprovalRoute.DIRECTOR_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 8 exception " + e.getMessage());
        }
        if (ok8) passedUnit++;
        System.out.println(ok8 ? "PASS 8 MEDIUM -> DIRECTOR_REVIEW" : "FAIL 8 MEDIUM -> DIRECTOR_REVIEW");

        // 9. SECURITY_REVIEW
        boolean ok9 = false;
        try {
            ok9 = new ApprovalRouter().route(
                    10_000, false, RiskLevel.LOW, true
            ) == ApprovalRoute.SECURITY_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 9 exception " + e.getMessage());
        }
        if (ok9) passedUnit++;
        System.out.println(ok9 ? "PASS 9 SECURITY_REVIEW" : "FAIL 9 SECURITY_REVIEW");

        // 10. 499999 -> FAST_TRACK
        boolean ok10 = false;
        try {
            ok10 = new ApprovalRouter().route(
                    499_999, true, RiskLevel.LOW, false
            ) == ApprovalRoute.FAST_TRACK;
        } catch (Exception e) {
            System.out.println("FAIL 10 exception " + e.getMessage());
        }
        if (ok10) passedUnit++;
        System.out.println(ok10 ? "PASS 10 499999 urgent -> FAST_TRACK" : "FAIL 10 499999 urgent -> FAST_TRACK");

        // 11. 500001 -> FINANCE_REVIEW
        boolean ok11 = false;
        try {
            ok11 = new ApprovalRouter().route(
                    500_001, false, RiskLevel.LOW, false
            ) == ApprovalRoute.FINANCE_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 11 exception " + e.getMessage());
        }
        if (ok11) passedUnit++;
        System.out.println(ok11 ? "PASS 11 500001 -> FINANCE_REVIEW" : "FAIL 11 500001 -> FINANCE_REVIEW");

        // 12. amount < 0 должен вызвать IllegalArgumentException
        boolean ok12 = false;
        try {
            new ApprovalRouter().route(
                    -1, true, RiskLevel.LOW, false
            );
        } catch (IllegalArgumentException e) {
            ok12 = true;
        } catch (Exception e) {
            System.out.println("FAIL 12 wrong exception " + e.getMessage());
        }
        if (ok12) passedUnit++;
        System.out.println(ok12 ? "PASS 12 amount < 0" : "FAIL 12 amount < 0");

        // 13. risk == null должен вызвать IllegalArgumentException
        boolean ok13 = false;
        try {
            new ApprovalRouter().route(
                    10_000, true, null, false
            );
        } catch (IllegalArgumentException e) {
            ok13 = true;
        } catch (Exception e) {
            System.out.println("FAIL 13 wrong exception " + e.getMessage());
        }
        if (ok13) passedUnit++;
        System.out.println(ok13 ? "PASS 13 risk == null" : "FAIL 13 risk == null");

        // 14. Часть А: верхняя граница STANDARD
        boolean ok14 = false;
        try {
            ok14 = new ApprovalRouter().route(
                    49_999, false, RiskLevel.LOW, false
            ) == ApprovalRoute.STANDARD;
        } catch (Exception e) {
            System.out.println("FAIL 14 exception " + e.getMessage());
        }
        if (ok14) passedUnit++;
        System.out.println(ok14 ? "PASS 14 49999 -> STANDARD" : "FAIL 14 49999 -> STANDARD");

        // 15. Часть А: сумма 50000 уже не подходит для STANDARD
        boolean ok15 = false;
        try {
            ok15 = new ApprovalRouter().route(
                    50_000, false, RiskLevel.LOW, false
            ) == ApprovalRoute.FINANCE_REVIEW;
        } catch (Exception e) {
            System.out.println("FAIL 15 exception " + e.getMessage());
        }
        if (ok15) passedUnit++;
        System.out.println(ok15 ? "PASS 15 50000 -> FINANCE_REVIEW" : "FAIL 15 50000 -> FINANCE_REVIEW");

        // 16. Часть Б: подходящая заявка
        boolean ok16 = false;
        try {
            ok16 = new ApprovalRouter().addStandardEligibility(
                    49_999, false, RiskLevel.LOW, false
            );
        } catch (Exception e) {
            System.out.println("FAIL 16 exception " + e.getMessage());
        }
        if (ok16) passedUnit++;
        System.out.println(ok16 ? "PASS 16 addStandardEligibility true" : "FAIL 16 addStandardEligibility true");

        // 17. Часть Б: персональные данные запрещают STANDARD
        boolean ok17 = false;
        try {
            ok17 = !new ApprovalRouter().addStandardEligibility(
                    10_000, false, RiskLevel.LOW, true
            );
        } catch (Exception e) {
            System.out.println("FAIL 17 exception " + e.getMessage());
        }
        if (ok17) passedUnit++;
        System.out.println(ok17 ? "PASS 17 personal is not eligible" : "FAIL 17 personal is not eligible");

        System.out.println("Unit: " + passedUnit + "/17 passed");
        System.out.println();
        System.out.println("Data-driven:");

        try {
            var lines = Files.readAllLines(Paths.get("data/sample.csv"));
            int passed = 0;
            int total = 0;

            for (int i = 1; i < lines.size(); i++) {
                if (lines.get(i).isEmpty() || lines.get(i).startsWith("#")) {
                    continue;
                }

                total++;
                String[] p = lines.get(i).split(";", -1);

                try {
                    if (p.length != 5) {
                        throw new IllegalArgumentException("expected 5 values");
                    }

                    long amount = Long.parseLong(p[0]);
                    boolean urgent = parseBoolean(p[1], "urgent");
                    RiskLevel risk = p[2].equals("null")
                            ? null
                            : RiskLevel.valueOf(p[2]);
                    boolean personal = parseBoolean(p[3], "personal");
                    String expected = p[4];

                    ApprovalRoute actual = new ApprovalRouter().route(
                            amount,
                            urgent,
                            risk,
                            personal
                    );

                    if (actual.toString().equals(expected)) {
                        passed++;
                        System.out.println("PASS data/" + total);
                    } else {
                        System.out.println("FAIL data/" + total);
                    }
                } catch (IllegalArgumentException e) {
                    if (p.length == 5 && p[4].equals("ERROR")) {
                        passed++;
                        System.out.println("PASS data/" + total);
                    } else {
                        System.out.println("FAIL data/" + total);
                    }
                }
            }

            System.out.println(
                    "Data-driven: " + passed + "/" + total + " passed"
            );
        } catch (Exception e) {
            System.out.println("Ошибка чтения CSV: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Интерактивная проверка:");
        System.out.println("Введите: amount;urgent;risk;personal");
        System.out.println("exit для выхода");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine();
            if (line.isEmpty() || line.equals("exit")) {
                break;
            }

            try {
                String[] parts = line.split(";", -1);

                if (parts.length != 4) {
                    System.out.println("Ошибка: expected 4 values");
                    continue;
                }

                long amount = Long.parseLong(parts[0]);
                boolean urgent = parseBoolean(parts[1], "urgent");
                RiskLevel risk = RiskLevel.valueOf(parts[2]);
                boolean personal = parseBoolean(parts[3], "personal");

                ApprovalRoute result = new ApprovalRouter().route(
                        amount,
                        urgent,
                        risk,
                        personal
                );

                System.out.println("Маршрут: " + result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static boolean parseBoolean(String value, String fieldName) {
        if (value.equals("true")) {
            return true;
        }
        if (value.equals("false")) {
            return false;
        }
        throw new IllegalArgumentException(fieldName + " must be true or false");
    }
}
