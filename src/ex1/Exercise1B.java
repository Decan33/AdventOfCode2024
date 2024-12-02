package ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("java:S106")
public class Exercise1B {
    private static final ArrayList<Integer> leftList = new ArrayList<>();
    private static final ArrayList<Integer> rightList = new ArrayList<>();

    private static Integer calculateFrequency(Integer element) {
        return Math.toIntExact(rightList.stream().filter(number -> number.equals(element)).count());
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new FileReader("src/ex1/input.txt"))) {

            var line = reader.readLine();

            while (null != line) {
                var tokens = new StringTokenizer(line.trim());

                leftList.add(Integer.valueOf(tokens.nextToken()));
                rightList.add(Integer.valueOf(tokens.nextToken()));

                line = reader.readLine();

            }

        }

        var freqs = 0;

        for (var num : leftList) {
            freqs += num * calculateFrequency(num);
        }


        System.out.println("Freq: " + freqs);
    }
}
