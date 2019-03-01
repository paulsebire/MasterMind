package com.ocr.paul;

public class RechercheChallenger {

    private static String randomCode="";
    private static String result="";
    private static String codeFromUser="";
    private static int nbTry;

    public static void rechercheChallenger(){
        boolean playAgain=true;
        result="";
        nbTry=0;
        randomCode = String.valueOf((int) (Math.random() * (double)Math.pow(10,Utilities.codeSize)));
        randomCode=Utilities.codeInShape(randomCode);
        while (!result.equals(Utilities.solution)) {
            playAgain=Utilities.allowedToPlay(nbTry,randomCode);
            if (!playAgain)break;
            /*if (nbTry < Utilities.allowedTry) {
                System.out.println("ATTENTION! il ne reste plus que: " + (Utilities.allowedTry - nbTry) + " essais");
            } else{
                System.out.println("DOMMAGE! vous n'avez pas trouvé la solution");
                System.out.println("La solution était: "+randomCode);
                break;
            }*/
            System.out.println("Que pensez-vous être le code de l'ordinateur?");
            codeFromUser = Utilities.getTheString();
            result = breakTheCode(codeFromUser, randomCode);
            System.out.println("Proposition: " + codeFromUser + " ==> Réponse: " + result);
            nbTry++;
            if (result.equals(Utilities.solution)) {
                System.out.println("FELICITATTONS!!! Vous avez trouvé le code secret!");
                break;
            }
        }

    }

        public static String breakTheCode(String codeFromUser, String codeForIA) {
            int codeSize=4;
            StringBuilder resultStringBuilder=new StringBuilder("");
            for (int i=0;i<codeSize;i++){
                if (Utilities.compareChar(codeFromUser.charAt(i),codeForIA.charAt(i))==0){
                    resultStringBuilder.append('=');
                }else if (Utilities.compareChar(codeFromUser.charAt(i),codeForIA.charAt(i))==-1){
                    resultStringBuilder.append('+');
                }else resultStringBuilder.append('-');
            }

            return resultStringBuilder.toString();
        }

}
