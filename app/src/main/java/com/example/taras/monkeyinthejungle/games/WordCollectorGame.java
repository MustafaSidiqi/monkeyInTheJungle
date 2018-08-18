package com.example.taras.monkeyinthejungle.games;

import java.util.Random;


public class WordCollectorGame {
    private final String[] randomWords = {
            "There", "are", "multiple", "in", "depth", "features", "such", "product", "reviews",
            "laptops", "digital", "audio", "players", "technology", "editorials", "digital",
            "cameras", "advertising", "regular", "features", "include", "adrenaline", "junkie",
            "article", "speculative", "pages", "about", "upcoming", "technology"};
    private char[] randomString;
    private String word;

    public WordCollectorGame() {}
    public char[] getShuffledWord() {
        Random rand = new Random();

        word = randomWords[rand.nextInt(randomWords.length)];
        int wordLength = word.length();
        randomString = new char[wordLength];

        for(int i = 0; i < wordLength; i++ ) {
            int r = rand.nextInt(wordLength);
            while( randomString[r] != 0) {
                r++;
                r = r%wordLength;
            }
            randomString[r] = word.charAt(i);
        }
        return randomString;
    }
}