package com.example.wordle.adapters.controllers;

import com.example.wordle.domain.LetterFilter;
import com.example.wordle.domain.QueryWords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WordleController {
    Logger logger = LoggerFactory.getLogger(WordleController.class);
    @Autowired
    private LetterFilter letterFilter;

    @Autowired
    private QueryWords queryWords;

    @GetMapping("filter")
    public List<String> getFilteredWords(@RequestParam String included, @RequestParam String excluded) throws IOException {
        return letterFilter.filterWords(included, excluded);
    }

    @GetMapping("word")
    public String getRandomWord() throws IOException {
        String word = queryWords.getRandomWord();
        logger.info(word);
        return word;
    }

    @GetMapping("/word/{word}/validate")
    public Boolean isValidWord(@PathVariable String word) throws IOException {
        return queryWords.wordExists(word);
    }
}