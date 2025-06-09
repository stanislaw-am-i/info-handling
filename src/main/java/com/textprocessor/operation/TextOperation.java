package com.textprocessor.operation;

import com.textprocessor.model.TextComponent;

public interface TextOperation {
    TextComponent execute(TextComponent text);
} 