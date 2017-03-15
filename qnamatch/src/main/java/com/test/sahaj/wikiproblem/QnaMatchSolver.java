package com.test.sahaj.wikiproblem;

import com.test.sahaj.base.Solution;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ashish on 28/2/17.
 */
public class QnaMatchSolver implements Solution<QnaMatchInput, QnaMatchOutput> {
    private final static List<String> VALID_SUFFIX = new ArrayList<String>() { {
        add("S");
        add("ES");
        add("ED");
    } };
    private static List<List<String>> getWordsFromSentences(String[] sentences) {
        List<List<String>> wordsFromEachSentence =
                Stream.generate(ArrayList<String>::new).limit(sentences.length).collect(Collectors.toList());
        for(int i = 0; i <sentences.length; i++) {
            wordsFromEachSentence.get(i).addAll(OpenNLPParseUtils.getImportantWordsFromWordList(
                    OpenNLPParseUtils.getWordsFromSentence(sentences[i])));
        }
        return wordsFromEachSentence;
    }

    private static int[][] getCommonWordsCountScore(List<List<String>> ll1, List<List<String>> ll2) {
        final int size1 = ll1.size();
        final int size2 = ll2.size();
        int[][] commonCounts = new int[size1][size2];
        for (int i = 0; i < size1; i++) {
            final List<String> l1 = ll1.get(i);
            for (int j = 0; j < size2; j++) {
                final List<String> l2 = ll2.get(j);

                int count = 0;
                for (String word : l1) {
                    for (String word1 : l2) {
                        if (word1.equals(word)) {
                            count += 1;
                        } else if (word1.startsWith(word)) {
                            if (VALID_SUFFIX.contains(word1.substring(word.length()))) {
                                count++;
                            }
                        } else if (word.contains(word1)) {
                            if (VALID_SUFFIX.contains(word.substring(word1.length()))) {
                                count++;
                            }
                        }
                    }
                }
                commonCounts[i][j] = count;
            }
        }
        return commonCounts;
    }
    public QnaMatchOutput solve(QnaMatchInput qnaMatchInput) {
        QnaMatchOutput qnaMatchOutput = null;

        //validation
        final int inputQuestionsCount = qnaMatchInput.getQuestions().length;
        final int inputAnswerOptionsCount = qnaMatchInput.getAnswerOptions().length;
        //assert (inputAnswerOptionsCount == inputQuestionsCount);

        //solve
        List<List<String>> wordsFromEachSentenceOfParagraph =
                getWordsFromSentences(OpenNLPParseUtils.getSentencesFromParagraph(qnaMatchInput.getParagraph()));
        List<List<String>> wordsFromEachQuestion =
                getWordsFromSentences(qnaMatchInput.getQuestions());
        List<List<String>> wordsFromEachAnswerOption =
                getWordsFromSentences(qnaMatchInput.getAnswerOptions());

        final int paragraphSentenceCount = wordsFromEachSentenceOfParagraph.size();

        //Score is based on the no of common words

        int[][] scoreQuestionInParagraphSentence = getCommonWordsCountScore(wordsFromEachQuestion, wordsFromEachSentenceOfParagraph);
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        int[][] scoreAnswerOptionInParagraphSentence = getCommonWordsCountScore(wordsFromEachAnswerOption, wordsFromEachSentenceOfParagraph);

        int[][] maxScore = new int[inputQuestionsCount][inputAnswerOptionsCount];
        for (int i = 0; i < inputQuestionsCount; i++) {
            for (int j = 0; j < inputAnswerOptionsCount; j++) {
                for (int k = 0; k < paragraphSentenceCount; k++) {
                    int matchScore = scoreQuestionInParagraphSentence[i][k] * scoreAnswerOptionInParagraphSentence[j][k];
                    //System.out.print(scoreQuestionInParagraphSentence[i][k]+","+scoreAnswerOptionInParagraphSentence[j][k]+" ");
                    if (matchScore > maxScore[i][j]) {
                        maxScore[i][j] = matchScore;
                    }
                }
                //System.out.println(">>>>" + maxScore[i][j]);
            }
        }
        //System.out.println(Arrays.asList(maxScore));

        Map<Integer, Integer> questionAnswerOptionMap = new HashMap<>();
        int loopCount = qnaMatchInput.getQuestions().length;

        while (loopCount > 0) {
            int maxScoreVaue = 0;
            Integer questionMatch = null;
            Integer answerOptionMatch = null;
            for (int i = 0; i < inputQuestionsCount; i++) {
                for (int j = 0; j < inputAnswerOptionsCount; j++) {
                    if (maxScore[i][j] > maxScoreVaue) {
                        maxScoreVaue = maxScore[i][j];
                        questionMatch = i;
                        answerOptionMatch = j;
                    }
                }
            }
            if (questionMatch != null && answerOptionMatch != null) {
                questionAnswerOptionMap.put(questionMatch, answerOptionMatch);
                for (int j = 0; j < inputAnswerOptionsCount; j++) {
                    maxScore[questionMatch][j] = 0;
                }
                for (int i = 0; i < inputQuestionsCount; i++) {
                    maxScore[i][answerOptionMatch] = 0;
                }
            }

            loopCount--;
        }

        List<String> answerOptionsChosen = new ArrayList<>();
        for (int i = 0; i < inputQuestionsCount; i++) {
            if (questionAnswerOptionMap.containsKey(i)) {
                answerOptionsChosen.add(qnaMatchInput.getAnswerOptions()[questionAnswerOptionMap.get(i)]);
            }
        }

        qnaMatchOutput = new QnaMatchOutput(answerOptionsChosen.toArray(new String[answerOptionsChosen.size()]));

        return qnaMatchOutput;
    }
}
