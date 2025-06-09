package com.textprocessor.model;

public class Paragraph extends AbstractTextComponent {
    public Paragraph(String text) {
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
} 