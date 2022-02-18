package com.endava.testing;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;

public class Main {

    static HashMap<String, Integer> variables = new HashMap<String, Integer>();

    public static void main(String[] args) {
        variables.put("j", 100);

        boolean goAhead = true;
        while(goAhead){
            String[] input = Calculator.readInput();
            for(String i : input)
                if (i.toLowerCase().equals("exit") | i.toLowerCase().equals("quit"))
                    return;
            Calculator.validateInput(input, variables);
            System.out.println("");
        }
    }
}

class Calculator {

    Calculator(){}

    static String[] readInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the command: ");
        String input = sc.nextLine();
        return input.split(" ");
    }

    static void validateInput(String[] str, HashMap cache) {
        /*
         * SUM 2 2, prints 2+2 result.
         * SUM 2 2 A, stores 2+2 in var A.
         * SUM A 3, prints A+3 = 4+3 = 7. (with A != null)
         * SUM 3 A, prints 3+A = 3+4 = 7. (with A != null)
         * SUM J 5, displays error. (with J == null)
         * */

        if( str[0].toLowerCase().equals( "show" ) ) {
            if(str.length > 2){
                System.out.println("Wrong input format. SHOW only has one argument.");
                return;
            }
            if( cache.get(str[1]) != null)
                Calculator.Show(str[1], cache);
            else
                System.out.println("The variable has not been assigned yet.");
        }

        else if( !str[0].equals("sum") & !str[0].equals("minus") & !str[0].equals("multiply") & !str[0].equals("divide")  ) {
            System.out.println("Error. Please type a valid operation: sum, minus, multiply, divide.");
        }
        else {
            if ( str.length == 3 ){
                boolean twoNumbers = Calculator.isAlpha(str[0]) & Calculator.isNumeric(str[1]) & Calculator.isNumeric(str[2]);
                boolean stringAndNumber = Calculator.isAlpha(str[0]) & Calculator.isAlpha(str[1]) & Calculator.isNumeric(str[2]);
                boolean numberAndString = Calculator.isAlpha(str[0]) & Calculator.isNumeric(str[1]) & Calculator.isAlpha(str[2]);
                if ( twoNumbers ){
                    System.out.println( Calculator.calculate(str[0], str[1], str[2]) );
                } else if ( stringAndNumber ){
                    if( cache.get(str[1]) == null){
                        cache.put(str[1], str[2]);
                    } else {
                        System.out.println( Calculator.calculate( str[0], cache.get(str[1]).toString(), str[2] ));
                    }
                } else if ( numberAndString ){
                    if( cache.get(str[2]) == null){
                        cache.put(str[2], str[1]);
                    } else {
                        System.out.println( Calculator.calculate( str[0], str[1], cache.get(str[2]).toString() ));
                    }
                } else {
                    System.out.println("Invalid input");
                }
            } else if ( str.length==4 ){
                boolean twoNumbersAndString = Calculator.isAlpha(str[0]) & Calculator.isNumeric(str[1]) & Calculator.isNumeric(str[2]) & Calculator.isAlpha(str[3]);
                if (twoNumbersAndString){
                    System.out.println("Calculation result stored in " + str[3]);
                    cache.put(str[3], Calculator.calculate(str[0], str[1], str[2]) );
                } else {
                    System.out.println("Invalid input format");
                }
            } else
                System.out.println("Invalid input length");

        }

    }

    static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (str == null) {
            return false;
        }
        return pattern.matcher(str).matches();
    }

    public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }

    static int calculate(String ops, String xs, String ys){
            int result=0;
            String operation = ops.toLowerCase();
            int x = Integer.parseInt(xs);
            int y = Integer.parseInt(ys);
        switch (operation) {
            case "sum" -> result = Calculator.SumXY(x, y);
            case "minus" -> result = Calculator.MinusXY(x, y);
            case "multiply" -> result = Calculator.MultiplyXY(x, y);
            case "divide" -> result = Calculator.DivideXY(x, y);
            default -> System.out.println("Invalid operation. Enter only SUM, MINUS, MULTIPLY or DIVIDE.");
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

    static void Show(String key, HashMap cache){
        System.out.println(cache.get(key));
    }
}
