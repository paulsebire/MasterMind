package com.ocr.paul;

public class MasterMindChallenger {

    public static void masterMindChallengerMode(){
        String codeFromUser="";
        String codeFromIA = Utilities.getTheRandomColours();
        int nbTry=0;
        boolean playAgain=true;
        boolean success=false;
        while (!success) {
            playAgain=Utilities.allowedToPlay(nbTry,codeFromIA);
            if (!playAgain)break;
            System.out.println("Que pensez-vous être la combianaison de l'ordinateur?");
            codeFromUser = Utilities.getTheColours();
            System.out.println("vous proposez les couleurs suivantes: " + codeFromUser);
            success=mastermindChallenger(codeFromIA, codeFromUser);
            nbTry ++;
        }
        if (success)System.out.println("FELICITATIONS, vous avez trouvé le code");
        return;
    }

    public static boolean mastermindChallenger (String codeFromIA, String codeFromUser){
        boolean success=false;
        boolean []validation= new boolean[codeFromIA.length()];
        int nbPresence=0;
        int bonnePosition=0;

        for (int i=0;i<codeFromIA.length();i++){
            if (Utilities.compareChar(codeFromUser.charAt(i),codeFromIA.charAt(i))==0){
                validation[i]=true;
                bonnePosition++;
            } else{
                validation[i]=false;
            }
        }

        for (int i=0;i<codeFromIA.length();i++){
            if (Utilities.compareChar(codeFromUser.charAt(i),codeFromIA.charAt(i))!=0){
                int j=0;
                boolean presence=false;
                while (j<codeFromIA.length() && !presence){
                    if (!validation[j]&&(Utilities.compareChar(codeFromUser.charAt(j),codeFromIA.charAt(i))==0)){
                        nbPresence++;
                        validation[j]=true;
                        presence=true;
                    }
                    j++;
                }
            }
        }
        if (bonnePosition!=4){
            System.out.println("il y a "+ nbPresence+ " de couleurs mal placées");
            System.out.println("il y a "+ bonnePosition +" de couleurs bien placées");
            success=false;
        } else success=true;
        return success;
    }

}
