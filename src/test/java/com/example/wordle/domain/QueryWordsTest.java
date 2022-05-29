package com.example.wordle.domain;

import com.example.wordle.adapters.WordListLoader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class QueryWordsTest {
    @Mock
    private WordListLoader wordListLoader;

    @InjectMocks
    private QueryWords queryWords;
    private List<String> wordList;

    @BeforeEach
    public void setup() throws IOException {
        wordList = List.of("abcde", "defgh", "ijklm");
        Mockito.when(wordListLoader.getWordListFromResources()).thenReturn(wordList);
    }

    @Nested
    class random_words {

        @Test
        public void getRandomWordTest() throws IOException {
            String randomWord = queryWords.getRandomWord();
            Assertions.assertThat(wordList).contains(randomWord);
        }
    }
}
