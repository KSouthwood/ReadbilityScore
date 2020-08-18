package readability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String data = readFile(args[0]);

        if (!data.isBlank()) {
            int countSentences = data.split("[?!.][ ]?").length;
            int countWords = data.split("\\s").length;
            int countChars = data.replaceAll("\\s", "").length();

            String[] words = data.split("\\s");
            int[] syllables = countSyllables(words);

            System.out.println("Words: " + countWords);
            System.out.println("Sentences: " + countSentences);
            System.out.println("Characters: " + countChars);
            System.out.println("Syllables: " + syllables[0]);
            System.out.println("Polysyllables: " + syllables[1]);

            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            String choice = new Scanner(System.in).nextLine().toUpperCase();
            int[] ages = {0, 0};

            if (choice.equals("ARI") || choice.equals("ALL")) {
                double score = (4.71 * ((double) countChars / countWords)) + (0.5 * ((double) countWords / countSentences)) - 21.43;
                int age = getAgeRange(score);
                ages[0] += age;
                ages[1]++;
                System.out.printf("Automated Readability Index: %.2f (about %d year olds).\n", score, age);
            }

            if (choice.equals("FK") || choice.equals("ALL")) {
                double score = (0.39 * ((double) countWords / countSentences)) + (11.8 * ((double) syllables[0] / countWords)) - 15.59;
                int age = getAgeRange(score);
                ages[0] += age;
                ages[1]++;
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).\n", score, age);
            }

            if (choice.equals("SMOG") || choice.equals("ALL")) {
                double score = 1.043 * (Math.sqrt(syllables[1] * (30.0 / countSentences))) + 3.1291;
                int age = getAgeRange(score);
                ages[0] += age;
                ages[1]++;
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).\n", score, age);
            }

            if (choice.equals("CL") || choice.equals("ALL")) {
                double score = (0.0588 * (((double) countChars / countWords) * 100.0) - 0.296 * (((double) countSentences / countWords) * 100.0) - 15.8);
                int age = getAgeRange(score);
                ages[0] += age;
                ages[1]++;
                System.out.printf("Coleman–Liau index: %.2f (about %d year olds).\n", score, age);
            }

            if (ages[1] > 0) {
                System.out.printf("\nThis text should be understood in average by %.2f year olds.\n", (double) ages[0] / ages[1]);
            } else {
                System.out.println("Unknown score choice.");
            }
        }
    }

    private static String readFile(String arg) {
        StringBuilder file_in = new StringBuilder();
        try (BufferedReader data = new BufferedReader(new FileReader(arg))) {
            String line;
            while ((line = data.readLine()) != null) {
                file_in.append(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return file_in.toString();
    }

    private static int getAgeRange(double ari) {
        int index = (int) Math.ceil(ari);

        switch (index) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
            case 14:
                return 24;
        }

        return 6;
    }

    private static int[] countSyllables(String[] words) {
        int[] syllables = new int[2];

        for (String word : words) {
            word = word.replaceAll("[?!.,]", "");
            int vowels = 0;
            int index;
            for (index = 0; index < word.length(); index++) {
                if (Character.toString(word.charAt(index)).matches("[aeiouyAEIOUY]")) {
                    vowels++;
                    if (index > 0 && Character.toString(word.charAt(index - 1)).matches("[aieouy]")) {
                        vowels--;
                    }
                }
            }

            if (word.charAt(index - 1) == 'e' || word.charAt(index - 1) == 'E') {
                vowels--;
            }

            if (vowels < 1) {
                vowels = 1;
            }
            syllables[0] += vowels;
            syllables[1] += vowels > 2 ? 1 : 0; // polysyllables count (word has 3 or more syllables)
        }

        return syllables;
    }
}
