package com.test.sahaj.wikiproblem;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ashish on 2/3/17.
 */
public class QnaMatchSolverTest {
    QnaMatchInput qnaMatchInput;
    QnaMatchOutput qnaMatchOutput;
    @Before
    public void setUp() throws Exception {
        qnaMatchInput = QnaMatchTestUtils.getSampleInput();
        qnaMatchOutput = QnaMatchTestUtils.getSampleOutput();
    }

    @Test
    public void solve() throws Exception {
        QnaMatchSolver qnaMatchSolver = new QnaMatchSolver();
        QnaMatchOutput actualOutput = qnaMatchSolver.solve(qnaMatchInput);
        List<String > possibleAnserList = Arrays.asList(qnaMatchInput.getAnswerOptions());
        assertEquals(qnaMatchInput.getAnswerOptions().length, actualOutput.getAnswers().length);
        for(String answer : qnaMatchOutput.getAnswers()) {
            assertTrue(possibleAnserList.contains(answer));
        }
    }

}