package com.ocr.paul;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities {

    static Scanner sc = new Scanner(System.in);
    static int choiceOfGame=0;

    public static int getTheNumber (int min, int max){

        boolean responseIsGood;
        do {
            try {
                choiceOfGame = sc.nextInt();
                if  (choiceOfGame>=min && choiceOfGame<=max){
                    responseIsGood = true;
                }else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir un nombre compris  entre "+min+" et "+max);
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir un nombre compris  entre "+min+" et "+max);
            }
        }while (!responseIsGood) ;
        return choiceOfGame;
    }

}
