import com.ocr.paul.Utilities;

public class Main {

    public static void main(String[] args) {
        Utilities utilities = new Utilities();


        boolean playAgain=true;
        int gameChoice=0;


        while (playAgain){
            System.out.println("A quel jeu désirez-vous jouer?");
            System.out.println("1 - Jeu de recherche");
            System.out.println("2 - Mastermind");
            gameChoice=Utilities.getTheNumber(1,2);
            switch (gameChoice){
                case 1:
                    System.out.println("Vous avez choisi le Jeu de Recherche");
                    System.out.println("Veuillez choisir le mode de jeu: 1-Challenger, 2-Défenseur, 3-Duel VS IA");
                    gameChoice=Utilities.getTheNumber(1,3);
                    switch (gameChoice){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }

                    break;
                case 2:
                    System.out.println("Vous avez choisi le Jeu de MasterMind");
                    System.out.println("Veuillez choisir le mode de jeu: 1-Challenger, 2-Défenseur, 3-Duel VS IA");
                    gameChoice=Utilities.getTheNumber(1,3);
                   /* switch (gameChoice){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }*/
                    break;
            }
            System.out.println("Voulez-vous faire une autre partie? 1-OUI, 2-NON");
            gameChoice=Utilities.getTheNumber(1,2);
            if (gameChoice==1)playAgain=true;
            else playAgain=false;
        }

    }
}
