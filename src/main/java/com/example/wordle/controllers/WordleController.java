package com.example.wordle.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WordleController {

    @GetMapping("/include-letters")
    public List<String> getWordsWithLetters(@RequestParam String included, @RequestParam String excluded) throws IOException {
        ArrayList<String> words = new ArrayList<>(getWordListFromResources());
        return words.stream()
                .filter(word ->
                        included.chars().mapToObj(c -> (char) c).allMatch(letter -> word.indexOf(letter) > -1))
                .filter(word -> excluded.chars().mapToObj(c -> (char) c).noneMatch(letter -> word.indexOf(letter) > -1))
                .toList();
    }

    private List<String> getWordListFromResources() throws IOException {
        InputStream wordsInputStream = new ClassPathResource("words.txt").getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(wordsInputStream));
        return Arrays.stream(reader.lines().collect(Collectors.joining("\n")).split(" ")).toList();
    }
}