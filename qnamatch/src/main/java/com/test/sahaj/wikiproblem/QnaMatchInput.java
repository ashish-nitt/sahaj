package com.test.sahaj.wikiproblem;

import com.test.sahaj.base.Input;

import java.util.Arrays;

/**
 * Created by ashish on 28/2/17.
 */
public class QnaMatchInput implements Input {
    private final String paragraph;
    private final String[] questions;
    private final String[] answerOptions;

    public QnaMatchInput(String[] inputLines) {
        paragraph = inputLines.length > 0 ? inputLines[0] : null;
        questions = inputLines.length > 1 ? Arrays.copyOfRange(inputLines, 1, inputLines.length - 1) : null;
        answerOptions = inputLines.length > 1 ? inputLines[inputLines.length - 1].split(";") : null;
    }
    public String getParagraph() {
        return paragraph;
    }
    public String[] getQuestions() {
        return questions;
    }
    public String[] getAnswerOptions() {
        return answerOptions;
    }
}
