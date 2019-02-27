package com.ocr.paul;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Character.isDigit;

public class Utilities {
    static int codeSize = 4;
    static Scanner sc = new Scanner(System.in);
    static int number = 0;

    public static int getTheNumber(int min, int max) {

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

    public static String getTheString() {
        System.out.println("veuillez entrer un code compris entre " + codeBound(codeSize, 0) + " et " + codeBound(codeSize, 9));
        String stringFromUser = "";
        boolean responseIsGood = false;
        do {
            try {
                stringFromUser = sc.nextLine();
                if (stringFromUser.length() == codeSize) {
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
                    System.out.println("Veuillez saisir " + codeSize + " chiffres");
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir un code à " + codeSize + " chiffres");
            }
        } while (!responseIsGood);
        return stringFromUser;
    }

    private static String codeBound(int codeSize, int minOrMax) {
        String bound = "";
        StringBuilder boundBuilder = new StringBuilder("");
        for (int j = 0; j < codeSize; j++) {
            boundBuilder.append(minOrMax);
        }

        bound = boundBuilder.toString();
        boundBuilder.setLength(0);

        return bound;
    }

    public static String codeInShape(String proposition) {
        StringBuilder codeStringbuilder = new StringBuilder();
        if (proposition.length() != codeSize) {
            for (int k = 0; k < codeSize - proposition.length(); k++) {
                codeStringbuilder.append("0");
            }
            codeStringbuilder.append(proposition);
            proposition = codeStringbuilder.toString();
            codeStringbuilder.setLength(0);
        }
        return proposition;
    }

    public static String getTheRechercheResponse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez répondre à la proposition de l'IA (avec +,-,=)");
        String stringFromUser = "";
        boolean responseIsGood = false;
        do {
            try {
                stringFromUser = sc.nextLine();
                if (stringFromUser.length() == codeSize) {
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
                    System.out.println("Veuillez saisir " + codeSize + " symboles (+,-,=)");
                }
            } catch (InputMismatchException e) {
                sc.next();
                responseIsGood = false;
                System.out.println("Veuillez saisir " + codeSize + " symboles (+,-,=)");
            }
        } while (!responseIsGood);
        return stringFromUser;
    }

    public static boolean getTheResult(String responseFromUser, String result, String codeFromIA) {
        if (responseFromUser.equals("====") && result.equals("====")) {
            System.out.println("Il y a EGALITE");
            return true;
        }
        if (responseFromUser.equals("====")) {
            System.out.println("L'IA a trouvé votre code, vous avez PERDU");
            System.out.println("La solution du code de la machnie était: " + codeFromIA);
            return true;
        }
        if (result.equals("====")) {
            System.out.println("Vous avez trouvé le code de l'IA, vous avez GAGNE");
            return true;
        }
        return false;
    }

    public static String getTheRandomColours() {
        String randomColours = "";
        StringBuilder tempColours = new StringBuilder();
        tempColours.setLength(0);
        while (tempColours.length() < codeSize) {
            tempColours.append(1 + (int) (Math.random() * 4));
        }
        randomColours = tempColours.toString();
        return randomColours;
    }

    public static String getTheColours() {
        String stringFromUser = "";
        boolean responseIsGood = false;
        do {
            try {
                stringFromUser = sc.nextLine();
                if (stringFromUser.length() == codeSize) {
                    for (int i = 0; i < stringFromUser.length(); i++) {
                        if (isDigit(stringFromUser.charAt(i))) {
                            if (Character.getNumericValue(stringFromUser.charAt(i)) < 5 && Character.getNumericValue(stringFromUser.charAt(i)) > 0) {
                                responseIsGood = true;
                            } else {
                                responseIsGood = false;
                                System.out.println("le code ne peut contenir que les chiffres compris entre 1 et 4");
                                break;
                            }
                        } else {
                            responseIsGood = false;
                            System.out.println("le code ne peut contenir que les chiffres compris 1,2,3 ou 4");
                            break;
                        }
                    }
                } else {
                    responseIsGood = false;
                    System.out.println("Veuillez saisir une combinaison de " + codeSize + " chiffres compris entre 1 et 4");
                }
            } catch (InputMismatchException e) {
                responseIsGood = false;
                System.out.println("Veuillez saisir " + codeSize + " chiffres compris entre 1 et 4");
                sc.next();
            }
        } while (!responseIsGood);
        return stringFromUser;
    }
}
