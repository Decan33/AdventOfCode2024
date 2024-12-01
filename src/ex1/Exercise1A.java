package ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Exercise1A {
    private static ArrayList<Integer> leftList = new ArrayList<>();
    private static ArrayList<Integer> rightList = new ArrayList<>();
    private static ArrayList<Integer> distances = new ArrayList<>();
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

        leftList.sort(null);
        rightList.sort(null);

        for (int i = 0; i < 1000; i++) {
            distances.add(Math.abs(leftList.get(i) - rightList.get(i)));
        }

        var sum = distances.stream().reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);
    }
}
