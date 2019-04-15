package net.mingism;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ngram {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line = "2,the";
        String[] paragraphAsArray = paragraphToArray(PARAGRAPH);

        int length = getLength(line);
        String text = getText(line);
        String result = predict(length, text, paragraphAsArray);
        System.out.println(result);

    }

//    public static void main(String[] args) {
//        paragraphToArray(PARAGRAPH);
//    }

    private static int getLength(String input) {
        return Integer.valueOf(input.split(",")[0]);
    }

    private static String getText(String input) {
        return input.split(",")[1];
    }

    private static String predict(int length, String text, String[] paragraph) {
        List<List<String>> arrayByLength = produceArrayByLength(paragraph, length);
        System.out.println(arrayByLength);
        return length + ";" + text + Arrays.asList(paragraph);
    }

    private static List<List<String>> produceArrayByLength(String[] paragraph, int length) {
        List<List<String>> result = new ArrayList<>();

        for( int j=0; j<paragraph.length; j++) {
            List<String> subList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (j+i <paragraph.length) {
                    subList.add(paragraph[j+i]);
                }
            }
            result.add(subList);
        }
        return result;
    }

    private static String[] paragraphToArray(String paragraph) {
        String filteredParagraph = paragraph.replaceAll("[^a-zA-Z0-9 ]", "");
        return filteredParagraph.split(" ");
    }

    private static final String PARAGRAPH = "Mary had a little lamb its fleece was white as snow; " +
            "And everywhere that Mary went, the lamb was sure to go. " +
            "It followed her to school one day, which was against the rule; " +
            "It made the children laugh and play, to see a lamb at school. " +
            "And so the teacher turned it out, but still it lingered near, " +
            "And waited patiently about till Mary did appear. " +
            "\"Why does the lamb love Mary so?\" the eager children cry;\" Why, Mary loves the lamb, you know\" the teacher did reply.\"";

// Algorithm
// 1. turn paragraph to array of strings
// 2. turn array of strings to array of array of strings
// 3. keep a hashmap of <text, occurance>
// 4. walk through the array of array of strings to populate the // hashmap
// 5. walk through the hashmap, populate a tree map of <string , // percentage of occurance>.
// 6. turn the treemap into required string input

}
