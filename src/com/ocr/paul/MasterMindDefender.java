package com.ocr.paul;

public class MasterMindDefender {
    static StringBuilder resultFromIA= new StringBuilder();

    public static void masterMindDefenderMode(){
        int nbTry=0;
        String codeFromUser=Utilities.getTheColours();
        String codeFromIA=Utilities.getTheRandomColours();
        System.out.println("1-L'ordinateur propose le code suivant: "+codeFromIA);
        while (!codeFromIA.equals(codeFromUser)||nbTry < Utilities.allowedTry) {
            if (nbTry < Utilities.allowedTry) {
                System.out.println("ATTENTION! il ne reste plus que: " + (Utilities.allowedTry - nbTry) + " essais");
            } else{
                System.out.println("FELICITATIONS! l'ordinateur n'a pas réussi à trouver votre code.");
                break;
            }
            Utilities.askGoodColours();
            codeFromIA=mastermindDefender(codeFromUser,codeFromIA);
            nbTry++;
            if (nbTry < Utilities.allowedTry)System.out.println("2-L'ordinateur propose le code suivant: "+codeFromIA);

        }

        if (codeFromIA.equals(codeFromUser)) System.out.println("l'ordinateur à trouvé votre code: "+ codeFromUser);

        return;
    }

    public static String mastermindDefender (String codeFromUser, String codeFromIA){
        resultFromIA.setLength(0);
        for (int i=0;i<codeFromUser.length();i++){
            if (Utilities.compareChar(codeFromIA.charAt(i),codeFromUser.charAt(i))==0){
                resultFromIA.append(codeFromIA.charAt(i));
            }else resultFromIA.append( Utilities.minColours + (int)(Math.random()*Utilities.maxColours));
        }
        codeFromIA=resultFromIA.toString();
        return codeFromIA;
    }
}
