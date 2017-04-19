package com.example.darcie.educationalgame;

import android.util.Log;

import java.util.Random;

/**
 * Created by Darcie on 13/04/2017.
 */

public class GameMath {

    public int chooseRandomNum(String operator){
        Random rand = new Random();

        switch (operator) {
            case "+":
            case "-": {
                int randomNumber = rand.nextInt((150 - 1) + 1) + 1;
                Log.i("Random num", ":" + randomNumber);
                return randomNumber;
            }
            case "/": {
                int randomNumber = rand.nextInt((15 - 1) + 1) + 1;
                Log.i("Random num", ":" + randomNumber);
                return randomNumber;
            }
            default: {
                int randomNumber = rand.nextInt((12 - 1) + 1) + 1;
                Log.i("Random num", ":" + randomNumber);
                return randomNumber;
            }
        }
    }

    public String chooseOperator(){
        Random rand = new Random();
        int randomNumber = rand.nextInt((4 - 1) + 1) + 1;
        String operator = null;
        switch (randomNumber){
            case 1:
                operator = "+";
                Log.i("Random operator", ":" + operator);
                return operator;
            case 2:
                operator = "-";
                Log.i("Random operator", ":" + operator);
                return operator;
            case 3:
                operator = "*";
                Log.i("Random operator", ":" + operator);
                return operator;
            case 4:
                operator = "/";
                Log.i("Random operator", ":" + operator);
                return operator;
        }
        return operator = "+";
    }

    public String generateSumString(int num1,int num2, String operator, int answer){

        //Returns a string with the generated sum to be output to the question textField.
        String sum;
        int max = getMax(num1, num2);
        Log.i("Max", ":" + max);
        int min = getMin(num1, num2);
        Log.i("Min", ":" + min);
        if (operator.equals("/")){
            sum = Integer.toString(getNewSum(max,min)) + " " + operator + " " + Integer.toString(getMin(num1, num2));
            Log.i("Sum", ":" + sum);
        } else {
            sum = Integer.toString(max) + operator + Integer.toString(min);
            Log.i("Sum", ":" + sum);
        }
        return sum;
    }

    public int calculateSumAnswer(int num1,int num2, String operator){
        int answer = 0;
        int max = getMax(num1, num2);
        int min = getMin(num1, num2);

        switch (operator) {
            case "+":
                answer = num1 + num2;
                Log.i("Answer", ":" + answer);
                break;
            case "-":
                answer = max - min;
                Log.i("Answer", ":" + answer);
                break;
            case "*":
                answer = num1 * num2;
                Log.i("Answer", ":" + answer);
                break;
            case "/":
                int newSum = getNewSum(max, min);
                Log.i("new", ":" + newSum);
                answer = newSum/min;
                Log.i("Answer", ":" + answer);
                break;
        }
        return answer;
    }

    private int getMax(int num1, int num2){
        int max;
        if (num1 >= num2){
            max = num1;
        } else {
            max = num2;
        }
        return max;
    }

    private int getMin(int num1, int num2){
        int min;
        if (num1 <= num2){
            min = num1;
        } else {
            min = num2;
        }
        return min;
    }

    private int getNewSum(int max, int min){
        return max * min;
    }
}


