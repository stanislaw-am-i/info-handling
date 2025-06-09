package com.textprocessor.operation;

import com.textprocessor.model.Paragraph;
import com.textprocessor.model.Text;
import com.textprocessor.model.TextComponent;

public class RemoveShortSentences implements TextOperation {
    private final int minWordCount;

    public RemoveShortSentences(int minWordCount) {
        this.minWordCount = minWordCount;
    }

    @Override
    public TextComponent execute(TextComponent text) {
        Text result = new Text(text.getText());

        for (TextComponent paragraph : text.getChildren()) {
            Paragraph newParagraph = new Paragraph(paragraph.getText());
            for (TextComponent sentence : paragraph.getChildren()) {
                if (sentence.getWordCount() >= minWordCount) {
                    newParagraph.add(sentence);
                }
            }
            if (!newParagraph.getChildren().isEmpty()) {
                result.add(newParagraph);
            }
        }

        return result;
    }
} 