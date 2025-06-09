package com.textprocessor.operation;

import java.util.ArrayList;
import java.util.List;

import com.textprocessor.model.Sentence;
import com.textprocessor.model.Text;
import com.textprocessor.model.TextComponent;

public class FindSentencesWithLongestWord implements TextOperation {
    @Override
    public TextComponent execute(TextComponent text) {
        int maxWordLength = findMaxWordLength(text);
        List<TextComponent> sentencesWithLongestWord = new ArrayList<>();

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                if (containsWordOfLength(sentence, maxWordLength)) {
                    sentencesWithLongestWord.add(sentence);
                }
            }
        }

        Text result = new Text("Sentences with longest word:");
        sentencesWithLongestWord.forEach(result::add);
        return result;
    }

    private int findMaxWordLength(TextComponent component) {
        int maxLength = 0;
        for (TextComponent child : component.getChildren()) {
            if (child instanceof Sentence) {
                for (TextComponent word : child.getChildren()) {
                    maxLength = Math.max(maxLength, word.getText().length());
                }
            }
            maxLength = Math.max(maxLength, findMaxWordLength(child));
        }
        return maxLength;
    }

    private boolean containsWordOfLength(TextComponent sentence, int length) {
        return sentence.getChildren().stream()
                .anyMatch(word -> word.getText().length() == length);
    }
} 