package readability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String data = readFile(args[0]);

        if (!data.isBlank()) {
            String[] sentences = data.split("[?!.][ ]?");
            int countSentences = sentences.length;
            int countWords = 0;
            int countChars = 0;

            String[] words = data.split("\\s");
            countWords += words.length;
            for (String word : words) {
                countChars += word.length();
            }

            double ariScore = (4.71 * ((double) countChars / countWords)) + (0.5 * ((double) countWords / countSentences)) - 21.43;
            String ageRange = getAgeRange(ariScore);

            System.out.println("Words: " + countWords);
            System.out.println("Sentences: " + countSentences);
            System.out.println("Characters: " + countChars);
            System.out.printf("The score is: %.2f\n", ariScore);
            System.out.println("This text should be understood by " + ageRange + " year olds.");
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

    private static String getAgeRange(double ari) {
        int index = (int) Math.ceil(ari);

        switch (index) {
            case 1:
                return "5-6";
            case 2:
                return "6-7";
            case 3:
                return "7-9";
            case 4:
                return "9-10";
            case 5:
                return "10-11";
            case 6:
                return "11-12";
            case 7:
                return "12-13";
            case 8:
                return "13-14";
            case 9:
                return "14-15";
            case 10:
                return "15-16";
            case 11:
                return "16-17";
            case 12:
                return "17-18";
            case 13:
                return "18-24";
            case 14:
                return "24+";
        }

        return "";
    }
}
