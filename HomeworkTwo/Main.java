package HomeworkTwo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        WordCounter(FileReaderMethod(Paths.get((("textFile.txt")))));
    }

    public static String FileReaderMethod(Path file) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.toAbsolutePath().toString()));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String lineSeparator = System.getProperty("line.separator");
            while((line = reader.readLine()) != null ) {
                stringBuilder.append(line);
                stringBuilder.append(lineSeparator);
            }
            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return null;
    }

    public static void WordCounter(String text) {
        try {
            String[] words = text.split("\\p{P}*[ \\t\\n\\r]+");
            TreeMap<String, Integer> wordCount = new TreeMap<>();
            int totalWordsAmount = 0;
            int mostPopularWordAmount = 0;

            for (int i = 0; i < words.length; i++) {
                totalWordsAmount++;
                String word = words[i].toLowerCase();
                if (!wordCount.containsKey(word)) {
                    wordCount.put(word, 0);
                }
                wordCount.put(word, wordCount.get(word) + 1);
                if (wordCount.get(word).intValue() > mostPopularWordAmount) {
                    mostPopularWordAmount = wordCount.get(word).intValue();
                }
            }

            for (String word : wordCount.keySet()) {
                int wordAmount = wordCount.get(word);
                System.out.print(word + " " + wordCount.get(word) + " ");
                System.out.format("%.2f", (double) 100 * wordAmount / totalWordsAmount);
                System.out.println("% ");

            }

            Collection<String> collection = wordCount.keySet();
            for (String word : collection) {
                int wordAmount = wordCount.get(word);
                if (word != null) {
                    if (mostPopularWordAmount == wordAmount) {
                        System.out.print("The most popular word: "
                                + word + " ");
                        System.out.format("%.2f", (double) 100 * wordAmount / totalWordsAmount);
                        System.out.println("%");
                    }
                }
            }

            System.out.println("Words amount: " + totalWordsAmount);
        } catch (NullPointerException e) {
            System.out.println("please input the right file way");
        }
    }

}
