package com.example.wordle.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LetterFilterTest {
    @Mock
    private WordListLoader wordListLoader;

    @InjectMocks
    private LetterFilter letterFilter;

    @BeforeEach
    public void setup() throws IOException {
        Mockito.when(wordListLoader.getWordListFromResources()).thenReturn(List.of("abcde", "defgh", "ijklm"));
    }

    @Test
    public void filterWordsShouldIncludeAndExcludeLetters() throws IOException {
        var lettersIncluded = "de";
        var lettersExcluded = "f";
        var words = letterFilter.filterWords(lettersIncluded, lettersExcluded);
        Assertions.assertEquals(List.of("abcde"), words);
    }

    @Test
    public void filterWordsOnlyIncludingLetters() throws IOException {
        var lettersIncluded = "de";
        var lettersExcluded = "";
        var words = letterFilter.filterWords(lettersIncluded, lettersExcluded);
        Assertions.assertEquals(List.of("abcde", "defgh"), words);
    }

    @Test
    public void filterWordsOnlyExcludingLetters() throws IOException {
        var lettersIncluded = "";
        var lettersExcluded = "d";
        var words = letterFilter.filterWords(lettersIncluded, lettersExcluded);
        Assertions.assertEquals(List.of("ijklm"), words);
    }

    @Test
    public void filterWordsWithoutLetters() throws IOException {
        var lettersIncluded = "";
        var lettersExcluded = "";
        var words = letterFilter.filterWords(lettersIncluded, lettersExcluded);
        Assertions.assertEquals(List.of("abcde", "defgh", "ijklm"), words);
    }
}
