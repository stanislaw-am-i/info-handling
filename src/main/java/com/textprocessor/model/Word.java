package com.textprocessor.model;

public class Word extends AbstractTextComponent {
    public Word(String text) {
        super(text);
    }

    @Override
    public int getWordCount() {
        return 1;
    }

    @Override
    public String getText() {
        return text;
    }
} 