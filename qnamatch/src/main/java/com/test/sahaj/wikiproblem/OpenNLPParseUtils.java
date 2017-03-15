package com.test.sahaj.wikiproblem;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.*;

/**
 * Created by ashish on 15/3/17.
 */
public class OpenNLPParseUtils {
    private static final String[] ignoreWords = new String[] {
            "is", "are", "not", "n't", "and",
            "or", "a", "an", "along", "with",
            "I", "they", "their", "his", "her",
            "which", "who", "what", "where",
            "this", "that", "in", "to", "it",
            "the", "while", "as"
    };
    private static final Set<String> ignoreSet;
    private static SentenceModel SENTENCE_MODEL;
    private static TokenizerModel TOKENIZER_MODEL;

    static {
        ignoreSet = new HashSet<>();
        for (String ignoreWord : ignoreWords) {
            ignoreSet.add(ignoreWord.toUpperCase());
        }

        try(InputStream is = OpenNLPParseUtils.class.getResourceAsStream("/en-sent.bin")) {
            SENTENCE_MODEL = new SentenceModel(is);
        } catch (Exception e) {
            System.out.println(e);
        }

        try (InputStream is = OpenNLPParseUtils.class.getResourceAsStream("/en-token.bin")) {
            TOKENIZER_MODEL = new TokenizerModel(is);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String[] getSentencesFromParagraph(String paragraph) {
        SentenceDetectorME sdetector = new SentenceDetectorME(SENTENCE_MODEL);
        return sdetector.sentDetect(paragraph);
    }

    public static List<String> getWordsFromSentence(String sentence) {
        Tokenizer tokenizer = new TokenizerME(TOKENIZER_MODEL);
        return Arrays.asList(tokenizer.tokenize(sentence));
    }

    public static List<String> getImportantWordsFromWordList(List<String> words) {
        List<String> newList = new ArrayList<>();
        for (String word : words) {
            word = word.toUpperCase();
            if (!ignoreSet.contains(word)) {
                if(StringUtils.isAlphanumeric(word)) {
                    newList.add(word);
                }
            }
        }
        return newList;
    }
}
