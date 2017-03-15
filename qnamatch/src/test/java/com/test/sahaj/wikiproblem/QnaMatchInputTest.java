package com.test.sahaj.wikiproblem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by ashish on 2/3/17.
 */
public class QnaMatchInputTest {
    QnaMatchInput qnaMatchInput;
    @Before
    public void setUp() throws Exception {
        qnaMatchInput = QnaMatchTestUtils.getSampleInput();
    }

    @Test
    public void getParagraph() throws Exception {
        assertNotNull(qnaMatchInput);
        assertNotNull(qnaMatchInput.getParagraph());
    }

    @Test
    public void getQuestions() throws Exception {
        assertNotNull(qnaMatchInput);
        assertNotNull(qnaMatchInput.getQuestions());
        assertThat(qnaMatchInput.getQuestions().length, is(QnaMatchTestUtils.QNA_COUNT_UNDER_TEST));
    }

    @Test
    public void getAnswerOptions() throws Exception {
        assertNotNull(qnaMatchInput);
        assertNotNull(qnaMatchInput.getAnswerOptions());
        assertThat(qnaMatchInput.getAnswerOptions().length, is(QnaMatchTestUtils.QNA_COUNT_UNDER_TEST));
    }

}