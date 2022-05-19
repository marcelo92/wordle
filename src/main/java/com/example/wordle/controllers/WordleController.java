package com.example.wordle.controllers;

import com.example.wordle.services.LetterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WordleController {
    @Autowired
    private LetterFilter letterFilter;

    @GetMapping("/include-letters")
    public List<String> getFilteredWords(@RequestParam String included, @RequestParam String excluded) throws IOException {
        return letterFilter.filterWords(included, excluded);
    }
}