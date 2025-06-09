package com.textprocessor.model;

public class Sentence extends AbstractTextComponent {
    public Sentence(String text) {
        super(text);
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : children) {
            result.append(component.getText()).append(" ");
        }
        return result.toString().trim();
    }

    @Override
    public int getSentenceCount() {
        return 1;
    }
} 