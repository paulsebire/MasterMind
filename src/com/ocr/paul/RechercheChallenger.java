package com.ocr.paul;

public class RechercheChallenger {

    private static String randomCode="";
    private static String result="";
    private static String codeFromUser="";
    private static int codeSize=4;
    private static int allowedTry=5;
    private static int nbTry;

    public static void rechercheChallenger(){
        result="";
        nbTry=0;
        randomCode = String.valueOf((int) (Math.random() * (double)Math.pow(10,codeSize)));
        randomCode=Utilities.codeInShape(randomCode);
        while (!result.equals("====")) {
            if (nbTry < allowedTry) {
                System.out.println("ATTENTION! il ne reste plus que: " + (allowedTry - nbTry) + " essais");
            } else{
                System.out.println("DOMMAGE! vous n'avez pas trouvé la solution");
                System.out.println("La solution était: "+randomCode);
                break;
            }
            codeFromUser = Utilities.getTheString();
            result = breakTheCode(codeFromUser, randomCode);
            System.out.println("Proposition: " + codeFromUser + " ==> Réponse: " + result);
            nbTry++;
            if (result.equals("====")) {
                System.out.println("FELICITATTONS!!! Vous avez trouvé le code secret!");
                break;
            }
        }

    }

        public static String breakTheCode(String codeFromUser, String codeForIA) {
            int codeSize=4;
            StringBuilder resultStringBuilder=new StringBuilder("");
            for (int i=0;i<codeSize;i++){
                if (Character.getNumericValue(codeFromUser.charAt(i))==Character.getNumericValue(codeForIA.charAt(i))){
                    resultStringBuilder.append('=');
                }else if (Character.getNumericValue(codeFromUser.charAt(i))<Character.getNumericValue(codeForIA.charAt(i))){
                    resultStringBuilder.append('+');
                }else resultStringBuilder.append('-');
            }

            return resultStringBuilder.toString();
        }





}
