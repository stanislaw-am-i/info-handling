package com.textprocessor.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.textprocessor.model.Text;
import com.textprocessor.model.TextComponent;

public class CountDuplicateWords implements TextOperation {
    @Override
    public TextComponent execute(TextComponent text) {
        Map<String, Integer> wordCount = new HashMap<>();
        countWords(text, wordCount);

        String result = wordCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));

        return new Text(result);
    }

    private void countWords(TextComponent component, Map<String, Integer> wordCount) {
        for (TextComponent child : component.getChildren()) {
            if (child.getChildren().isEmpty()) {
                String word = child.getText().toLowerCase();
                wordCount.merge(word, 1, Integer::sum);
            } else {
                countWords(child, wordCount);
            }
        }
    }
} 