package com.endava.testing;

import java.util.Locale;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        String[] input = Calculator.readInput();
        int inputType = Calculator.sortInput(input);
        System.out.println(inputType);
    }
}

class Calculator {

    Calculator(){}

    static String[] readInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your operation or type 'exit': ");
        String input = sc.nextLine();
        return input.split(" ");
    }

    static int sortInput(String[] input){
            int result=0;
            String operation = input[0].toLowerCase();
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);
        switch (operation) {
            case "sum" -> result = Calculator.SumXY(x, y);
            case "minus" -> result = Calculator.MinusXY(x, y);
            case "multiply" -> result = Calculator.MultiplyXY(x, y);
            case "divide" -> result = Calculator.DivideXY(x, y);
            case "show" -> Calculator.Show();
        }

        return result;
    }

    static int SumXY(int x, int y){
        return x+y;
    }

    static int MinusXY(int x, int y){
        return x-y;
    }

    static int MultiplyXY(int x, int y){
        return x*y;
    }

    static int DivideXY(int x, int y){
        return x/y;
    }

    static void Show(){
    }
}