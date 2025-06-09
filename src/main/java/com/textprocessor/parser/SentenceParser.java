package com.textprocessor.parser;

import com.textprocessor.model.Sentence;
import com.textprocessor.model.TextComponent;

public class SentenceParser extends TextParser {
    private static final String SENTENCE_DELIMITER = "(?<=[.!?])\\s+";

    @Override
    public TextComponent parse(String text) {
        String[] sentences = text.split(SENTENCE_DELIMITER);
        TextComponent result = new Sentence(text);
        
        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                TextComponent sentenceComponent = new Sentence(sentence.trim());
                if (nextParser != null) {
                    TextComponent parsedComponent = nextParser.parse(sentence.trim());
                    sentenceComponent.add(parsedComponent);
                }
                result.add(sentenceComponent);
            }
        }
        
        return result;
    }
} 