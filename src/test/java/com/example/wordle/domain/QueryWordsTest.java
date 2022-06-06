package com.example.wordle.domain;

import com.example.wordle.adapters.WordListLoader;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            assertThat(wordList).contains(randomWord);
        }
    }

    @Nested
    class word_exists {
        @Test
        public void wordExistsWithExistingWord() throws IOException {
            assertTrue(queryWords.wordExists("abcde"));
        }

        @Test
        public void wordExistsWithNonExistingWord() throws IOException {
            assertFalse(queryWords.wordExists("edcab"));
        }
    }
}
