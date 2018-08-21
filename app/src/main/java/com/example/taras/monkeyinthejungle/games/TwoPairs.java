package com.example.taras.monkeyinthejungle.games;

import java.util.Arrays;
import java.util.Collections;

public class TwoPairs {

    private int firstCard, secondCard;
    private int clickedFirst, clickedSecond;
    private int cardNum = 1;
    private int points;

    //Array for images
    private Integer[] cardArray = {101, 102, 103, 104, 201, 202, 203, 204};

    public Integer[] getCardArray() {
        return cardArray;
    }

    public void setCardArray(Integer[] cardArray) {
        this.cardArray = cardArray;
    }

    public void shuffleArray() {
        //Shuffle the cards
        Collections.shuffle(Arrays.asList(cardArray));
    }

    public int getCardArrayIndex(int index) {
        return cardArray[index];
    }

    public int getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(int firstCard) {
        this.firstCard = firstCard;
    }

    public int getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(int secondCard) {
        this.secondCard = secondCard;
    }

    public int getClickedFirst() {
        return clickedFirst;
    }

    public void setClickedFirst(int clickedFirst) {
        this.clickedFirst = clickedFirst;
    }

    public int getClickedSecond() {
        return clickedSecond;
    }

    public void setClickedSecond(int clickedSecond) {
        this.clickedSecond = clickedSecond;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        this.points = points + 1;
    }



}
