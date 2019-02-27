package com.ocr.paul;

public class RechercheDefender {
    private static StringBuilder codeAdapt = new StringBuilder("");
    private static int allowedTry = 5;
    private static int codeSize = 4;


    public static void rechercheDefender() {

         int nbTry = 0;
         int count = 0;
        String codeFromUser = Utilities.getTheString();
        String proposition = String.valueOf((int) (Math.random() * (double) Math.pow(10, codeSize)));
        System.out.println("Votre code secret est: " + codeFromUser);
        proposition = Utilities.codeInShape(proposition);
        System.out.println("L'IA propose le code suivant: " + proposition);
        System.out.println("ATTENTION! il ne reste plus que: " + (allowedTry - nbTry) + " essais à l'IA");
        nbTry++;
        String responseFromUser = Utilities.getTheRechercheResponse();
        if (responseFromUser.equals("====")) {
            System.out.println("L'IA a trouvé le code!!");
            return;
        }
        do {

            if (nbTry >= allowedTry) {
                System.out.println("FELICITATION!! Vous avez vaincu la machine");
                break;
            } else System.out.println("ATTENTION! il ne reste plus que: " + (5 - nbTry) + " essais à l'IA");
            proposition = iABreakYourCode(responseFromUser, proposition, count);
            System.out.println("l'IA propose le code  suivant: " + proposition);
            responseFromUser = Utilities.getTheRechercheResponse();
            if (responseFromUser.equals("====")) {
                System.out.println("L'IA a trouvé le code!!");
                break;
            }
            nbTry++;
            count++;
        } while (nbTry < allowedTry + 1);
        return;

    }


    public static String iABreakYourCode(String responseFromUser, String proposition, int count) {
        int[][] codeHistory = new int[10][4];
        int[] propTab = new int[4];
        int number;
        codeAdapt.setLength(0);
        for (int i = 0; i < proposition.length(); i++) {
            propTab[i] = Character.getNumericValue(proposition.charAt(i));
        }
        for (int i = 0; i < proposition.length(); i++) {
            codeHistory[count][i] = Character.getNumericValue(proposition.charAt(i));
        }
        for (int i = 0; i < responseFromUser.length(); i++) {
            if (responseFromUser.charAt(i) == '+') {
                number = (int) ((Character.getNumericValue(proposition.charAt(i)) + 9) / 2);
                if (count > 1 && codeHistory[count - 1][i] > codeHistory[count][i]) {
                    number = (int) ((codeHistory[count - 2][i] + codeHistory[count- 1][i]) / 2);
                }
                if (count > 1 && codeHistory[count][i] == 8) number = 9;
                codeAdapt.append(number);
            } else if (responseFromUser.charAt(i) == '-') {
                number = (int) ((Character.getNumericValue(proposition.charAt(i))) / 2);
                codeAdapt.append(number);
            } else {
                number = (Character.getNumericValue(proposition.charAt(i)));
                codeAdapt.append(number);
            }
        }

        proposition = codeAdapt.toString();
        return proposition;
    }
}