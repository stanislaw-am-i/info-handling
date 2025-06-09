package com.textprocessor.parser;

import com.textprocessor.model.TextComponent;
import com.textprocessor.model.Word;

public class WordParser extends TextParser {
    private static final String WORD_DELIMITER = "\\s+";

    @Override
    public TextComponent parse(String text) {
        String[] words = text.split(WORD_DELIMITER);
        TextComponent result = new Word(text);
        
        for (String word : words) {
            if (!word.trim().isEmpty()) {
                TextComponent wordComponent = new Word(word.trim());
                if (nextParser != null) {
                    TextComponent parsedComponent = nextParser.parse(word.trim());
                    wordComponent.add(parsedComponent);
                }
                result.add(wordComponent);
            }
        }
        
        return result;
    }
} 