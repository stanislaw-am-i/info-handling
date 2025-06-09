package com.textprocessor.model;

public class Text extends AbstractTextComponent {
    public Text(String text) {
        super(text);
    }

    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : children) {
            result.append(component.getText()).append("\n");
        }
        return result.toString().trim();
    }
} 