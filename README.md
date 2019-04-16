DA Java EE Project 3 - JDK 11 
==
How to compile the application ?
-
From IntelliJ: you have to go on the Maven runtime window and run the command -> clean install
<br/>
if you want to change the configuration you have to edit the file config.properties
###Launch of the IntelliJ console application
first of all you have to go in the folder who contains the jar file with the "cd" command then:<br/>
• in Dev mode execute the command: (you can replace "dev" by whatever you want)


    java -Dfile.encoding=UTF-8 -jar mastermind-1.0-SNAPSHOT.jar dev 
• in player mode execute the command :<br/>


    java -Dfile.encoding=UTF-8 -jar mastermind-1.0-SNAPSHOT.jar
###Launching the application in a specific directory:
•	extract the archive mastermind.zip in a directory <br/>
•	double click on mastermind.bat or mastermind- DevMode<br/>
•	and enjoy!<br/>

###How to Generate the Javadoc ?
In the Maven runtime window by running the command -> javadoc:javadoc <br/>
or by using in intelliJ Tools-> Generate Javadoc (select the output directory if there's none)<br/>
javadoc will be created in the directory in c:\javadoc<br/>

