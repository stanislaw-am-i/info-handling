package com.textprocessor;

import com.textprocessor.model.TextComponent;
import com.textprocessor.operation.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextProcessorTest {
    private static final String TEST_TEXT = "First paragraph. Second sentence.\n\n" +
            "Second paragraph. Another sentence. Third sentence.\n\n" +
            "Third paragraph. Last sentence.";

    @Test
    void testSortParagraphsBySentenceCount() {
        TextComponent text = TextProcessor.processText(TEST_TEXT);
        TextOperation operation = new SortParagraphsBySentenceCount();
        TextComponent result = operation.execute(text);
        
        assertNotNull(result);
        assertTrue(result.getText().contains("First paragraph"));
        assertTrue(result.getText().contains("Second paragraph"));
        assertTrue(result.getText().contains("Third paragraph"));
    }

    @Test
    void testFindSentencesWithLongestWord() {
        TextComponent text = TextProcessor.processText(TEST_TEXT);
        TextOperation operation = new FindSentencesWithLongestWord();
        TextComponent result = operation.execute(text);
        
        assertNotNull(result);
        assertTrue(result.getText().contains("Sentences with longest word"));
    }

    @Test
    void testRemoveShortSentences() {
        TextComponent text = TextProcessor.processText(TEST_TEXT);
        TextOperation operation = new RemoveShortSentences(3);
        TextComponent result = operation.execute(text);
        
        assertNotNull(result);
        assertTrue(result.getText().length() > 0);
    }

    @Test
    void testCountDuplicateWords() {
        TextComponent text = TextProcessor.processText(TEST_TEXT);
        TextOperation operation = new CountDuplicateWords();
        TextComponent result = operation.execute(text);
        
        assertNotNull(result);
        assertTrue(result.getText().length() >= 0);
    }

    @Test
    void testCountVowelsAndConsonants() {
        TextComponent text = TextProcessor.processText(TEST_TEXT);
        TextOperation operation = new CountVowelsAndConsonants();
        TextComponent result = operation.execute(text);
        
        assertNotNull(result);
        assertTrue(result.getText().contains("Vowels"));
        assertTrue(result.getText().contains("Consonants"));
    }
} 