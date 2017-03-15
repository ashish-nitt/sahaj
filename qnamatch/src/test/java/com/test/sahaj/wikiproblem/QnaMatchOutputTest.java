package com.test.sahaj.wikiproblem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by ashish on 2/3/17.
 */
public class QnaMatchOutputTest {
    QnaMatchOutput qnaMatchOutput;
    @Before
    public void setUp() throws Exception {
        qnaMatchOutput = QnaMatchTestUtils.getSampleOutput();
    }

    @Test
    public void getAnswers() throws Exception {
        assertNotNull(qnaMatchOutput);
        assertNotNull(qnaMatchOutput.getAnswers());
        assertThat(qnaMatchOutput.getAnswers().length, is(5));
    }

}