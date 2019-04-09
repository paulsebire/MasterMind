package com.ocr.paul;

import org.apache.logging.log4j.Logger;

/**
 * This class manage the main menu and the possibility to play again
 * it uses an instances of each class
 * and the logger
 */
public class Menu {
    private Utilities utilities;
    private ResearchGame researchGame;
    private MasterMind masterMind;
    private Logger logger;

    /**
     * constructor of the menu class
     * @param utilities: an instance of the utilities class
     * @param researchGame: an instance of the research game class
     * @param masterMind: an instance of the mastermind game class
     * @param logger: manage the logs
     */
    public Menu(Utilities utilities, ResearchGame researchGame, MasterMind masterMind, Logger logger) {
        this.utilities = utilities;
        this.researchGame = researchGame;
        this.masterMind = masterMind;
        this.logger = logger;
    }

    /**
     * this method display and manage the choice of game and the choice of mode
     */
    public void setTheMenu(){

        boolean playAgain=true;
        int gameChoice;
        utilities.fixTheColoursBounds();
        while (playAgain){
            logger.info("menu principal");
            System.out.println("A quel jeu désirez-vous jouer?");
            System.out.println("1 - Jeu de recherche");
            System.out.println("2 - Mastermind");
            System.out.println("3 - Quitter le programme");
            gameChoice=utilities.getTheNumber(1,3);

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
                            logger.info("l'utilisateur a choisi le mode Défenseur");
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
                case 3:
                    System.out.println("Merci d'avoir joué! à bientôt");
                    return;
            }
        }

    }


}
