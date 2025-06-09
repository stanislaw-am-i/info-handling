package com.textprocessor.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextComponent implements TextComponent {
    protected List<TextComponent> children = new ArrayList<>();
    protected String text;

    public AbstractTextComponent(String text) {
        this.text = text;
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        children.remove(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return children;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int getWordCount() {
        return children.stream()
                .mapToInt(TextComponent::getWordCount)
                .sum();
    }

    @Override
    public int getSentenceCount() {
        return children.stream()
                .mapToInt(TextComponent::getSentenceCount)
                .sum();
    }
} 