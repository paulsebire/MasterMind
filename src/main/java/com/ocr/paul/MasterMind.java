package com.ocr.paul;

public class MasterMind {

    private Utilities utilities;
    private boolean devMode;
    private StringBuilder resultFromIA= new StringBuilder();

    /**
     * Constructor of Mastermind's class
     * @param utilities is an instance of Utilities'class
     * @param devMode will be use in order to allow display
     */
    public MasterMind(Utilities utilities, boolean devMode) {
        this.utilities = utilities;
        this.devMode = devMode;

    }

    /**
     * getter of boolean devMode, will be use in order to allow display
     * @return boolean
     */
    public boolean isDevMode() {
        return devMode;
    }

    /**
     * allow the code to use methods in Utilities
     * @return an instance of Utilities
     */
    public Utilities getUtilities() {
        return utilities;
    }

    /**
     * Manage the mastermind mode Challenger
     */
    public void masterMindChallengerMode(){
        String codeFromUser="";
        String codeFromIA = getUtilities().getTheRandomColours();
        if (isDevMode()) System.out.println("la combinaison de l'Ia est :"+codeFromIA);
        int nbTry=0;
        boolean playAgain=true;
        boolean success=false;
        while (!success) {
            playAgain=getUtilities().allowedToPlay(nbTry,codeFromIA);
            if (!playAgain)break;
            System.out.println("Que pensez-vous être la combianaison de l'ordinateur?");
            codeFromUser = getUtilities().getTheColours();
            System.out.println("vous proposez les couleurs suivantes: " + codeFromUser);
            success=mastermindChallenger(codeFromIA, codeFromUser);
            nbTry ++;
        }
        if (success)System.out.println("FELICITATIONS, vous avez trouvé le code");
        return;
    }

    /**
     * this method compare the strings in parameter
     * @param codeFromIA
     * @param codeFromUser
     * @return true if the two strings are the same
     */
    public boolean mastermindChallenger (String codeFromIA, String codeFromUser){
        boolean success=false;
        boolean []validation= new boolean[codeFromIA.length()];
        int nbPresence=0;
        int goodPosition=0;
        for (int i=0;i<codeFromIA.length();i++){
            if (getUtilities().compareChar(codeFromUser.charAt(i),codeFromIA.charAt(i))==0){
                validation[i]=true;
                goodPosition++;
            } else{
                validation[i]=false;
            }
        }
        for (int i=0;i<codeFromIA.length();i++){
            if (getUtilities().compareChar(codeFromUser.charAt(i),codeFromIA.charAt(i))!=0){
                int j=0;
                boolean presence=false;
                while (j<codeFromIA.length() && !presence){
                    if (!validation[j]&&(getUtilities().compareChar(codeFromUser.charAt(j),codeFromIA.charAt(i))==0)){
                        nbPresence++;
                        validation[j]=true;
                        presence=true;
                    }
                    j++;
                }
            }
        }
        if (goodPosition!=4){
            System.out.println("il y a "+ nbPresence+ " de couleurs mal placées");
            System.out.println("il y a "+ goodPosition +" de couleurs bien placées");
            success=false;
        } else success=true;
        return success;
    }

    /**
     * Manage the mastermind mode Defender
     */
    public void masterMindDefenderMode(){
        int nbTry=0;
        String codeFromUser=getUtilities().getTheColours();
        String codeFromIA=getUtilities().getTheRandomColours();
        System.out.println("L'ordinateur propose le code suivant: "+codeFromIA);
        while (!codeFromIA.equals(codeFromUser)||nbTry < getUtilities().getAllowedTry()) {
            if (nbTry < getUtilities().getAllowedTry()) {
                System.out.println("ATTENTION! il ne reste plus que: " + (getUtilities().getAllowedTry() - nbTry) + " essais");
            } else{
                System.out.println("FELICITATIONS! l'ordinateur n'a pas réussi à trouver votre code.");
                break;
            }
            getUtilities().askGoodColours();
            codeFromIA=mastermindDefender(codeFromUser,codeFromIA);
            nbTry++;
            if (nbTry < getUtilities().getAllowedTry())System.out.println("L'ordinateur propose le code suivant: "+codeFromIA);

        }

        if (codeFromIA.equals(codeFromUser)) System.out.println("l'ordinateur à trouvé votre code: "+ codeFromUser);

        return;
    }

    /**
     * the method compare the two strings in parameter, if unmatching char it is replaced by a random number
     * @param codeFromUser
     * @param codeFromIA
     * @return codeFromIA adapted to the user response
     */
    public String mastermindDefender (String codeFromUser, String codeFromIA){
        resultFromIA.setLength(0);
        for (int i=0;i<codeFromUser.length();i++){
            if (getUtilities().compareChar(codeFromIA.charAt(i),codeFromUser.charAt(i))==0){
                resultFromIA.append(codeFromIA.charAt(i));
            }else resultFromIA.append( getUtilities().minColours + (int)(Math.random()*getUtilities().maxColours));
        }
        codeFromIA=resultFromIA.toString();
        if (isDevMode()) System.out.println("la combinaison de l'Ia est :"+codeFromIA);
        return codeFromIA;
    }

    /**
     * Manage the mastermind mode Duel
     * call alternatively challenger and defender mode
     */
    public  void masterMindDuel(){
        String codeFromUserFinding="";
        boolean victory=false;
        int nbTours=1;
        boolean successUser =false;
        System.out.println("--------------------------------------------------------------");
        System.out.println("Veuillez saisir votre code secret");
        String codeFromUser = getUtilities().getTheColours();
        if (isDevMode()) System.out.println("la combinaison de l'Ia est :"+codeFromUser);
        System.out.println("Votre code secret est: " + codeFromUser);
        String codeFromIA=getUtilities().getTheRandomColours();
        if (isDevMode()) System.out.println("la combinaison de l'Ia est :"+codeFromIA);
        System.out.println("L'IA a choisi son code secret");
        System.out.println("--------------------------------------------------------------");
        System.out.println("                       LE DUEL COMMENCE");
        String proposition = getUtilities().getTheRandomColours();
        while(!victory){
            getUtilities().displayNbTour(nbTours);
            System.out.println("l'IA propose le code  suivant: " + proposition);
            getUtilities().askGoodColours();
            System.out.println("que pensez vous être la combinaison de la machine?");
            codeFromUserFinding = getUtilities().getTheColours();
            System.out.println("vous proposez les couleurs suivantes: " + codeFromUserFinding);
            successUser = mastermindChallenger(codeFromIA, codeFromUserFinding);
            nbTours++;
            victory=getUtilities().getTheMastermindResultDuel(codeFromUser,proposition,codeFromIA,successUser);
            proposition = mastermindDefender(codeFromUser, proposition);
        }
    }

}