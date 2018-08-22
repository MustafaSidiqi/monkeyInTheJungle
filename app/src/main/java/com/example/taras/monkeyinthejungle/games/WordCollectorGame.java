package com.example.taras.monkeyinthejungle.games;

import java.util.Random;


public class WordCollectorGame {
    private final String[] randomWords = {
            "There", "Are", "Multiple", "Depth", "Features", "Such", "product", "review",
            "laptop", "digital", "audio", "player", "technology", "editorial", "camera",
            "advertising", "regular", "feature", "include", "adrenaline", "junkie", "article",
            "speculative", "page", "about", "upcoming", "technology"};
    private char[] randomString;
    private String word;

    public WordCollectorGame() {
        Random rand = new Random();

        word = randomWords[rand.nextInt(randomWords.length)];
        word.toLowerCase();
        String first = word.substring(0,1);
        word = first.toUpperCase() + word.substring(1);
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
    }
    public char[] getShuffledWord() {
        return randomString;
    }
    public String getWord() {
        return word;
    }
}