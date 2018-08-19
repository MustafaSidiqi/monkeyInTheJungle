package com.example.taras.monkeyinthejungle.games;

import android.util.Log;

import java.util.Random;

public class MissingNumberGame {

    private static final int MAXNUMBER = 100;
    private static final int MINNUMBER = 10;
    private static final int VARIABLES = 2;
    private static final int OPTIONS = 4;

    private Random rand;
    private int[] options;
    private String equation;
    private int answer;
    private int answerIndex;


    public MissingNumberGame() {
        rand = new Random();
        generateOptions(generateEquation());
    }

    public int[] getOptions() {
        return options;
    }

    public String getEquation() { return equation ;}

    public boolean isCorrect(int answer) {
        return answer == this.answer;
    }

    public int getAngwerId() { return answerIndex; }
    private int generateEquation() {
        int hiddenNumber = rand.nextInt(VARIABLES );
        Log.d("MN", hiddenNumber + "");
        int result = 0;
        equation = "";

        for( int i = 0; i < VARIABLES; i ++) {
            int number =  rand.nextInt((MAXNUMBER - MINNUMBER ) + 1 )  + MINNUMBER;

            if(rand.nextBoolean() || i == 0) {
             equation +=  " + ";
             result += number;
            } else {
                equation += " - ";
                result -= number;
            }

            if( i == hiddenNumber ) {
                equation += "X";
                this.answer = number;
            } else {
                equation += number;
            }

        }
        equation = equation.substring(3, equation.length());
        equation += " = " + result;
        return this.answer;
    }

    private void generateOptions(int correctAnswer) {
        options = new int[OPTIONS];
        for( int i = 0; i < OPTIONS; i++) {
            int randomNumber = rand.nextInt((MAXNUMBER - MINNUMBER) + 1 )  + MINNUMBER;
            options[i] = rand.nextBoolean() ? correctAnswer + randomNumber : correctAnswer - randomNumber;
        }
        answerIndex = rand.nextInt(OPTIONS);
        options[answerIndex] = correctAnswer;
    }
}
