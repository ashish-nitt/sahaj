package com.test.sahaj.wikiproblem;

import com.test.sahaj.base.Output;

import java.util.Arrays;

/**
 * Created by ashish on 28/2/17.
 */
public class QnaMatchOutput implements Output {
    private final String[] answers;
    public QnaMatchOutput(String[] outputLines) {
        answers = Arrays.copyOf(outputLines, outputLines.length);
    }
    String[] getAnswers() {
        return answers;
    }
}
