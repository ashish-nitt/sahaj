package com.test.sahaj.wikiproblem;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by ashish on 14/3/17.
 */
public class QnaMatchTestUtils {
    public static final int QNA_COUNT_UNDER_TEST = 5;
    public static QnaMatchInput getSampleInput() {
        try {
            List<String> lines = IOUtils.readLines(QnaMatchTestUtils.class.getResourceAsStream("/testInput.txt"));
            return new QnaMatchInput(lines.toArray(new String[lines.size()]));
        } catch (IOException e) {
            return null;
        }
    }
    public static QnaMatchOutput getSampleOutput() {
        try {
            List<String> lines = IOUtils.readLines(QnaMatchTestUtils.class.getResourceAsStream("/testOutput.txt"));
            return new QnaMatchOutput(lines.toArray(new String[lines.size()]));
        } catch (IOException e) {
            return null;
        }
    }
}
