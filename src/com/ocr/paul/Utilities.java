package com.ocr.paul;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Character.isDigit;

public class Utilities {

    private int codeSize;
    private int allowedTry;
    private int numberOfColours;

    Scanner sc = new Scanner(System.in);
    int number = 0;
    String solution="";
    int minColours;
    int maxColours;



    public Utilities(int codeSize, int allowedTry, int numberOfColours) {
        this.codeSize=codeSize;
        this.allowedTry=allowedTry;
        this.numberOfColours=numberOfColours;
        this.solution=stringOfEquals(codeSize);
    }

    public int getCodeSize() {
        return codeSize;
    }

    public int getAllowedTry() {
        return allowedTry;
    }

    public int getNumberOfColours() {
        return numberOfColours;
    }

    public void setNumberOfColours(int numberOfColours) {
        this.numberOfColours = numberOfColours;
    }

    public int getTheNumber(int min, int max) {

        boolean responseIsGood;
        do {
            try {
                number = sc.nextInt();
                if (number >= min && number <= max) {
                    responseIsGood = true;
                } else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir un nombre compris  entre " + min + " et " + max);
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir un nombre compris  entre " + min + " et " + max);
            }
        } while (!responseIsGood);
        return number;
    }

    public  String getTheString() {
        System.out.println("veuillez entrer un code compris entre " + codeBound(0) + " et " + codeBound(9));
        String stringFromUser = "";
        boolean responseIsGood = false;
        do {
            try {
                stringFromUser = sc.nextLine();
                if (stringFromUser.length() == getCodeSize()) {
                    for (int i = 0; i < stringFromUser.length(); i++) {
                        if (isDigit(stringFromUser.charAt(i))) {
                            responseIsGood = true;
                        } else {
                            responseIsGood = false;
                            System.out.println("le code ne peut contenir que des chiffres");
                            break;
                        }
                    }
                } else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir " + getCodeSize() + " chiffres");
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir un code à " + getCodeSize() + " chiffres");
            }
        } while (!responseIsGood);
        return stringFromUser;
    }

    public String codeBound(int minOrMax) {
        String bound = "";
        StringBuilder boundBuilder = new StringBuilder("");
        for (int j = 0; j < getCodeSize(); j++) {
            boundBuilder.append(minOrMax);
        }

        bound = boundBuilder.toString();
        boundBuilder.setLength(0);

        return bound;
    }

    public String codeInShape(String proposition) {
        StringBuilder codeStringBuilder = new StringBuilder();
        if (proposition.length() != getCodeSize()) {
            for (int k = 0; k < getCodeSize() - proposition.length(); k++) {
                codeStringBuilder.append("0");
            }
            codeStringBuilder.append(proposition);
            proposition = codeStringBuilder.toString();
            codeStringBuilder.setLength(0);
        }
        return proposition;
    }

    public  String getTheRechercheResponse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez répondre à la proposition de l'IA (avec +,-,=)");
        String stringFromUser = "";
        boolean responseIsGood = false;
        do {
            try {
                stringFromUser = sc.nextLine();
                if (stringFromUser.length() == getCodeSize()) {
                    for (int i = 0; i < stringFromUser.length(); i++) {
                        if (stringFromUser.charAt(i) == '+' || stringFromUser.charAt(i) == '-' || stringFromUser.charAt(i) == '=') {
                            responseIsGood = true;
                        } else {
                            responseIsGood = false;
                            System.out.println("la réponse ne peut contenir que les symboles +,-,=");
                            break;
                        }
                    }

                } else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir " + getCodeSize() + " symboles (+,-,=)");
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir " + getCodeSize() + " symboles (+,-,=)");
            }
        } while (!responseIsGood);
        return stringFromUser;
    }

    public  boolean getTheResult(String responseFromUser, String result, String codeFromIA) {

        if (responseFromUser.equals(solution) && result.equals(solution)) {
            System.out.println("Il y a EGALITE");
            return true;
        }
        if (responseFromUser.equals(solution)) {
            System.out.println("L'IA a trouvé votre code, vous avez PERDU");
            System.out.println("La solution du code de la machine était: " + codeFromIA);
            return true;
        }
        if (result.equals(solution)) {
            System.out.println("Vous avez trouvé le code de l'IA, vous avez GAGNE");
            return true;
        }
        return false;
    }

    public void fixTheColoursBounds (){
        if (getNumberOfColours()<4) setNumberOfColours(4);
        else if (getNumberOfColours()>10) setNumberOfColours(10);

        if (getNumberOfColours()!=10){
            minColours =1;
            maxColours= getNumberOfColours();
        } else{
            minColours=0;
            maxColours=9;
        }
    }

    public  String getTheRandomColours() {
        String randomColours = "";
        StringBuilder tempColours = new StringBuilder();
        tempColours.setLength(0);


        while (tempColours.length() < codeSize) {
            tempColours.append(minColours + (int) (Math.random() * numberOfColours));
        }
        randomColours = tempColours.toString();
        return randomColours;
    }

    public  String getTheColours() {
        String stringFromUser = "";
        boolean responseIsGood = false;
        do {
            try {
                stringFromUser = sc.nextLine();
                if (stringFromUser.length() == codeSize) {
                    for (int i = 0; i < stringFromUser.length(); i++) {
                        if (isDigit(stringFromUser.charAt(i))) {
                            if (Character.getNumericValue(stringFromUser.charAt(i)) >= minColours && Character.getNumericValue(stringFromUser.charAt(i)) <= maxColours) {
                                responseIsGood = true;
                            } else {
                                responseIsGood = false;
                                System.out.println("1-le code ne peut contenir que les chiffres compris entre "+minColours+" et "+maxColours);
                                break;
                            }
                        } else {
                            responseIsGood = false;
                            System.out.println("2-le code ne peut contenir que les chiffres compris entre "+minColours+" et "+maxColours);
                            break;
                        }
                    }
                } else {
                    responseIsGood = false;
                    System.out.println("3-Veuillez saisir une combinaison de " + codeSize + " chiffres compris entre "+minColours+" et "+maxColours);
                }
            } catch (InputMismatchException e) {
                responseIsGood = false;
                System.out.println("4-Veuillez saisir " + codeSize + " chiffres compris entre "+minColours+" et "+maxColours);
                sc.next();
            }
        } while (!responseIsGood);
        return stringFromUser;
    }

    public  boolean getTheMastermindResultDuel (String codeFromUser, String proposition,String codeFromIA, boolean successIA){
        if (proposition.equals(codeFromUser)&& successIA){
            System.out.println("Il y a EGALITE");
            return true;
        }
        if (proposition.equals(codeFromUser)){
            System.out.println("L'IA a trouvé votre code, vous avez PERDU");
            System.out.println("La solution du code de la machnie était: "+ codeFromIA);
            return true;
        }
        if (successIA){
            System.out.println("Vous avez trouvé le code de l'IA, vous avez GAGNE");
            return true;
        }return false;
    }

    public  void askGoodColours(){
        System.out.println("Combien il y a-t-il de couleurs bien placées?");
        getTheNumber(0,codeSize);
        System.out.println("Combien il y a-t-il de couleurs mal placées?");
        getTheNumber(0,codeSize);
    }

    public  void displayNbTour (int nbTours){
        System.out.println("-------------------------------------------------------------");
        System.out.println("TOUR NUMERO: " + nbTours);
        System.out.println("-------------------------------------------------------------");

    }

    public  int compareChar (char a, char b){
        if (Character.getNumericValue(a)==Character.getNumericValue(b))return 0;
        else if (Character.getNumericValue(a)>Character.getNumericValue(b)) return 1;
            else return -1;
    }

    public  String stringOfEquals (int codeSize){
        StringBuilder equals = new StringBuilder();

        for (int i=0;i<codeSize;i++){
            equals.append("=");
        }
        return equals.toString();
    }

    public  boolean allowedToPlay (int nbTry, String codeFromIA){

        if (nbTry < getAllowedTry()) {
            displayNbTour(nbTry+1);
            System.out.println("ATTENTION! il ne reste plus que: " + (getAllowedTry() - nbTry) + " essais");
            return true;
        } else{
            System.out.println("\n"+"DOMMAGE! vous n'avez pas trouvé la solution");
            System.out.println("La solution était: "+codeFromIA);
            return false;
        }

    }

}
