package com.textprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.textprocessor.interpreter.ExpressionEvaluator;
import com.textprocessor.model.TextComponent;
import com.textprocessor.operation.CountDuplicateWords;
import com.textprocessor.operation.CountVowelsAndConsonants;
import com.textprocessor.operation.FindSentencesWithLongestWord;
import com.textprocessor.operation.RemoveShortSentences;
import com.textprocessor.operation.SortParagraphsBySentenceCount;
import com.textprocessor.operation.TextOperation;
import com.textprocessor.parser.ParagraphParser;
import com.textprocessor.parser.SentenceParser;
import com.textprocessor.parser.TextParser;
import com.textprocessor.parser.WordParser;

public class TextProcessor {
    private static final Logger logger = LogManager.getLogger(TextProcessor.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            logger.error("Please provide input file path");
            return;
        }

        try {
            String inputText = Files.readString(Paths.get(args[0]));
            TextComponent processedText = processText(inputText);

            // Execute all operations
            executeOperation(new SortParagraphsBySentenceCount(), processedText, "Sorted paragraphs by sentence count:");
            executeOperation(new FindSentencesWithLongestWord(), processedText, "Sentences with longest word:");
            executeOperation(new RemoveShortSentences(3), processedText, "Text after removing short sentences:");
            executeOperation(new CountDuplicateWords(), processedText, "Duplicate words count:");
            executeOperation(new CountVowelsAndConsonants(), processedText, "Vowels and consonants count:");

        } catch (IOException e) {
            logger.error("Error reading file: " + e.getMessage());
        }
    }

    public static TextComponent processText(String text) {
        // Evaluate arithmetic expressions
        text = ExpressionEvaluator.evaluateExpressions(text);

        // Set up parser chain
        TextParser paragraphParser = new ParagraphParser();
        TextParser sentenceParser = new SentenceParser();
        TextParser wordParser = new WordParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(wordParser);

        // Parse text
        return paragraphParser.parse(text);
    }

    private static void executeOperation(TextOperation operation, TextComponent text, String header) {
        logger.info("\n" + header);
        TextComponent result = operation.execute(text);
        logger.info(result.getText());
    }
} 