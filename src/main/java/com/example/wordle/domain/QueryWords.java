package com.example.wordle.domain;

import com.example.wordle.adapters.WordListLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QueryWords {
    @Autowired
    private WordListLoader wordListLoader;

    public String getRandomWord() throws IOException {
        List<String> word = wordListLoader.getWordListFromResources();
        var randIndex = ThreadLocalRandom.current().nextInt(word.size());
        return word.get(randIndex);
    }

    public Boolean wordExists(String word) throws IOException {
        List<String> words = wordListLoader.getWordListFromResources();
        return words.contains(word);
    }
}
