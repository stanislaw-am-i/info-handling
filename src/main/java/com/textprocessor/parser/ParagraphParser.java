package com.textprocessor.parser;

import com.textprocessor.model.Paragraph;
import com.textprocessor.model.TextComponent;

public class ParagraphParser extends TextParser {
    private static final String PARAGRAPH_DELIMITER = "\\n\\s*\\n";

    @Override
    public TextComponent parse(String text) {
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);
        TextComponent result = new Paragraph(text);
        
        for (String paragraph : paragraphs) {
            if (!paragraph.trim().isEmpty()) {
                TextComponent paragraphComponent = new Paragraph(paragraph.trim());
                if (nextParser != null) {
                    TextComponent parsedComponent = nextParser.parse(paragraph.trim());
                    paragraphComponent.add(parsedComponent);
                }
                result.add(paragraphComponent);
            }
        }
        
        return result;
    }
} 