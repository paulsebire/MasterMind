package com.ocr.paul;

import java.util.HashMap;
import org.apache.logging.log4j.Logger;


public class ResearchGame {

    private Utilities utilities;
    private boolean devMode;

    private  String randomCode="";
    private  String result="";
    private  String codeFromUser="";
    private  int nbTry;
    private HashMap<Integer,String> codeHistory = new HashMap();
    private  StringBuilder codeAdapt = new StringBuilder("");
    private Logger logger;

    /**
     * Constructor of Mastermind's class
     * @param utilities is an instance of Utilities'class
     * @param devMode will be use in order to allow display
     */

    public ResearchGame(Utilities utilities, boolean devMode, Logger logger) {
        this.utilities=utilities;
        this.devMode= devMode;
        this.logger=logger;
    }

    /**
     * allow the code to use methods in Utilities
     * @return utilities is an instance of Utilities'class
     */
    public Utilities getUtilities() {
        return utilities;
    }
    /**
     * getter of boolean devMode, will be use in order to allow display
     * @return boolean
     */
    public boolean isDevMode() {
        return devMode;
    }

    /**
     * Manage the Research game in challenger mode
     */
    public void rechercheChallenger(){
        boolean playAgain=true;
        result="";
        nbTry=0;
        randomCode = String.valueOf((int) (Math.random() * (double)Math.pow(10,getUtilities().getCodeSize())));
        randomCode=getUtilities().codeInShape(randomCode);
        if (isDevMode()) System.out.println("la combinaison de l'Ia est :"+randomCode);
        logger.debug("la combinaison de l'Ia est :"+randomCode);
        while (!result.equals(getUtilities().solution)) {
            playAgain=getUtilities().allowedToPlay(nbTry,randomCode);
            if (!playAgain)break;
            System.out.println("Que pensez-vous être le code de l'ordinateur?");
            codeFromUser = getUtilities().getTheString();
            logger.debug("l'utilisateur a saisi le code: "+codeFromUser);
            result = breakTheCode(codeFromUser, randomCode);
            logger.debug("l'IA compare le code de l'utilisateur "+codeFromUser+ " avec son code: "+randomCode+" et donne la réponse: "+result);
            System.out.println("Proposition: " + codeFromUser + " ==> Réponse: " + result);
            nbTry++;
            logger.debug("l'IA compare sa réponse "+result+" avec la solution "+ getUtilities().solution);
            if (result.equals(getUtilities().solution)) {
                logger.debug("L'utilisateur a trouvé le code secret");
                System.out.println("FELICITATTONS!!! Vous avez trouvé le code secret!");
                break;
            }
        }
    }

    /**
     * this method compare the two strings in parameter
     * @param codeFromUser
     * @param codeForIA
     * @return a string composed of +,-,=
     */
    public String breakTheCode(String codeFromUser, String codeForIA) {
        StringBuilder resultStringBuilder=new StringBuilder("");
        for (int i=0;i<utilities.getCodeSize();i++){
            if (getUtilities().compareChar(codeFromUser.charAt(i),codeForIA.charAt(i))==0){
                resultStringBuilder.append('=');
            }else if (getUtilities().compareChar(codeFromUser.charAt(i),codeForIA.charAt(i))==-1){
                resultStringBuilder.append('+');
            }else resultStringBuilder.append('-');
        }

        return resultStringBuilder.toString();
    }

    /**
     * Manage the Research game in Defender mode
     */
    public void rechercheDefender() {
        int nbTry = 0;
        int count = 0;
        String codeFromUser = utilities.getTheString();
        String proposition = String.valueOf((int) (Math.random() * Math.pow(10, getUtilities().getCodeSize())));
        logger.debug("l'IA doit trouver le code: "+codeFromUser);
        System.out.println("Votre code secret est: " + codeFromUser);
        proposition = utilities.codeInShape(proposition);
        if (isDevMode()) System.out.println("la proposition de l'Ia est :"+proposition);
        logger.debug("l'IA propose le code :"+proposition);
        utilities.displayNbTour(nbTry+1);
        System.out.println("ATTENTION! il ne reste plus que: " + (getUtilities().getAllowedTry() - nbTry) + " essais à l'IA");
        System.out.println("L'IA propose le code suivant: " + proposition);
        nbTry++;
        String responseFromUser = utilities.getTheRechercheResponse();
        logger.debug("L'utilisateur compare son code "+codeFromUser+ " a celui proposé par l'IA: "+proposition+" et a répondu: "+responseFromUser);
        logger.debug("Le programme compare la réponse de l'utilisateur: "+responseFromUser+" à la solution: "+utilities.solution);
        if (responseFromUser.equals(utilities.solution)) {
            System.out.println("L'IA a trouvé le code!!");
            return;
        }
        do {

            if (nbTry >= getUtilities().getAllowedTry()) {
                logger.debug("nombre de tentative de l'IA dépasse le nombre autorisé");
                System.out.println("FELICITATION!! Vous avez vaincu la machine");
                break;
            } else {
                utilities.displayNbTour(nbTry+1);
                System.out.println("ATTENTION! il ne reste plus que: " + (getUtilities().getAllowedTry() - nbTry) + " essais");
            }
            proposition = iABreakYourCode(responseFromUser, proposition, count);
            logger.debug("tours numéro: "+(nbTry+1));
            logger.debug("suite à la réponse de l'utilisateur, la proposition de l'IA devient: "+proposition);
            System.out.println("l'IA propose le code  suivant: " + proposition);
            responseFromUser = utilities.getTheRechercheResponse();
            logger.debug("L'utilisateur compare son code "+codeFromUser+ " a celui proposé par l'IA: "+proposition+" et a répondu: "+responseFromUser);
            logger.debug("Le programme compare la réponse de l'utilisateur: "+responseFromUser+" à la solution: "+utilities.solution);
            if (responseFromUser.equals(utilities.solution)) {
                System.out.println("L'IA a trouvé le code!!");
                logger.debug("L'IA a trouvé le code!!");
                break;
            }
            nbTry++;
            count++;
        } while (nbTry <= getUtilities().getAllowedTry() );
        codeHistory.clear();
        return;

    }

    /**
     * this method create a string char by char adapted from previous propositions
     * @param responseFromUser composed of +,-,=
     * @param proposition string trying to find the user's code
     * @param count actualnumber of plays
     * @return a string adapted from previous propositions
     */
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
                    }else{ if (valueOFCharInHM(count-1,i)>valueOFCharInHM(count,i))
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
                            if (valueOFCharInHM(count-1,i)<valueOFCharInHM(count,i)){
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

    /**
     * convert a char from hashmap in integer
     * @param key
     * @param i
     * @return an integer of the char i in the hashmap indice key
     */
    public int valueOFCharInHM (int key, int i){
            return Character.getNumericValue(codeHistory.get(key).charAt(i));

    }

    /**
     * Manage the Research game in duel mode
     */
    public void rechercheDuel(){
        codeHistory.clear();
        boolean victory=false;
        String responseFromUser;
        String codeFromUserFinding;
        String result;
        int nbTours=1;
        System.out.println("--------------------------------------------------------------");
        System.out.println("Veuillez saisir votre code secret");
        String codeFromUser = utilities.getTheString();
        System.out.println("Votre code secret est: " + codeFromUser);
        String codeFromIA = String.valueOf((int) (Math.random() * (double)Math.pow(10,getUtilities().getCodeSize())));
        codeFromIA=utilities.codeInShape(codeFromIA);
        String proposition=String.valueOf((int) (Math.random() * (double)Math.pow(10,getUtilities().getCodeSize())));
        proposition=utilities.codeInShape(proposition);
        utilities.letTheDuelBegin(isDevMode(),codeFromIA);
        logger.debug("l'utilisateur a choisi son code secret: "+codeFromUser);
        logger.debug("le code secret de l'IA est: "+codeFromIA);
        while (!victory){
            utilities.displayNbTour(nbTours);
            System.out.println("l'IA propose le code  suivant: "+proposition);
            logger.debug("l'IA propose le code: "+proposition);
            responseFromUser=utilities.getTheRechercheResponse();
            logger.debug("la réponse de l'utilisateur sur la proposition de l'IA "+proposition+" sur son code secret "+codeFromUser+" est "+responseFromUser);
            proposition = iABreakYourCode(responseFromUser,proposition,nbTours);
            logger.debug("l'IA adapte sa proposition: "+proposition+" par rapport à la réponse de l'utilisateur: "+responseFromUser);
            System.out.println("que pensez-vous être le code de la machine?");
            codeFromUserFinding = utilities.getTheString();
            result = breakTheCode(codeFromUserFinding, codeFromIA);
            logger.debug("l'utilisateur propose le code "+codeFromUserFinding+" et l'IA lui répond: "+result);
            System.out.println("Proposition du joueur: " + codeFromUserFinding + " ==> Réponse: " + result);
            logger.debug("le programme vérifie si la réponse de l'utilisateur "+responseFromUser+ " ou la réponse de l'IA "+result+" correspond à la solution: "+getUtilities().solution);
            victory=utilities.getTheResult(responseFromUser,result,codeFromIA);
            nbTours++;
        }
    }

}
