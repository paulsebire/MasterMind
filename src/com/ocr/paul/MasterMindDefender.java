package com.ocr.paul;

public class MasterMindDefender {
    static StringBuilder resultFromIA= new StringBuilder();

    public static void masterMindDefender(){
        String codeFromUser=Utilities.getTheColours();
        String codeFromIA=Utilities.getTheRandomColours();
        System.out.println("L'ordinateur propose le code suivant: "+codeFromIA);
        do {

            System.out.println("Combien il y a-t-il de couleurs bien placées?");
            Utilities.getTheNumber(0,4);
            System.out.println("Combien il y a-t-il de couleurs mal placées?");
            Utilities.getTheNumber(0,4);
            codeFromIA=mastermindDefender(codeFromUser,codeFromIA);
            System.out.println("L'ordinateur propose le code suivant: "+codeFromIA);
        }while (!codeFromIA.equals(codeFromUser));

        System.out.println("l'ordinateur à trouvé votre code: "+ codeFromUser);

        return;
    }

    public static String mastermindDefender (String codeFromUser, String codeFromIA){
        resultFromIA.setLength(0);
        for (int i=0;i<codeFromUser.length();i++){
            if (Character.getNumericValue(codeFromIA.charAt(i))==Character.getNumericValue(codeFromUser.charAt(i))){
                resultFromIA.append(codeFromIA.charAt(i));
            }else resultFromIA.append( 1 + (int)(Math.random()*4));
        }
        codeFromIA=resultFromIA.toString();
        return codeFromIA;
    }
}
