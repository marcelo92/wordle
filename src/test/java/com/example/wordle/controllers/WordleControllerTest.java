package com.example.wordle.controllers;

import com.example.wordle.adapters.controllers.WordleController;
import com.example.wordle.domain.LetterFilter;
import com.example.wordle.domain.QueryWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class WordleControllerTest {
    @InjectMocks
    private WordleController wordleController;

    @Mock
    private QueryWords queryWords;

    @Mock
    private LetterFilter letterFilter;

    @Nested
    class filter_test {
        @Test
        public void getWordsWithLettersTest() throws IOException {
            List<String> words = List.of("abcde");
            Mockito.when(letterFilter.filterWords(anyString(), anyString())).thenReturn(words);
            var result = wordleController.getFilteredWords("abc", "def");
            Assertions.assertEquals(words, result);
        }
    }

    @Nested
    class get_word {
        @Test
        public void getRandomWord() throws IOException {
            Mockito.lenient().when(queryWords.getRandomWord()).thenReturn("abcde");
            String word = wordleController.getRandomWord();
            Assertions.assertEquals("abcde", word);
        }
    }
}
