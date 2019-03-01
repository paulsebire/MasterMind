package com.ocr.paul;

public class RechercheDefender {
    private static StringBuilder codeAdapt = new StringBuilder("");
    private static int[][] codeHistory = new int[100][Utilities.codeSize];

    public static void rechercheDefender() {

         int nbTry = 0;
         int count = 0;
        String codeFromUser = Utilities.getTheString();
        String proposition = String.valueOf((int) (Math.random() * Math.pow(10, Utilities.codeSize)));
        System.out.println("Votre code secret est: " + codeFromUser);
        proposition = Utilities.codeInShape(proposition);
        System.out.println("L'IA propose le code suivant: " + proposition);
        System.out.println("ATTENTION! il ne reste plus que: " + (Utilities.allowedTry - nbTry) + " essais à l'IA");
        nbTry++;
        String responseFromUser = Utilities.getTheRechercheResponse();
        if (responseFromUser.equals(Utilities.solution)) {
            System.out.println("L'IA a trouvé le code!!");
            return;
        }
        do {

            if (nbTry >= Utilities.allowedTry) {
                System.out.println("FELICITATION!! Vous avez vaincu la machine");
                break;
            } else System.out.println("ATTENTION! il ne reste plus que: " + (5 - nbTry) + " essais à l'IA");
            proposition = iABreakYourCode(responseFromUser, proposition, count);
            System.out.println("l'IA propose le code  suivant: " + proposition);
            responseFromUser = Utilities.getTheRechercheResponse();
            if (responseFromUser.equals(Utilities.solution)) {
                System.out.println("L'IA a trouvé le code!!");
                break;
            }
            nbTry++;
            count++;
        } while (nbTry <= Utilities.allowedTry );
        return;

    }


    public static String iABreakYourCode(String responseFromUser, String proposition, int count) {

        int[] propTab = new int[Utilities.codeSize];
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
                number = (Character.getNumericValue(proposition.charAt(i)) + 9) / 2;
                if (count > 1 && codeHistory[count - 1][i] > codeHistory[count][i]) {
                    number =(codeHistory[count - 1][i] + codeHistory[count][i]) / 2;
                }
                if (count > 1 && codeHistory[count][i] == 8) number = 9;
                codeAdapt.append(number);
            } else if (responseFromUser.charAt(i) == '-') {
                number = (Character.getNumericValue(proposition.charAt(i))) / 2;
                if (count > 1 && codeHistory[count - 1][i] < codeHistory[count][i]) {
                    number =(codeHistory[count - 1][i] + codeHistory[count][i]) / 2;
                }
                if (count > 1 && codeHistory[count][i] == 1) number = 0;
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