import com.ocr.paul.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static Properties load(String filename) throws IOException, FileNotFoundException{
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(filename);
        try{
            properties.load(input);
            return properties;
        }
        finally{
            input.close();
        }
    }

    public static void main(String[] args) {

        int codeSize=0;
        int allowedTry=0;
        int numberOfColours=0;
        boolean devMode=false;

        try{
            // chargement des propriétés
            Properties prop = load("src\\com\\ocr\\paul\\config.properties");
            // Affichage des propriétés
            // Récupère la propriété ma.cle
            // Si la propriété n'existe pas, retourne la valeur par défaut "vide"
            codeSize = Integer.parseInt(prop.getProperty("codeSize", "4"));
            allowedTry = Integer.parseInt(prop.getProperty("allowedTry", "5"));
            numberOfColours = Integer.parseInt(prop.getProperty("numberOfColours", "4"));

            if (prop.getProperty("devMode","false").equals("true"))devMode=true;
            else devMode=false;
        }
        catch(Exception e){
            e.printStackTrace();
        }




        Utilities utilities = new Utilities(codeSize,allowedTry,numberOfColours);
        ResearchGame researchGame=new ResearchGame(utilities,devMode);
        MasterMind masterMind= new MasterMind(utilities,devMode);



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
                            while (playAgain){
                                System.out.println("Jeu de recherche - mode Challenger");
                                researchGame.rechercheChallenger();
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 2:
                            while (playAgain){
                                System.out.println("Jeu de recherche - mode Défenseur");
                                researchGame.rechercheDefender();
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 3:
                            while (playAgain){
                                System.out.println("Jeu de recherche - mode Duel VS IA");
                                researchGame.rechercheDuel();
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Vous avez choisi le Jeu de MasterMind");
                    System.out.println("Veuillez choisir le mode de jeu: 1-Challenger, 2-Défenseur, 3-Duel VS IA");
                    gameChoice=utilities.getTheNumber(1,3);
                   switch (gameChoice){
                        case 1:
                            while (playAgain){
                                System.out.println("Jeu du MasterMind - mode Challenger");
                                masterMind.masterMindChallengerMode();
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 2:
                            while (playAgain){
                                System.out.println("Jeu du MasterMind - mode Défenseur");
                                masterMind.masterMindDefenderMode();
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 3:
                            while (playAgain){
                                System.out.println("Jeu du MasterMind - mode Duel VS IA");
                                masterMind.masterMindDuel();
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                    }
                    break;
            }
            System.out.println("\n"+"Voulez-vous jouer à un autre jeu? 1-OUI, 2-NON");
            gameChoice=utilities.getTheNumber(1,2);
            if (gameChoice==2)playAgain=false;

        }
        System.out.println("Merci d'avoir joué! à bientôt");
    }
}
