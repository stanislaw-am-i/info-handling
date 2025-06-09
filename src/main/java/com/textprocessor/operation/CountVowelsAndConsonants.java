package com.textprocessor.operation;

import java.util.ArrayList;
import java.util.List;

import com.textprocessor.model.Text;
import com.textprocessor.model.TextComponent;

public class CountVowelsAndConsonants implements TextOperation {
    private static final String VOWELS = "aeiouAEIOU";

    @Override
    public TextComponent execute(TextComponent text) {
        List<String> results = new ArrayList<>();

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int[] counts = countVowelsAndConsonants(sentence);
                results.add(String.format("Sentence: %s\nVowels: %d, Consonants: %d",
                        sentence.getText(), counts[0], counts[1]));
            }
        }

        return new Text(String.join("\n\n", results));
    }

    private int[] countVowelsAndConsonants(TextComponent sentence) {
        int vowels = 0;
        int consonants = 0;

        for (TextComponent word : sentence.getChildren()) {
            String text = word.getText();
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    if (VOWELS.indexOf(c) != -1) {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }
        }

        return new int[]{vowels, consonants};
    }
} 