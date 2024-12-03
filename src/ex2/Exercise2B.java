package ex2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Exercise2B {

    private static final List<SafetyReport> safetyList = new ArrayList<>();

    private static boolean isAscending(List<Integer> nums) {
        return IntStream
                .range(0, nums.size() - 1)
                .noneMatch(i -> nums.get(i) < nums.get(i + 1));
    }

    private static boolean isDescending(List<Integer> nums) {
        return IntStream
                .range(0, nums.size() - 1)
                .noneMatch(i -> nums.get(i) > nums.get(i + 1));
    }

    public static void main(String[] args) {

        try (var reader = new BufferedReader(new FileReader("src/ex2/input.txt"))) {

            var line = reader.readLine();

            while (null != line) {
                var tokens = Arrays
                        .stream(line.trim().split(" "))
                        .map(Integer::parseInt)
                        .toList();

                SafetyReport safetyReport = checkForSafetyLevels(tokens);
                if (SafetyReport.SAFE.equals(safetyReport)) {
                    safetyList.add(SafetyReport.SAFE);

                } else {
                    if (dampenedReport(tokens)) {
                        safetyList.add(SafetyReport.SAFE);
                    } else {
                        safetyList.add(SafetyReport.UNSAFE);
                    }
                }

                line = reader.readLine();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Reports: " + safetyList.stream().filter(SafetyReport.SAFE::equals).count());

    }

    private static boolean dampenedReport(List<Integer> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            int finalI = i;
            var dampedReport = IntStream.range(0, tokens.size())
                    .filter(index -> index != finalI)
                    .mapToObj(tokens::get)
                    .toList();

            if (checkForSafetyLevels(dampedReport).equals(SafetyReport.SAFE)) {
                return true;
            }
        }

        return false;
    }

    private static SafetyReport checkForSafetyLevels(List<Integer> tokens) {
        if (!hasWrongOrder(tokens) && !isDifferenceTooHigh(tokens)) {

            return SafetyReport.SAFE;
        }

        return SafetyReport.UNSAFE;
    }

    private static boolean hasWrongOrder(List<Integer> tokens) {
        return !isDescending(tokens) && !isAscending(tokens);
    }

    private static boolean isDifferenceTooHigh(List<Integer> tokens) {
        for (int i = 0; i < tokens.size() - 1; i++) {
            var difference = Math.abs(tokens.get(i) - tokens.get(i+1));

            if (difference < 1 || difference > 3) {
                return true;
            }
        }

        return false;
    }

}
