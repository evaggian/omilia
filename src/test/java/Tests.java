import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    @Test
    public void ambiguitiesTest(){

        List<String> results = new ArrayList<>();
        List<String> expectedResults = new ArrayList<>();

        /*NaturalNumberInterpreter.generateAlternatives("2 10 6 9 30 6 6 4", results, 0);

        expectedResults.add("2 10 6 9 306 6 4");
        expectedResults.add("2 16 9 306 6 4");
        expectedResults.add("2 106 9 306 6 4");
        expectedResults.add("2 106 9 36 6 4");
        expectedResults.add("2 10 6 9 36 6 4");
        expectedResults.add("2 16 9 36 6 4");
        expectedResults.add("2 16 9 30 6 6 4");
        expectedResults.add("2 106 9 30 6 6 4");

        Assertions.assertTrue(expectedResults.size() == results.size() && expectedResults.containsAll(results));*/

    }

    @Test
    public void validationNumberTest(){
        Assertions.assertFalse(NaturalNumberInterpreter.isValidGreekPhoneNumber("30 2 5 58".replaceAll(" ", "")));
        Assertions.assertTrue(NaturalNumberInterpreter.isValidGreekPhoneNumber("2 10 69 30 6 6 4".replaceAll(" ", "")));
        Assertions.assertFalse(NaturalNumberInterpreter.isValidGreekPhoneNumber(" 2 10 69 30 6 60 4".replaceAll(" ", "")));
        Assertions.assertTrue(NaturalNumberInterpreter.isValidGreekPhoneNumber("0 0 30 69 74 0 9 22 52".replaceAll(" ", "")));



        // 700 30 2


        // 724 -> 700 20 4


    }
}
