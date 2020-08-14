package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String sentence = input.nextLine();

        String result = sentence.length() > 100 ? "HARD" : "EASY";

        System.out.println(result);
    }
}
