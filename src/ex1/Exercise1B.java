package ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exercise1B {
    private static ArrayList<Integer> leftList = new ArrayList<>();
    private static ArrayList<Integer> rightList = new ArrayList<>();

    private static Integer calculateFrequency(Integer element) {
        return Math.toIntExact(rightList.stream().filter(number -> number.equals(element)).count());
    }

    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new FileReader("src/ex1/input.txt"));

        var line = reader.readLine();

        while(line != null) {
            var tokens = new StringTokenizer(line.trim());

            leftList.add(Integer.valueOf(tokens.nextToken()));
            rightList.add(Integer.valueOf(tokens.nextToken()));

            line = reader.readLine();

        }

        reader.close();

        var freqs = 0;

        for (var num : leftList) {
            freqs += num * calculateFrequency(num);
        }


        System.out.println("Freq: " + freqs);
    }
}
