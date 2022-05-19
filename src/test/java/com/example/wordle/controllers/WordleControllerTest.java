package com.example.wordle.controllers;

import com.example.wordle.services.LetterFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private LetterFilter letterFilter;

    @Test
    public void getWordsWithLettersTest() throws IOException {
        List<String> words = List.of("abcde");
        Mockito.when(letterFilter.filterWords(anyString(),anyString())).thenReturn(words);
        var result = wordleController.getFilteredWords("abc", "def");
        Assertions.assertEquals(words, result);
    }
}
