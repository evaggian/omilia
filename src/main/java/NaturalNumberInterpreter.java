import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaturalNumberInterpreter {

    public static void main(String[] args) {

        // Prompt the user for input
        //System.out.print("Enter a sequence of numbers separated by spaces: ");
        //Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();

        String input = "2 10 69 30 6 6 4";

        // Initialize the result list
        List<String> results = new ArrayList<>();

        // Initialize the inputIndex to start from the beginning of the string
        Integer inputIndex = 0;

        System.out.println(input);

        List<List<String>> superList = new ArrayList<>();

        // Call recursive method to generate all number alternatives
        generateAlternatives(input, superList, inputIndex);

        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();

        // Start the enumeration
        enumerateSequences(superList, currentSequence, 0, alternations);

        // Print the results
        for (List<String> sequence : alternations) {
            StringBuilder sb = new StringBuilder();
            for (String symbol : sequence) {
                sb.append(symbol);
            }
            //System.out.println(sb);
            String validationMessage = isValidGreekPhoneNumber(sb.toString().replaceAll(" ", "")) ? "[phone number: VALID]" : "[phone number: INVALID]";
            System.out.println("Interpretation: " + sb.toString().replaceAll(" ", "") + "\t" + validationMessage);

        }

        // Call isValidGreekPhoneNumber method to validate each generated number, and print if the number is valid or not
        for (String alternative : results){
            String validationMessage = isValidGreekPhoneNumber(alternative.replaceAll(" ", "")) ? "[phone number: VALID]" : "[phone number: INVALID]";
            System.out.println("Interpretation: " + alternative.replaceAll(" ", "") + "\t" + validationMessage);
        }
    }

    public static void generateAlternatives(String input, List<List<String>> superList, Integer inputIndex) {
        // Split the string into substrings, separated by whitespaces
        String[] substrings = input.split(" ");

        int i;

        // Iterate over each substring, starting from the inputIndex
        for (i = inputIndex ; i < substrings.length; i++) {

            int skip = 0;
            boolean nextExists = (i + 1 < substrings.length);
            boolean nextNextExists = (i + 2 < substrings.length);
            List<String> innerList = new ArrayList<>();

            String current = substrings[i];         // Get the current substring, i.e. "69" from the string "69 30 6 6 4"

            if (current.length() == 1){
                innerList.add(current);
            }
            else if (current.length() == 2) {            // Check if the substring has 2 digits
                if  (current.startsWith("1")){
                    innerList.add(current);
                }
                else if (current.endsWith("0")) {// Check if the substring ends with '0'
                    if (nextExists){
                        if ((substrings[i+1].length() == 1) && (!substrings[i+1].equals("0"))){
                            innerList.add(current.charAt(0) + substrings[i + 1]); // 30 6 = 36
                            innerList.add(current + substrings[i + 1]);           // 30 6 = 306
                            skip = 1;
                        }
                        else {
                            innerList.add(current);
                        }
                    }
                    else {
                        innerList.add(current);     // 60 0 = 60
                    }
                }
                else {
                    innerList.add((current));                              // 69
                    innerList.add(current.charAt(0) + "0" + current.charAt(1));     // 609
                }
            }
            else{           // chunk has 3 digits
                if (current.endsWith("00")) {        // Check if the substring ends with '00'
                    if (nextExists) {
                        if ((substrings[i + 1].length() == 1) && (!substrings[i + 1].equals("0"))) {
                            innerList.add(String.valueOf(current.charAt(0)) + current.charAt(1) + substrings[i + 1]);         // 700 + 4 = 704
                            innerList.add(current + substrings[i + 1]);         // 700 + 4 = 7004
                            skip = 1;
                        } else if (substrings[i + 1].length() == 2) {
                            if (substrings[i + 1].startsWith("1")) {
                                innerList.add(current.charAt(0) + substrings[i + 1]);         // 700 + 15 = 715
                                innerList.add(current + substrings[i + 1]);         // 700 + 15 = 70015
                                skip = 1;
                            } else if (substrings[i + 1].endsWith("0")) {        // Check if the substring ends with '0'
                                if (nextNextExists){
                                    if (substrings[i + 2].length() == 1) {
                                        innerList.add(current + substrings[i + 1] + substrings[i + 2]); // 700 + 20 + 4 = 700204
                                        innerList.add(current + substrings[i + 1].charAt(0) + substrings[i + 2]);                // 700 + 20 + 4 = 70024
                                        innerList.add(String.valueOf(current.charAt(0)) + substrings[i + 1].charAt(0) + substrings[i + 2]);                   // 700 + 20 + 4 = 724
                                        innerList.add(current.charAt(0) + substrings[i + 1] + substrings[i + 2]);           // 700 + 20 + 4 = 7204
                                        skip = 2;
                                    }
                                } else {
                                    innerList.add(current.charAt(0) + substrings[i + 1]);          // 700 + 20 = 720
                                    innerList.add(current + substrings[i + 1]);                    // 700 + 20 = 70020
                                    skip = 1;
                                }
                            } else {
                                innerList.add((current.charAt(0) + substrings[i + 1]));                              // 700 + 24 = 724
                                innerList.add((String.valueOf(current.charAt(0)) + substrings[i + 1].charAt(0) + "0" + substrings[i + 1].charAt(1)));   // 700 + 24 = 7204
                                innerList.add((current + substrings[i + 1]));                              // 700 + 24 = 70024
                                innerList.add((current + substrings[i + 1].charAt(0) + "0" + substrings[i + 1].charAt(1)));   // 700 + 24 = 700204
                                skip = 1;
                            }
                            //innerList.add(current.charAt(0) + current.charAt(1) + substrings[i+1]);         // 700 + 24 = 70024
                            //innerList.add(current.charAt(0) + substrings[i+1]);         // 700 + 24 = 724
                        }
                        else {
                            innerList.add(current);
                        }
                    }
                    else {
                        innerList.add(current);         // 700
                    }
                }
                else if (current.endsWith("0")){
                    if  (current.charAt(1) == '1'){
                        innerList.add(current);             // 710
                        innerList.add(current.charAt(0) + "00" + current.charAt(1) + current.charAt(2));    // 710 = 700 10
                    }
                    else {
                        if (nextExists) {
                            if ((substrings[i + 1].length() == 1) && (!substrings[i + 1].equals("0"))) {
                                innerList.add(current + substrings[i + 1]); // 720 4 = 7204
                                innerList.add(String.valueOf(current.charAt(0)) + current.charAt(1) + substrings[i + 1]);           // 720 4 = 724
                                innerList.add(current.charAt(0) + "00" + current.charAt(1) + substrings[i + 1]);           // 720 4 = 700 24
                                innerList.add(current.charAt(0) + "00" + current.charAt(1) + current.charAt(2) + substrings[i + 1]);           // 720 4 = 700 204
                                skip = 1;
                            }
                            else {
                                innerList.add(current);
                                innerList.add(current.charAt(0) + "00" + current.charAt(1) + current.charAt(2));
                            }
                        }
                        else {
                            innerList.add(current);
                        }
                    }
                }
                else if (current.charAt(1) == '0'){
                    innerList.add(current); // 706
                    innerList.add(current.charAt(0) + "00" + current.charAt(2));           // 7006
                }
                else {
                    innerList.add(current); // 724
                    innerList.add(current.charAt(0) + "00" + current.charAt(1) + current.charAt(2));           // 724 = 70024
                    innerList.add(String.valueOf(current.charAt(0)) + current.charAt(1) + "0" + current.charAt(2));           // 724 = 7204
                    innerList.add(current.charAt(0) + "00" + current.charAt(1) + "0" + current.charAt(2));           // 724 = 700204
                }
            }
            superList.add(innerList);

            if (skip == 1){
                i += 1;
                //superList.add(new ArrayList<>());
            }
            else if (skip == 2){
                i += 2;
                //superList.add(new ArrayList<>());
                //superList.add(new ArrayList<>());
            }
        }

        for (List<String> list: superList){
            System.out.println(list);
        }
    }

    // Function to enumerate all possible sequences
    public static void enumerateSequences(List<List<String>> lists, List<String> currentSequence, int index, List<List<String>> results) {
        // If we have used all lists, add the currentSequence to results
        if (index == lists.size()) {
            results.add(new ArrayList<>(currentSequence));
            return;
        }

        // Iterate through the substrings in the current list
        for (String s : lists.get(index)) {
            // Add the symbol to the currentSequence
            currentSequence.add(s);

            // Recursively explore the next list
            enumerateSequences(lists, currentSequence, index + 1, results);

            // Backtrack by removing the last symbol to explore other possibilities
            currentSequence.remove(currentSequence.size() - 1);
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
