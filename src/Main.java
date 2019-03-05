import com.ocr.paul.*;

public class Main {

    private static Utilities utilities = new Utilities(4,5);
    private static ResearchGame researchGame=new ResearchGame(utilities);
    // private static MasterMind masterMind= new MasterMind();

    public static void main(String[] args) {

        boolean playAgain=true;
        int gameChoice;
        utilities.fixTheColoursBounds();

        while (playAgain){
            System.out.println("A quel jeu désirez-vous jouer?");
            System.out.println("1 - Jeu de recherche");
            System.out.println("2 - Mastermind");
            gameChoice=utilities.getTheNumber(1,2);
            switch (gameChoice){
                case 1:
                    System.out.println("Vous avez choisi le Jeu de Recherche");
                    System.out.println("Veuillez choisir le mode de jeu: 1-Challenger, 2-Défenseur, 3-Duel VS IA");
                    gameChoice=utilities.getTheNumber(1,3);
                    switch (gameChoice){
                        case 1:
                            System.out.println("Jeu de recherche - mode Challenger");
                            researchGame.rechercheChallenger();
                            break;
                        case 2:
                            System.out.println("Jeu de recherche - mode Défenseur");
                            researchGame.rechercheDefender();
                            break;
                        case 3:
                            System.out.println("Jeu de recherche - mode Duel VS IA");
                            researchGame.rechercheDuel();
                            break;
                    }
                    break;
               /* case 2:
                    System.out.println("Vous avez choisi le Jeu de MasterMind");
                    System.out.println("Veuillez choisir le mode de jeu: 1-Challenger, 2-Défenseur, 3-Duel VS IA");
                    gameChoice=Utilities.getTheNumber(1,3);
                   switch (gameChoice){
                        case 1:
                            System.out.println("Jeu du MasterMind - mode Challenger");
                            masterMindChallenger.masterMindChallengerMode();
                            break;
                        case 2:
                            System.out.println("Jeu du MasterMind - mode Défenseur");
                            masterMindDefender.masterMindDefenderMode();
                            break;
                        case 3:
                            System.out.println("Jeu du MasterMind - mode Duel VS IA");
                            masterMindDuel.masterMindDuel();
                            break;
                    }
                    break;*/
            }
            System.out.println("\n"+"Voulez-vous faire une autre partie? 1-OUI, 2-NON");
            gameChoice=utilities.getTheNumber(1,2);
            if (gameChoice==1)playAgain=true;
            else playAgain=false;
        }

    }
}
