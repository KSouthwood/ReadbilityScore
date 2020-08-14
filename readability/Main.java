package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.nextLine();
        String[] result = text.split("[?!.][ ]?");

        double count = 0;
        for (String sentence : result) {
            count += sentence.split("\\s").length;
        }

        System.out.println((count / result.length) <= 10.0 ? "EASY" : "HARD");
    }
}
