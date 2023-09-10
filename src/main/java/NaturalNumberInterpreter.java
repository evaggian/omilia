import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class NaturalNumberInterpreter {

    public static void main(String[] args) {

        // Prompt the user for input
        System.out.print("Enter a sequence of numbers separated by spaces: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //String input = "2 10 6 9 30 6 6 4";

        // Initialize the result list
        List<String> results = new ArrayList<>();

        Integer inputIndex = 0;
        generateAlternatives(input, results, inputIndex);

        // Remove duplicates using a HashSet
        HashSet<String> uniqueSet = new HashSet<>(results);

        // Convert the HashSet back to a list (if needed)
        List<String> resultList = new ArrayList<>(uniqueSet);

        for (String str : resultList) {
            System.out.println(str);
        }

        for (String alternative : resultList){
            String validationMessage = isValidGreekPhoneNumber(alternative.replaceAll(" ", "")) ? "[phone number: VALID]" : "[phone number: INVALID]";
            System.out.println("Interpretation: " + alternative.replaceAll(" ", "") + "\t" + validationMessage);
        }
    }

    public static void generateAlternatives(String input, List<String> result, Integer inputIndex) {
        String[] substrings = input.split(" ");

        String alt1;
        String alt2;

        int i;

        for (i = inputIndex ; i < substrings.length; i++) {

            String current = substrings[i];         // 69 30 6 6 4
            int index = input.indexOf(current);     // get index of 69

            if (current.length() == 2) {            // check if chunk has 2 digits
                if (current.endsWith("0")) {        // check if it ends with '0'

                    alt1 = input.substring(0, index) + current.charAt(0) + input.substring(index + 2).substring(1); // 30 + 6 = 36
                    alt2 = input.substring(0, index) + current + input.substring(index + 2).substring(1); // 30 + 6 = 306


                } else {
                    alt1 = input.substring(0, index) + current + input.substring(index + 2);         // 69
                    alt2 = input.substring(0, index) + current.charAt(0) + "0" + current.charAt(1) + input.substring(index + 2);   // 609

                }
                generateAlternatives(alt1, result, index + 1);
                generateAlternatives(alt2, result, index + 1);

                result.add(alt1);
                result.add(alt2);
            }
        }
    }

    // Helper function to check if the number is a valid Greek phone number
    public static boolean isValidGreekPhoneNumber(String number) {
        return (number.length() == 10 && (number.startsWith("2") || number.startsWith("69"))) ||
                (number.length() == 14 && (number.startsWith("00302") || number.startsWith("003069")));
    }
}
