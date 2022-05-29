package com.example.wordle.domain;

import com.example.wordle.adapters.WordListLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LetterFilter {
    @Autowired
    private WordListLoader wordListLoader;

    public List<String> filterWords(String included, String excluded) throws IOException {
        ArrayList<String> words = new ArrayList<>(wordListLoader.getWordListFromResources());
        return words.stream()
                .filter(word ->
                        included.chars().mapToObj(c -> (char) c).allMatch(letter -> word.indexOf(letter) > -1))
                .filter(word -> excluded.chars().mapToObj(c -> (char) c).noneMatch(letter -> word.indexOf(letter) > -1))
                .toList();
    }
}
