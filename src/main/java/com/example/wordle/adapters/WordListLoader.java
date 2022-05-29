package com.example.wordle.adapters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordListLoader {
    @Value("classpath:words.txt")
    private Resource wordsFile;

    public List<String> getWordListFromResources() throws IOException {
        InputStream wordsInputStream = wordsFile.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(wordsInputStream));
        return Arrays.stream(reader.lines().collect(Collectors.joining("\n")).split(" ")).toList();
    }
}
