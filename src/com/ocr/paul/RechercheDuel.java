package com.ocr.paul;

public class RechercheDuel {

    public static void rechercheDuel(){
        boolean victory=false;
        int nbTours=1;
        System.out.println("--------------------------------------------------------------");
        System.out.println("Veuillez saisir votre code secret");
        String codeFromUser = Utilities.getTheString();
        System.out.println("Votre code secret est: " + codeFromUser);
        String codeFromIA = String.valueOf((int) (Math.random() * (double)Math.pow(10,Utilities.codeSize)));
        String proposition=String.valueOf((int) (Math.random() * (double)Math.pow(10,Utilities.codeSize)));
        codeFromIA=Utilities.codeInShape(codeFromIA);
        proposition=Utilities.codeInShape(proposition);
        System.out.println("L'IA a choisi son code secret");
        System.out.println("--------------------------------------------------------------");
        System.out.println("                       LE DUEL COMMENCE");
        System.out.println("--------------------------------------------------------------");
        System.out.println("TOUR NUMERO: "+ nbTours);
        System.out.println("--------------------------------------------------------------");
        System.out.println("l'IA propose le code  suivant: "+proposition);
        String responseFromUser=Utilities.getTheRechercheResponse();
        proposition = RechercheDefender.iABreakYourCode(responseFromUser,proposition,nbTours-1);
        System.out.println("que pensez vous être le code de la machine?");
        String codeFromUserFinding = Utilities.getTheString();
        String result = RechercheChallenger.breakTheCode(codeFromUserFinding, codeFromIA);
        System.out.println("Proposition du joueur: " + codeFromUserFinding + " ==> Réponse: " + result);
        victory=Utilities.getTheResult(responseFromUser,result,codeFromIA);
        while (!victory){

            nbTours++;
            Utilities.displayNbTour(nbTours);
            System.out.println("l'IA propose le code  suivant: "+proposition);
            responseFromUser=Utilities.getTheRechercheResponse();
            proposition = RechercheDefender.iABreakYourCode(responseFromUser,proposition,nbTours-1);
            System.out.println("que pensez-vous être le code de la machine?");
            codeFromUserFinding = Utilities.getTheString();
            result = RechercheChallenger.breakTheCode(codeFromUserFinding, codeFromIA);
            System.out.println("Proposition du joueur: " + codeFromUserFinding + " ==> Réponse: " + result);
            victory=Utilities.getTheResult(responseFromUser,result,codeFromIA);
        }
        return;

    }
}
