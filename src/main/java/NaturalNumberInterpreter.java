import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaturalNumberInterpreter {

    public static void main(String[] args) {

        // Prompt the user for input
        System.out.print("Enter a sequence of numbers separated by spaces: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        List<List<String>> superList = new ArrayList<>();

        generateAllAmbiguities(input, superList);

        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();

        // Start the enumeration
        enumerateAllAmbiguities(superList, currentSequence, 0, alternations);

        // Print the results
        System.out.println("Input is: " + input);

        int i = 1;
        for (List<String> ambiguity : alternations) {
            StringBuilder sb = new StringBuilder();
            for (String s : ambiguity) {
                sb.append(s);
            }

            // Call isValidGreekPhoneNumber method to validate each generated number, and print if the number is valid or not
            String validationMessage = isValidGreekPhoneNumber(sb.toString().replaceAll(" ", "")) ? "[phone number: VALID]" : "[phone number: INVALID]";
            System.out.println("Interpretation " + i + ": " + sb.toString().replaceAll(" ", "") + "\t" + validationMessage);

            i++;
        }

    }

    /* Generate all ambiguities by splitting the input string in substrings.
    For each substring, check length and other conditions (starts, ends with 0) and
    create a list with all possible interpretations. Add each interpretation to an innerList.
    At the end, add each innerList to a superList. */
    public static void generateAllAmbiguities(String input, List<List<String>> superList) {
        // Split the string into substrings, separated by whitespaces
        String[] substrings = input.split(" ");

        // Iterate over each substring
        for (int i = 0 ; i < substrings.length; i++) {

            int skip = 0;

            boolean nextExists = (i + 1 < substrings.length);       // Variable to check later if array index out of bounds, when i + 1
            boolean nextNextExists = (i + 2 < substrings.length);   // Variable to check later if array index out of bounds, when i + 2

            List<String> innerList = new ArrayList<>();

            String current = substrings[i];         // Get the current substring, i.e. "69" from the string "69 30 6 6 4"

            if (current.length() == 1){             // Check if the substring has 1 digit only
                innerList.add(current);
            }
            else if (current.length() == 2) {            // Check if the substring has 2 digits
                if  (current.startsWith("1")){          // Check if the substring starts with 1
                    innerList.add(current);
                }
                else if (current.endsWith("0")) {       // Check if the substring ends with '0'
                    if (nextExists){
                        if ((substrings[i+1].length() == 1) && (!substrings[i+1].equals("0"))){     // Check if the substring has 1 digit and is not 0
                            innerList.add(current.charAt(0) + substrings[i + 1]);                   // i.e. 30 6 = 36
                            innerList.add(current + substrings[i + 1]);                             // i.e. 30 6 = 306
                            skip = 1;
                        }
                        else {
                            innerList.add(current);
                        }
                    }
                    else {
                        innerList.add(current);             //i.e. 60 0 = 60
                    }
                }
                else {
                    innerList.add((current));                                       // i.e. 69
                    innerList.add(current.charAt(0) + "0" + current.charAt(1));     // i.e. 609
                }
            }
            else{                                                                   // The substring has 3 digits
                if (current.endsWith("00")) {                                       // Check if the substring ends with '00'
                    if (nextExists) {
                        if ((substrings[i + 1].length() == 1) && (!substrings[i + 1].equals("0"))) {        // Check if the next substring has 1 digit and is not 0
                            innerList.add(String.valueOf(current.charAt(0)) + current.charAt(1) + substrings[i + 1]);         // i.e. 700 4 = 704
                            innerList.add(current + substrings[i + 1]);                                                       // i.e. 700 4 = 7004
                            skip = 1;
                        } else if (substrings[i + 1].length() == 2) {               // Check if the next substring ends has 2 digits
                            if (substrings[i + 1].startsWith("1")) {
                                innerList.add(current.charAt(0) + substrings[i + 1]);         // i.e. 700 15 = 715
                                innerList.add(current + substrings[i + 1]);                   // i.e. 700 15 = 70015
                                skip = 1;
                            } else if (substrings[i + 1].endsWith("0")) {           // Check if the next substring ends with '0'
                                if (nextNextExists){
                                    if (substrings[i + 2].length() == 1) {          // Check if the next next substring has 1 digit
                                        innerList.add(current + substrings[i + 1] + substrings[i + 2]);                          // i.e. 700 20 4 = 700204
                                        innerList.add(current + substrings[i + 1].charAt(0) + substrings[i + 2]);                // i.e. 700 20 4 = 70024
                                        innerList.add(String.valueOf(current.charAt(0)) + substrings[i + 1].charAt(0) + substrings[i + 2]);         // i.e. 700 20 4 = 724
                                        innerList.add(current.charAt(0) + substrings[i + 1] + substrings[i + 2]);                                   // i.e. 700 20 4 = 7204
                                        skip = 2;
                                    }
                                } else {
                                    innerList.add(current.charAt(0) + substrings[i + 1]);          // i.e. 700 20 = 720
                                    innerList.add(current + substrings[i + 1]);                    // i.e. 700 20 = 70020
                                    skip = 1;
                                }
                            } else {
                                innerList.add((current.charAt(0) + substrings[i + 1]));                                                                     // i.e. 700 24 = 724
                                innerList.add((String.valueOf(current.charAt(0)) + substrings[i + 1].charAt(0) + "0" + substrings[i + 1].charAt(1)));       // i.e. 700 24 = 7204
                                innerList.add((current + substrings[i + 1]));                                                                               // i.e. 700 24 = 70024
                                innerList.add((current + substrings[i + 1].charAt(0) + "0" + substrings[i + 1].charAt(1)));                                 // i.e. 700 24 = 700204
                                skip = 1;
                            }
                        }
                        else {
                            innerList.add(current);
                        }
                    }
                    else {
                        innerList.add(current);         // i.e. 700
                    }
                }
                else if (current.endsWith("0")){
                    if  (current.charAt(1) == '1'){
                        innerList.add(current);                                                             // i.e. 710
                        innerList.add(current.charAt(0) + "00" + current.charAt(1) + current.charAt(2));    // i.e. 710 = 700 10
                    }
                    else {
                        if (nextExists) {
                            if ((substrings[i + 1].length() == 1) && (!substrings[i + 1].equals("0"))) {                            // Check if the next substring has 1 digit and is not 0
                                innerList.add(current + substrings[i + 1]); // 720 4 = 7204
                                innerList.add(String.valueOf(current.charAt(0)) + current.charAt(1) + substrings[i + 1]);                       // i.e. 720 4 = 724
                                innerList.add(current.charAt(0) + "00" + current.charAt(1) + substrings[i + 1]);                                // i.e. 720 4 = 700 24
                                innerList.add(current.charAt(0) + "00" + current.charAt(1) + current.charAt(2) + substrings[i + 1]);            // i.e. 720 4 = 700 204
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
                    innerList.add(current.charAt(0) + "00" + current.charAt(2));           // i.e. 7006
                }
                else {
                    innerList.add(current); // 724
                    innerList.add(current.charAt(0) + "00" + current.charAt(1) + current.charAt(2));                            // i.e. 724 = 70024
                    innerList.add(String.valueOf(current.charAt(0)) + current.charAt(1) + "0" + current.charAt(2));             // i.e. 724 = 7204
                    innerList.add(current.charAt(0) + "00" + current.charAt(1) + "0" + current.charAt(2));                      // i.e. 724 = 700204
                }
            }
            superList.add(innerList);

            if (skip == 1){             // Skip the next substring, because it was concatenated with the current string.
                i += 1;
            }
            else if (skip == 2){        // Skip the next 2 substrings, because it was concatenated with the current string.
                i += 2;
            }
        }
    }

    // Function to enumerate all possible ambiguities
    public static void enumerateAllAmbiguities(List<List<String>> lists, List<String> ambiguitiesList, int index, List<List<String>> results) {
        // If we have used all lists, add the ambiguitiesList to results
        if (index == lists.size()) {
            results.add(new ArrayList<>(ambiguitiesList));
            return;
        }

        // Iterate through the substrings in the current list
        for (String s : lists.get(index)) {
            // Add the substring to the ambiguitiesList
            ambiguitiesList.add(s);

            // Recursively explore the next list
            enumerateAllAmbiguities(lists, ambiguitiesList, index + 1, results);

            // Backtrack by removing the last substring to explore other possibilities
            ambiguitiesList.remove(ambiguitiesList.size() - 1);
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
