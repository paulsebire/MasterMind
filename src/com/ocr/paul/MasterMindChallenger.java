package com.ocr.paul;

public class MasterMindChallenger {

    public static void masterMindChallengerMode(){
        String codeFromUser="";
        String codeFromIA = Utilities.getTheRandomColours();
        //System.out.println("codeFromIA: " + codeFromIA);

        boolean success=false;
        do {
            System.out.println("Que pensez-vous être la combianaison de l'ordinateur?");
            codeFromUser = Utilities.getTheColours();
            System.out.println("vous proposez les couleurs suivantes: " + codeFromUser);
            success=mastermindChallenger(codeFromIA, codeFromUser);
        }while (!success);
        System.out.println("FELICITATIONS, vous avez trouvé le code");
        return;
    }

    public static boolean mastermindChallenger (String codeFromIA, String codeFromUser){
        boolean success=false;
        boolean []validation= new boolean[codeFromIA.length()];
        int nbPresence=0;
        int bonnePosition=0;

        for (int i=0;i<codeFromIA.length();i++){
            if (Character.getNumericValue(codeFromUser.charAt(i))==Character.getNumericValue(codeFromIA.charAt(i))){
                validation[i]=true;
                bonnePosition++;
            } else{
                validation[i]=false;
            }
        }

        for (int i=0;i<codeFromIA.length();i++){
            if (Character.getNumericValue(codeFromUser.charAt(i))!=Character.getNumericValue(codeFromIA.charAt(i))){
                int j=0;
                boolean presence=false;
                while (j<codeFromIA.length() && !presence){
                    if (!validation[j]&&(Character.getNumericValue(codeFromUser.charAt(j))==Character.getNumericValue(codeFromIA.charAt(i)))){
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
