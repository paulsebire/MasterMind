package com.ocr.paul;

import java.util.HashMap;


public class ResearchGame {

    private Utilities utilities;
    private boolean devMode;

    private  String randomCode="";
    private  String result="";
    private  String codeFromUser="";
    private  int nbTry;
    private HashMap<Integer,String> codeHistory = new HashMap();
    private  StringBuilder codeAdapt = new StringBuilder("");




    public ResearchGame(Utilities utilities, boolean devMode) {
        this.utilities=utilities;
        this.devMode= devMode;
    }

    public Utilities getUtilities() {
        return utilities;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void rechercheChallenger(){
        boolean playAgain=true;
        result="";
        nbTry=0;
        randomCode = String.valueOf((int) (Math.random() * (double)Math.pow(10,getUtilities().getCodeSize())));
        randomCode=getUtilities().codeInShape(randomCode);
        if (isDevMode()) System.out.println("la combinaison de l'Ia est :"+randomCode);
        while (!result.equals(getUtilities().solution)) {
            playAgain=getUtilities().allowedToPlay(nbTry,randomCode);
            if (!playAgain)break;
            System.out.println("Que pensez-vous être le code de l'ordinateur?");
            codeFromUser = getUtilities().getTheString();
            result = breakTheCode(codeFromUser, randomCode);
            System.out.println("Proposition: " + codeFromUser + " ==> Réponse: " + result);
            nbTry++;
            if (result.equals(getUtilities().solution)) {
                System.out.println("FELICITATTONS!!! Vous avez trouvé le code secret!");
                break;
            }
        }
    }

    public String breakTheCode(String codeFromUser, String codeForIA) {
        int codeSize=4;
        StringBuilder resultStringBuilder=new StringBuilder("");
        for (int i=0;i<codeSize;i++){
            if (getUtilities().compareChar(codeFromUser.charAt(i),codeForIA.charAt(i))==0){
                resultStringBuilder.append('=');
            }else if (getUtilities().compareChar(codeFromUser.charAt(i),codeForIA.charAt(i))==-1){
                resultStringBuilder.append('+');
            }else resultStringBuilder.append('-');
        }

        return resultStringBuilder.toString();
    }

    public void rechercheDefender() {
        int nbTry = 0;
        int count = 0;
        String codeFromUser = utilities.getTheString();
        String proposition = String.valueOf((int) (Math.random() * Math.pow(10, getUtilities().getCodeSize())));
        if (isDevMode()) System.out.println("la proposition de l'Ia est :"+proposition);
        System.out.println("Votre code secret est: " + codeFromUser);
        proposition = utilities.codeInShape(proposition);
        utilities.displayNbTour(nbTry+1);
        System.out.println("ATTENTION! il ne reste plus que: " + (getUtilities().getAllowedTry() - nbTry) + " essais à l'IA");
        System.out.println("L'IA propose le code suivant: " + proposition);
        nbTry++;
        String responseFromUser = utilities.getTheRechercheResponse();
        if (responseFromUser.equals(utilities.solution)) {
            System.out.println("L'IA a trouvé le code!!");
            return;
        }
        do {

            if (nbTry >= getUtilities().getAllowedTry()) {
                System.out.println("FELICITATION!! Vous avez vaincu la machine");
                break;
            } else {
                utilities.displayNbTour(nbTry+1);
                System.out.println("ATTENTION! il ne reste plus que: " + (5 - nbTry) + " essais à l'IA");
            }
            proposition = iABreakYourCode(responseFromUser, proposition, count);
            System.out.println("l'IA propose le code  suivant: " + proposition);
            responseFromUser = utilities.getTheRechercheResponse();
            if (responseFromUser.equals(utilities.solution)) {
                System.out.println("L'IA a trouvé le code!!");
                break;
            }
            nbTry++;
            count++;
        } while (nbTry <= getUtilities().getAllowedTry() );
        codeHistory.clear();
        return;

    }


    public String iABreakYourCode(String responseFromUser, String proposition, int count) {

        int number;
        codeAdapt.setLength(0);

        codeHistory.put(count,proposition);
        if (isDevMode()) System.out.println("l'historique des combinaisons de l'IA est :"+codeHistory.toString());
        for (int i = 0; i < responseFromUser.length(); i++) {
            if (responseFromUser.charAt(i) == '+') {
                number = (Character.getNumericValue(proposition.charAt(i)) + 9) / 2;
                if (count >= 2 ){
                    if (valueOFCharInHM(count-1,i) > valueOFCharInHM(count,i)) {
                        number =(valueOFCharInHM(count-1,i)+ valueOFCharInHM(count,i)) / 2;
                    }else{ if (valueOFCharInHM(count-2,i)>valueOFCharInHM(count-1,i))
                            number =(valueOFCharInHM(count-2,i) + valueOFCharInHM(count-1,i)) / 2;
                            else number = (Character.getNumericValue(proposition.charAt(i)) + 9) / 2;
                    }
                }
                if (count > 1 && valueOFCharInHM(count,i) == 8) number = 9;
                codeAdapt.append(number);
            } else if (responseFromUser.charAt(i) == '-') {
                number = (Character.getNumericValue(proposition.charAt(i))) / 2;
                if (count >= 2) {
                    if (valueOFCharInHM(count-1,i) < valueOFCharInHM(count,i)) {
                        number = (valueOFCharInHM(count-1,i) +valueOFCharInHM(count,i)) / 2;
                    } else{
                            if (valueOFCharInHM(count-2,i)<valueOFCharInHM(count-1,i)){
                            number =(valueOFCharInHM(count-2,i) + valueOFCharInHM(count-1,i)) / 2;
                            }else number = (Character.getNumericValue(proposition.charAt(i))) / 2;

                    }
                }
                if (count > 1 &&valueOFCharInHM(count,i) == 1) number = 0;
                codeAdapt.append(number);
            } else {
                number = (Character.getNumericValue(proposition.charAt(i)));
                codeAdapt.append(number);
            }
        }
        proposition = codeAdapt.toString();
        return proposition;



    }

    public int valueOFCharInHM (int key, int i){
            return Character.getNumericValue(codeHistory.get(key).charAt(i));

    }


    public void rechercheDuel(){
        codeHistory.clear();
        boolean victory=false;
        int nbTours=1;
        System.out.println("--------------------------------------------------------------");
        System.out.println("Veuillez saisir votre code secret");
        String codeFromUser = utilities.getTheString();
        System.out.println("Votre code secret est: " + codeFromUser);
        String codeFromIA = String.valueOf((int) (Math.random() * (double)Math.pow(10,getUtilities().getCodeSize())));

        String proposition=String.valueOf((int) (Math.random() * (double)Math.pow(10,getUtilities().getCodeSize())));
        codeFromIA=utilities.codeInShape(codeFromIA);
        if (isDevMode()) System.out.println("la combinaison de l'Ia est :"+codeFromIA);
        proposition=utilities.codeInShape(proposition);
        if (isDevMode()) System.out.println("la proposition de l'Ia est :"+proposition);
        System.out.println("L'IA a choisi son code secret");
        System.out.println("--------------------------------------------------------------");
        System.out.println("                       LE DUEL COMMENCE");
        System.out.println("--------------------------------------------------------------");
        System.out.println("TOUR NUMERO: "+ nbTours);
        System.out.println("--------------------------------------------------------------");
        System.out.println("l'IA propose le code  suivant: "+proposition);
        String responseFromUser=utilities.getTheRechercheResponse();
        proposition = iABreakYourCode(responseFromUser,proposition,nbTours-1);
        System.out.println("que pensez vous être le code de la machine?");
        String codeFromUserFinding = utilities.getTheString();
        String result = breakTheCode(codeFromUserFinding, codeFromIA);
        System.out.println("Proposition du joueur: " + codeFromUserFinding + " ==> Réponse: " + result);
        victory=utilities.getTheResult(responseFromUser,result,codeFromIA);
        while (!victory){

            nbTours++;
            utilities.displayNbTour(nbTours);
            System.out.println("l'IA propose le code  suivant: "+proposition);
            responseFromUser=utilities.getTheRechercheResponse();
            proposition = iABreakYourCode(responseFromUser,proposition,nbTours-1);
            System.out.println("que pensez-vous être le code de la machine?");
            codeFromUserFinding = utilities.getTheString();
            result = breakTheCode(codeFromUserFinding, codeFromIA);
            System.out.println("Proposition du joueur: " + codeFromUserFinding + " ==> Réponse: " + result);
            victory=utilities.getTheResult(responseFromUser,result,codeFromIA);
        }
    }

}
