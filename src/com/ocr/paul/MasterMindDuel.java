/*package com.ocr.paul;

public class MasterMindDuel {

    public static void masterMindDuel(){
        String codeFromUserFinding="";
        boolean victory=false;
        int nbTours=1;
        boolean successUser =false;
        System.out.println("--------------------------------------------------------------");
        System.out.println("Veuillez saisir votre code secret");
        String codeFromUser = Utilities.getTheColours();
        System.out.println("Votre code secret est: " + codeFromUser);
        String codeFromIA=Utilities.getTheRandomColours();
        System.out.println("L'IA a choisi son code secret");
        System.out.println("--------------------------------------------------------------");
        System.out.println("                       LE DUEL COMMENCE");
        String proposition = Utilities.getTheRandomColours();
        while(!victory){
            Utilities.displayNbTour(nbTours);
            System.out.println("l'IA propose le code  suivant: " + proposition);
            Utilities.askGoodColours();
            System.out.println("que pensez vous être la combinaison de la machine?");
            codeFromUserFinding = Utilities.getTheColours();
            System.out.println("vous proposez les couleurs suivantes: " + codeFromUserFinding);
            successUser = MasterMindChallenger.mastermindChallenger(codeFromIA, codeFromUserFinding);
            nbTours++;
            victory=Utilities.getTheMastermindResultDuel(codeFromUser,proposition,codeFromIA,successUser);
            proposition = MasterMindDefender.mastermindDefender(codeFromUser, proposition);
        }


        return;

    }



}*/
