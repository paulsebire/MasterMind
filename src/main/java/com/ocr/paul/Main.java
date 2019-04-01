package com.ocr.paul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.Properties;



public class Main {


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
        ResearchGame researchGame=new ResearchGame(utilities,devMode,logger);
        MasterMind masterMind= new MasterMind(utilities,devMode,logger);
        Menu menu = new Menu(utilities,researchGame,masterMind,logger);

        menu.setTheMenu();

    }
}
