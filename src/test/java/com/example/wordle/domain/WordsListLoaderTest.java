package com.example.wordle.domain;

import com.example.wordle.adapters.WordListLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WordsListLoaderTest {
    @Mock
    private Resource wordsFile;
    @InjectMocks
    private WordListLoader wordListLoader;

    @BeforeEach
    public void setup() throws IOException {
        var wordsFile = new ClassPathResource("words.txt");
        ReflectionTestUtils.setField(wordListLoader, "wordsFile",wordsFile);
    }

    @Test
    public void getWordListFromResourcesTest() throws IOException {
        var words = wordListLoader.getWordListFromResources();
        Assertions.assertEquals(List.of("abcde", "defgh", "ijklm"), words);
    }
}
