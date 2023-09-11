import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tests {

    @Test
    public void test1() {
        // Test with 3-digit substring followed by 2-digit substring

        List<List<String>> superList = new ArrayList<>();
        NaturalNumberInterpreter.generateAllAmbiguities("204 24", superList);

        List<List<String>> listOfLists = new ArrayList<>();

        listOfLists.add(Arrays.asList("204", "2004"));
        listOfLists.add(Arrays.asList("24", "204"));

        Assertions.assertEquals(listOfLists, superList);

        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();;
        List<List<String>> expectedResults  = new ArrayList<>();

        expectedResults.add(Arrays.asList("204", "24"));
        expectedResults.add(Arrays.asList("204", "204"));
        expectedResults.add(Arrays.asList("2004", "24"));
        expectedResults.add(Arrays.asList("2004", "204"));

        NaturalNumberInterpreter.enumerateAllAmbiguities(listOfLists, currentSequence, 0, alternations);

        Assertions.assertEquals(alternations, expectedResults);
    }

    @Test
    public void test2() {
        // Test with 3-digit substring with 00s followed by 2-digit and 1-digit substring

        List<List<String>> superList;
        superList = new ArrayList<>();
        NaturalNumberInterpreter.generateAllAmbiguities("700 20 4", superList);

        List<List<String>> listOfLists = new ArrayList<>();

        listOfLists.add(Arrays.asList("700204", "70024", "724", "7204"));

        Assertions.assertEquals(listOfLists, superList);

        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();;
        List<List<String>> expectedResults  = new ArrayList<>();

        expectedResults.add(Collections.singletonList("700204"));
        expectedResults.add(Collections.singletonList("70024"));
        expectedResults.add(Collections.singletonList("724"));
        expectedResults.add(Collections.singletonList("7204"));

        NaturalNumberInterpreter.enumerateAllAmbiguities(listOfLists, currentSequence, 0, alternations);

        Assertions.assertEquals(alternations, expectedResults);
    }

    @Test
    public void test3() {
        // Test with 2-digit substring followed by 1-digit, 1-digit, 2-digit substring

        List<List<String>> superList;
        superList = new ArrayList<>();
        NaturalNumberInterpreter.generateAllAmbiguities("30 2 5 58", superList);

        List<List<String>> listOfLists = new ArrayList<>();

        listOfLists.add(Arrays.asList("32", "302"));
        listOfLists.add(Collections.singletonList("5"));
        listOfLists.add(Arrays.asList("58", "508"));

        Assertions.assertEquals(listOfLists, superList);

        List<List<String>> expectedResults = new ArrayList<>();;
        List<List<String>> alternations = new ArrayList<>();;
        List<String> currentSequence = new ArrayList<>();;

        expectedResults.add(Arrays.asList("32", "5", "58"));
        expectedResults.add(Arrays.asList("32", "5", "508"));
        expectedResults.add(Arrays.asList("302", "5", "58"));
        expectedResults.add(Arrays.asList("302", "5", "508"));

        NaturalNumberInterpreter.enumerateAllAmbiguities(listOfLists, currentSequence, 0, alternations);

        Assertions.assertEquals(alternations, expectedResults);

    }

    @Test
    public void test4() {
        List<List<String>> superList;

        // Test with number starting with 69, includes 3-digit substring
        superList = new ArrayList<>();
        NaturalNumberInterpreter.generateAllAmbiguities("6 97 400 23 7 40 5", superList);

        List<List<String>> listOfLists;
        listOfLists = new ArrayList<>();

        listOfLists.add(Collections.singletonList("6"));
        listOfLists.add(Arrays.asList("97", "907"));
        listOfLists.add(Arrays.asList("423", "4203", "40023", "400203"));
        listOfLists.add(Collections.singletonList("7"));
        listOfLists.add(Arrays.asList("45", "405"));

        Assertions.assertEquals(listOfLists, superList);

        List<List<String>> expectedResults = new ArrayList<>();
        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();

        expectedResults.add(Arrays.asList("6", "97", "423", "7", "45"));
        expectedResults.add(Arrays.asList("6", "97", "423", "7", "405"));
        expectedResults.add(Arrays.asList("6", "97", "4203", "7", "45"));
        expectedResults.add(Arrays.asList("6", "97", "4203", "7", "405"));
        expectedResults.add(Arrays.asList("6", "97", "40023", "7", "45"));
        expectedResults.add(Arrays.asList("6", "97", "40023", "7", "405"));
        expectedResults.add(Arrays.asList("6", "97", "400203", "7", "45"));
        expectedResults.add(Arrays.asList("6", "97", "400203", "7", "405"));
        expectedResults.add(Arrays.asList("6", "907", "423", "7", "45"));
        expectedResults.add(Arrays.asList("6", "907", "423", "7", "405"));
        expectedResults.add(Arrays.asList("6", "907", "4203", "7", "45"));
        expectedResults.add(Arrays.asList("6", "907", "4203", "7", "405"));
        expectedResults.add(Arrays.asList("6", "907", "40023", "7", "45"));
        expectedResults.add(Arrays.asList("6", "907", "40023", "7", "405"));
        expectedResults.add(Arrays.asList("6", "907", "400203", "7", "45"));
        expectedResults.add(Arrays.asList("6", "907", "400203", "7", "405"));

        NaturalNumberInterpreter.enumerateAllAmbiguities(listOfLists, currentSequence, 0, alternations);

        Assertions.assertEquals(alternations, expectedResults);
    }

    @Test
    public void test5() {
        List<List<String>> superList;

        // Test with number starting with 210, includes 2-digit substring
        superList = new ArrayList<>();
        NaturalNumberInterpreter.generateAllAmbiguities("2 10 6 9 30 6 6 4", superList);

        List<List<String>> listOfLists = new ArrayList<>();

        listOfLists.add(Collections.singletonList("2"));
        listOfLists.add(Collections.singletonList("10"));
        listOfLists.add(Collections.singletonList("6"));
        listOfLists.add(Collections.singletonList("9"));
        listOfLists.add(Arrays.asList("36", "306"));
        listOfLists.add(Collections.singletonList("6"));
        listOfLists.add(Collections.singletonList("4"));

        Assertions.assertEquals(listOfLists, superList);

        List<List<String>> expectedResults = new ArrayList<>();
        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();

        expectedResults.add(Arrays.asList("2", "10", "6", "9", "36", "6","4"));
        expectedResults.add(Arrays.asList("2", "10", "6" , "9", "306", "6","4"));

        NaturalNumberInterpreter.enumerateAllAmbiguities(listOfLists, currentSequence, 0, alternations);

        Assertions.assertEquals(alternations, expectedResults);
    }

    @Test
    public void test8(){
        List<List<String>> superList = new ArrayList<>();

        // Test with number starting with 210, includes two 2-digit substrings
        NaturalNumberInterpreter.generateAllAmbiguities("2 10 69 30 6 6 4", superList);

        List<List<String>> listOfLists = new ArrayList<>();

        listOfLists.add(Collections.singletonList("2"));
        listOfLists.add(Collections.singletonList("10"));
        listOfLists.add(Arrays.asList("69", "609"));
        listOfLists.add(Arrays.asList("36", "306"));
        listOfLists.add(Collections.singletonList("6"));
        listOfLists.add(Collections.singletonList("4"));

        Assertions.assertEquals(listOfLists, superList);

        List<List<String>> expectedResults = new ArrayList<>();
        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();

        expectedResults.add(Arrays.asList("2", "10", "69", "36", "6","4"));
        expectedResults.add(Arrays.asList("2", "10", "69", "306", "6","4"));
        expectedResults.add(Arrays.asList("2", "10", "609", "36", "6","4"));
        expectedResults.add(Arrays.asList("2", "10", "609", "306", "6","4"));

        NaturalNumberInterpreter.enumerateAllAmbiguities(listOfLists, currentSequence, 0, alternations);

        Assertions.assertEquals(alternations, expectedResults);
    }


    @Test
    public void test9(){
        List<List<String>> superList =  new ArrayList<>();

        // Test with number starting with 0030, includes one 3-digit substring and  one 3-digit substrings
        NaturalNumberInterpreter.generateAllAmbiguities("0 0 30 69 700 24 1 3 50 2", superList);

        List<List<String>> listOfLists = new ArrayList<>();

        listOfLists.add(Collections.singletonList("0"));
        listOfLists.add(Collections.singletonList("0"));
        listOfLists.add(Collections.singletonList("30"));
        listOfLists.add(Arrays.asList("69", "609"));
        listOfLists.add(Arrays.asList("724", "7204","70024","700204"));
        listOfLists.add(Collections.singletonList("1"));
        listOfLists.add(Collections.singletonList("3"));
        listOfLists.add(Arrays.asList("52","502"));

        Assertions.assertEquals(listOfLists, superList);

        List<List<String>> expectedResults = new ArrayList<>();;
        List<List<String>> alternations = new ArrayList<>();
        List<String> currentSequence = new ArrayList<>();

        expectedResults.add(Arrays.asList("0", "0", "30", "69", "724", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "69", "724", "1", "3", "502"));
        expectedResults.add(Arrays.asList("0", "0", "30", "69", "7204", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "69", "7204", "1", "3", "502"));
        expectedResults.add(Arrays.asList("0", "0", "30", "69", "70024", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "69", "70024", "1", "3", "502"));
        expectedResults.add(Arrays.asList("0", "0", "30", "69", "700204", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "69", "700204", "1", "3", "502"));

        expectedResults.add(Arrays.asList("0", "0", "30", "609", "724", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "609", "724", "1", "3", "502"));
        expectedResults.add(Arrays.asList("0", "0", "30", "609", "7204", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "609", "7204", "1", "3", "502"));
        expectedResults.add(Arrays.asList("0", "0", "30", "609", "70024", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "609", "70024", "1", "3", "502"));
        expectedResults.add(Arrays.asList("0", "0", "30", "609", "700204", "1", "3", "52"));
        expectedResults.add(Arrays.asList("0", "0", "30", "609", "700204", "1", "3", "502"));

        NaturalNumberInterpreter.enumerateAllAmbiguities(listOfLists, currentSequence, 0, alternations);

        Assertions.assertEquals(alternations, expectedResults);


    }

    @Test
    public void isValidGreekPhoneNumberTest(){
        Assertions.assertFalse(NaturalNumberInterpreter.isValidGreekPhoneNumber("30 2 5 58".replaceAll(" ", "")));
        Assertions.assertTrue(NaturalNumberInterpreter.isValidGreekPhoneNumber("2 10 69 30 6 6 4".replaceAll(" ", "")));
        Assertions.assertFalse(NaturalNumberInterpreter.isValidGreekPhoneNumber("2 10 69 30 6 60 4".replaceAll(" ", "")));
        Assertions.assertTrue(NaturalNumberInterpreter.isValidGreekPhoneNumber("0 0 30 69 74 0 9 22 52".replaceAll(" ", "")));
    }
}
