package com.ocr.paul;

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Character.isDigit;
import org.apache.logging.log4j.Logger;

/**
 * this class manage all the methods that are not involved in the core of the different games
 * it has different type of methods: display, seizure, comparison, choice to play again
 */
public class Utilities {

    private int codeSize;
    private int allowedTry;
    private int numberOfColours;
    private Logger logger;

    Scanner sc = new Scanner(System.in);
    int number = 0;
    String solution="";
    int minColours;
    int maxColours;


    /**
     * Constructor of the  CLass Utilities
     * @param codeSize determine the length of the combination
     * @param allowedTry determine the number of allowed try
     * @param numberOfColours determine the number of colours in the mastermind
     * @param logger: the logger
     */
    public Utilities(int codeSize, int allowedTry, int numberOfColours, Logger logger) {
        this.codeSize=codeSize;
        this.allowedTry=allowedTry;
        this.numberOfColours=numberOfColours;
        this.solution=stringOfEquals();
        this.logger=logger;


    }

    /**
     * getter of the integer codeSize, which determine the length of the combination in all games
     * @return the length of the combination
     */
    public int getCodeSize() {
        return codeSize;
    }

    /**
     * getter of the integer allowedTry, which determine the number of plays allowed to each players in all games
     * @return the numbers of allowed try
     */
    public int getAllowedTry() {
        return allowedTry;
    }

    /**
     * getter of the integer numberofcolours,
     * which determine the number of different colours that will be use in mastermind
     * @return the number of colours in mastermind
     */
    public int getNumberOfColours() {
        return numberOfColours;
    }
    /**
     * @param numberOfColours set the number of colours,
     * which determine the number of different colours that will be use in mastermind
     * this value must be between 4 and 10
     */
    public void setNumberOfColours(int numberOfColours) {
        this.numberOfColours = numberOfColours;
    }


    /**
     * get the user's input, input must be an integer between min and max
     * @param min: minimum value of asked integer
     * @param max: maximum value of asked integer
     * @return an input integer between min and max
     */
    public int getTheNumber(int min, int max) {

        boolean responseIsGood;
        do {
            try {
                number = sc.nextInt();
                if (number >= min && number <= max) {
                    responseIsGood = true;
                } else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir un nombre compris entre " + min + " et " + max);
                    logger.warn("out of bound: la saisie utilisateur "+number+" n'est pas comprise entre "+ min + " et " + max);
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir un nombre compris  entre " + min + " et " + max);
                logger.warn("not an Integer: la saisie utlilisateur n'est pas un nombre");
            }
        } while (!responseIsGood);
        return number;
    }

    /**
     * get the user's input, input a serial of integer stock in a string
     * @return a string composed of integer
     */
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
                            logger.warn("la saisie utlisateur n'est pas composé uniquement de chiffres");
                            break;
                        }
                    }
                } else {
                    responseIsGood = false;
                    logger.warn("l'utilisateur n'a pas saisi le bon nombre de caractères");
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

    /**
     * this method will return a string composed of one integer
     * its length will be equal to the combination length
     * @param minOrMax: value of the integer which will be repeated ie:0 for 0000 or 9 for 9999
     * @return a string composed of several integer
     */
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

    /**
     * this method will verify and fix the proposition if its length is too short
     * only used when the proposition length is too short because proposition is build by
     * an random integer
     * @param proposition: this method get a string in param and make sure the string have the good size
     * @return the string proposition at the good length
     */
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

    /**
     * get the user's input, input a serial of +,-,= stock in a string
     * @return a string composed +,-,=
     */
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
                            logger.warn("la réponse de l'utilisateur ne contient pas que les symboles +,-,=");
                            break;
                        }
                    }

                } else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir " + getCodeSize() + " symboles (+,-,=)");
                    logger.warn("l'utilisateur n'a pas saisi le bon nombre de caractères");
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir " + getCodeSize() + " symboles (+,-,=)");
            }
        } while (!responseIsGood);
        return stringFromUser;
    }

    /**
     * this method will compare each reponse (the one from Ia and the one from User) composed of +,-,=
     * to the solution (only made of '=')
     * @param responseFromUser string composed of +,-,= from user
     * @param result string composed of +,-,= from IA
     * @param codeFromIA string of integer that user must find
     * @return true in case of victory or equality
     */
    public  boolean getTheResult(String responseFromUser, String result, String codeFromIA) {

        if (responseFromUser.equals(solution) && result.equals(solution)) {
            System.out.println("\n"+"Il y a EGALITE");
            logger.debug("EGALITE car la réponse de l'utilisateur "+responseFromUser+" et la réponse de l'IA "+result+" sont égales à la solution "+solution);
            return true;
        }
        if (responseFromUser.equals(solution)) {
            System.out.println("\n"+"L'IA a trouvé votre code, vous avez PERDU");
            System.out.println("La solution du code de la machine était: " + codeFromIA);
            logger.debug("victoire de l'IA car seul la réponse de l'utilisateur "+responseFromUser+" est égale à la solution "+solution);
            return true;
        }
        if (result.equals(solution)) {
            System.out.println("\n"+"Vous avez trouvé le code de l'IA, vous avez GAGNE");
            logger.debug("victoire de l'utilisateur car seul la réponse de l'IA "+result+" est égale à la solution "+solution);
            return true;
        }
        return false;
    }

    /**
     * method which determine the lowest and the highest colour
     */
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

    /**
     * this method create a string composed of random numbers in the authorized interval of colours
     * @return a string composed of random integer for the mastermind game
     */
    public  String getTheRandomColours() {
        StringBuilder tempColours = new StringBuilder();
        tempColours.setLength(0);
        while (tempColours.length() < codeSize) {
            tempColours.append(minColours + (int) (Math.random() * maxColours));
        }
        return tempColours.toString();
    }

    /**
     * get the user's input, input a serial of integer in the authorized interval stock in a string
     * @return a string composed of integer selected by user for the mastermind game
     */
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
                                System.out.println("le code ne peut contenir que les chiffres compris entre "+minColours+" et "+maxColours);
                                logger.warn("out of bound: la saisie utilisateur "+number+" n'est pas comprise entre "+ minColours + " et " + maxColours);
                                break;
                            }
                        } else {
                            responseIsGood = false;
                            System.out.println("le code ne peut contenir que les chiffres compris entre "+minColours+" et "+maxColours);
                            logger.warn("la saisie utilisateur "+stringFromUser.charAt(i)+" n'est pas un chiffre");
                            break;
                        }
                    }
                } else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir une combinaison de " + codeSize + " chiffres compris entre "+minColours+" et "+maxColours);
                }
            } catch (InputMismatchException e) {
                responseIsGood = false;
                System.out.println("4-Veuillez saisir " + codeSize + " chiffres compris entre "+minColours+" et "+maxColours);
                sc.next();
            }
        } while (!responseIsGood);
        return stringFromUser;
    }

    /**
     * this method determine an display the result of the duel
     * @param codeFromUser secret code from user
     * @param proposition combination porposed by IA
     * @param codeFromIA secret code from IA
     * @param successUser boolean which state if the user has found the codeFromIA
     * @return true in case of victory or equality
     */
    public  boolean getTheMastermindResultDuel (String codeFromUser, String proposition,String codeFromIA, boolean successUser){

        if (proposition.equals(codeFromUser)&& successUser){
            System.out.println("\n"+"Il y a EGALITE");
            logger.debug("EGALITE");
            return true;
        }
        if (proposition.equals(codeFromUser)){
            System.out.println("\n"+"L'IA a trouvé votre code, vous avez PERDU");
            System.out.println("Le code secret de l'IA était: "+ codeFromIA);
            logger.debug("L'IA a GAGNE");
            return true;
        }
        if (successUser){
            System.out.println("\n"+"Vous avez trouvé le code de l'IA, vous avez GAGNE");
            logger.debug("L'utilisateur a GAGNE");
            return true;
        }return false;
    }

    /**
     * useless method in order to create interaction during mastermind defender
     */
    public  void askGoodColours(){
        int fakeAnswer;
        System.out.println("Combien il y a-t-il de couleurs bien placées?");
        fakeAnswer=getTheNumber(0,codeSize);
        logger.debug("FACTICE: l'utilisateur déclare qu'il y a "+fakeAnswer+" de couleurs bien placées");
        System.out.println("Combien il y a-t-il de couleurs mal placées?");
        fakeAnswer=getTheNumber(0,codeSize);
        logger.debug("FACTICE: l'utilisateur déclare qu'il y a "+fakeAnswer+" de couleurs mal placées");
    }

    /**
     * this method display the number of plays
     * @param nbTours integer who represent the number of round
     */
    public  void displayNbTour (int nbTours){
        System.out.println("-------------------------------------------------------------");
        System.out.println("TOUR NUMERO: " + nbTours);
        logger.debug("tours numéro: "+nbTours);
        System.out.println("-------------------------------------------------------------");

    }

    /**
     * this method compare the integer value of two char
     * @param a a char from a string
     * @param b a char from a string
     * @return 0 if equals, 1 if a greater than b, and -1 if b greater than a
     */
    public  int compareChar (char a, char b){
        if (Character.getNumericValue(a)==Character.getNumericValue(b))return 0;
        else if (Character.getNumericValue(a)>Character.getNumericValue(b)) return 1;
            else return -1;
    }

    /**
     * method which create a string composed of '=', of the length of the combination
     * @return a string of '=' of the desired length
     */
    public  String stringOfEquals (){
        StringBuilder equals = new StringBuilder();

        for (int i=0;i<getCodeSize();i++){
            equals.append("=");
        }
        return equals.toString();
    }

    /**
     * display the number of remaining allowed try
     * @param nbTry number of current try
     * @param codeFromIA in case of lose, display the code that the user didn't find
     * @return true if the number of try is ok
     */
    public  boolean allowedToPlay (int nbTry, String codeFromIA){

        if (nbTry < getAllowedTry()) {
            displayNbTour(nbTry+1);
            System.out.println("ATTENTION! il ne reste plus que: " + (getAllowedTry() - nbTry) + " essais");
            return true;
        } else{
            System.out.println("\n"+"DOMMAGE! vous n'avez pas trouvé la solution");
            logger.debug("nombre d'essais possible dépassé");
            System.out.println("La solution était: "+codeFromIA);
            return false;
        }

    }

    /**
     * this method ask if the player want to play again
     * @return a boolean which allow (true) or not (false) another turn of the different loop
     */
    public boolean wantToPlay(){
        int choice=0;
        System.out.println("\n"+"Que souhaitez vous faire?");
        System.out.println("1 - faire le même jeu");
        System.out.println("2 - retour au menu principal");
        System.out.println("3 - quitter le programme");
        choice=getTheNumber(1,3);

        switch (choice){
            case 1: return true;
            case 2: return false;
            case 3:
                System.out.println("Merci d'avoir joué! A bientôt.");
                logger.info("programme quitté par l'utilisateur");
                System.exit(0);
                return false;
        }
        return false;
    }

    public void letTheDuelBegin(boolean devMode, String codeFromIA){
        if (devMode) System.out.println("la combinaison de l'Ia est :"+codeFromIA);
        System.out.println("L'IA a choisi son code secret");
        System.out.println("--------------------------------------------------------------");
        System.out.println("                       LE DUEL COMMENCE");
    }
}
