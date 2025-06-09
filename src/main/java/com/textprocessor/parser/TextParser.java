package com.textprocessor.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.textprocessor.model.TextComponent;

public abstract class TextParser {
    protected static final Logger logger = LogManager.getLogger(TextParser.class);
    protected TextParser nextParser;

    public void setNextParser(TextParser parser) {
        this.nextParser = parser;
    }

    public abstract TextComponent parse(String text);
} 