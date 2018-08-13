package com.example.taras.monkeyinthejungle.games;

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

    public MissingNumberGame() {
        rand = new Random();
        generateOptions(generateEquation());
    }

    public int[] getOptions() {
        return options;
    }

    public boolean isCorrect(int answer) {
        return answer == this.answer;
    }

    private int generateEquation() {
        int hiddenNumber = rand.nextInt(VARIABLES + 1);
        int result = 0;
        equation = "";

        for( int i = 0; i < VARIABLES; i ++) {
            int number =  rand.nextInt((MAXNUMBER - MINNUMBER) + 1 )  + MINNUMBER;
            if( i == hiddenNumber ) {
                equation += "X";
                this.answer = number;
            }
            equation += number;
            if(rand.nextBoolean() && i != VARIABLES - 1) {
             equation += " + ";
             result += number;
            } else {
                equation += " - ";
                result -= number;
            }
        }
        equation += " = " + result;
        return result;
    }

    private void generateOptions(int correctAnswer) {
        options = new int[OPTIONS];
        for( int i = 0; i < OPTIONS; i++) {
            int randomNumber = rand.nextInt((MAXNUMBER - MINNUMBER) + 1 )  + MINNUMBER;
            options[i] = rand.nextBoolean() ? correctAnswer + randomNumber : correctAnswer - randomNumber;
        }
        options[rand.nextInt(OPTIONS)] = correctAnswer;
    }
}
