import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class NaturalNumberInterpreter {

    public static void main(String[] args) {

        // Prompt the user for input
        //System.out.print("Enter a sequence of numbers separated by spaces: ");
        //Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();

        String input = "2 10 6 9 30 6 6 4";

        // Initialize the result list
        List<String> results = new ArrayList<>();

        // Initialize the inputIndex to start from the beginning of the string
        Integer inputIndex = 0;

        // Call recursive method to generate all number alternatives
        generateAlternatives(input, results, inputIndex);

        // Print all alternatives
        for (String str : results) {
            System.out.println(str);
        }

        // Call isValidGreekPhoneNumber method to validate each generated number, and print if the number is valid or not
        for (String alternative : results){
            String validationMessage = isValidGreekPhoneNumber(alternative.replaceAll(" ", "")) ? "[phone number: VALID]" : "[phone number: INVALID]";
            System.out.println("Interpretation: " + alternative.replaceAll(" ", "") + "\t" + validationMessage);
        }
    }

    public static void generateAlternatives(String input, List<String> result, Integer inputIndex) {
        // Split the string into substrings, separated by whitespaces
        String[] substrings = input.split(" ");


        String alt1;
        String alt2;

        int i;

        // Iterate over each substring, starting from the inputIndex
        for (i = inputIndex ; i < substrings.length; i++) {

            String current = substrings[i];         // Get the current substring, i.e. "69" from the string "69 30 6 6 4"

            int index = input.indexOf(current);     // Get the index of "69" from the whole input string

            if (current.length() == 2) {            // Check if the substring has 2 digits
                if (current.endsWith("0")) {        // Check if the substring ends with '0'

                    // Create 2 alternative strings
                    alt1 = input.substring(0, index) + current.charAt(0) + input.substring(index + 2).substring(1);     // 30 + 6 = 36
                    alt2 = input.substring(0, index) + current + input.substring(index + 2).substring(1);               // 30 + 6 = 306


                } else {
                    alt1 = input.substring(0, index) + current + input.substring(index + 2);                                        // 69
                    alt2 = input.substring(0, index) + current.charAt(0) + "0" + current.charAt(1) + input.substring(index + 2);    // 609

                }

                // For each of the alternative strings created, call the recursive method and start with the index pointing to the next string
                generateAlternatives(alt1, result, index + 1);
                generateAlternatives(alt2, result, index + 1);

                // At the end of each recursion, save the last string generated into the results list
                result.add(alt1);
                result.add(alt2);
            }
        }
    }

    // Helper function to check if the number is a valid Greek phone number.
    // A number is valid if it has 10 digits and starts with 2 or 69 OR
    // if it has 14 digits and starts with 00302 or 003069
    public static boolean isValidGreekPhoneNumber(String number) {
        return (number.length() == 10 && (number.startsWith("2") || number.startsWith("69"))) ||
                (number.length() == 14 && (number.startsWith("00302") || number.startsWith("003069")));
    }
}
