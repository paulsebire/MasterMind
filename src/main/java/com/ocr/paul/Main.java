package com.ocr.paul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class Main {


   // private final static Logger logger = LogManager.getLogger();

    public static void main(String[] args)throws FileNotFoundException{

        final  Logger logger = LogManager.getLogger();
        logger.info("démarrage du programme");
        int codeSize=0;
        int allowedTry=0;
        int numberOfColours=0;
        boolean devMode=false;


        Config config = new Config("config.properties");

        try{
            Properties prop = config.load();
            codeSize = Integer.parseInt(prop.getProperty("codeSize", "4"));
            allowedTry = Integer.parseInt(prop.getProperty("allowedTry", "5"));
            numberOfColours = Integer.parseInt(prop.getProperty("numberOfColours", "4"));

            if (args==null) devMode=true;
            else if (prop.getProperty("devMode","false").equals("true"))devMode=true;
                else devMode=false;
        }
        catch(Exception e){
            e.printStackTrace();
        }




        Utilities utilities = new Utilities(codeSize,allowedTry,numberOfColours,logger);
        ResearchGame researchGame=new ResearchGame(utilities,devMode);
        MasterMind masterMind= new MasterMind(utilities,devMode);



        boolean playAgain=true;
        int gameChoice;
        utilities.fixTheColoursBounds();
        while (playAgain){
            logger.info("menu principal");
            System.out.println("A quel jeu désirez-vous jouer?");
            System.out.println("1 - Jeu de recherche");
            System.out.println("2 - Mastermind");
            gameChoice=utilities.getTheNumber(1,2);

            switch (gameChoice){
                case 1:
                    System.out.println("Vous avez choisi le Jeu de Recherche");
                    logger.info("l'uitlisateur a choisi le jeu de recherche");
                    System.out.println("Veuillez choisir le mode de jeu: 1-Challenger, 2-Défenseur, 3-Duel VS IA");
                    gameChoice=utilities.getTheNumber(1,3);
                    switch (gameChoice){
                        case 1:
                            logger.info("l'utilisateur a choisi le mode Challenger");
                            while (playAgain){
                                System.out.println("Jeu de recherche - mode Challenger");
                                logger.info("lancement d'une partie Recherche en  mode Challenger");
                                researchGame.rechercheChallenger();
                                logger.info("fin de la partie Recherche en  mode Challenger");
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 2:
                            logger.info("l'utilisateur a choisi le mode Dééfenseur");
                            while (playAgain){
                                System.out.println("Jeu de recherche - mode Défenseur");
                                logger.info("lancement d'une partie Recherche en  mode Défenseur");
                                researchGame.rechercheDefender();
                                logger.info("fin de la partie Recherche en  mode Défenseur");
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 3:
                            logger.info("l'utilisateur a choisi le mode Duel");
                            while (playAgain){
                                System.out.println("Jeu de recherche - mode Duel VS IA");
                                logger.info("lancement d'une partie Recherche en  mode Duel");
                                researchGame.rechercheDuel();
                                logger.info("fin de la partie Recherche en  mode Duel");
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Vous avez choisi le Jeu de MasterMind");
                    logger.info("l'uitlisateur a choisi le jeu mastermind");
                    System.out.println("Veuillez choisir le mode de jeu: 1-Challenger, 2-Défenseur, 3-Duel VS IA");
                    gameChoice=utilities.getTheNumber(1,3);
                   switch (gameChoice){
                        case 1:
                            logger.info("l'utilisateur a choisi le mode Challenger");
                            while (playAgain){
                                System.out.println("Jeu du MasterMind - mode Challenger");
                                logger.info("lancement d'une partie Mastermind en  mode Challenger");
                                masterMind.masterMindChallengerMode();
                                logger.info("fin de la partie Mastermind en  mode Challenger");
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 2:
                            logger.info("l'utilisateur a choisi le mode Challenger");
                            while (playAgain){
                                System.out.println("Jeu du MasterMind - mode Défenseur");
                                logger.info("lancement d'une partie Mastermind en  mode Défenseur");
                                masterMind.masterMindDefenderMode();
                                logger.info("fin de la partie Mastermind en  mode Défenseur");
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                        case 3:
                            logger.info("l'utilisateur a choisi le mode Duel");
                            while (playAgain){
                                System.out.println("Jeu du MasterMind - mode Duel VS IA");
                                logger.info("lancement d'une partie Mastermind en  mode Duel");
                                masterMind.masterMindDuel();
                                logger.info("fin de la partie MasterMind en  mode Duel");
                                playAgain=utilities.wantToPlay();
                            }
                            playAgain=true;
                            break;
                    }
                    break;
            }

        }
        System.out.println("Merci d'avoir joué! à bientôt");
        logger.info("fin du programme");
    }
}
