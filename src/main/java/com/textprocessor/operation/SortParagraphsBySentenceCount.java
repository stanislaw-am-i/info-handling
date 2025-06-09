package com.textprocessor.operation;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.textprocessor.model.Text;
import com.textprocessor.model.TextComponent;

public class SortParagraphsBySentenceCount implements TextOperation {
    @Override
    public TextComponent execute(TextComponent text) {
        List<TextComponent> sortedParagraphs = text.getChildren().stream()
                .sorted(Comparator.comparingInt(TextComponent::getSentenceCount))
                .collect(Collectors.toList());

        Text result = new Text(text.getText());
        sortedParagraphs.forEach(result::add);
        return result;
    }
} 