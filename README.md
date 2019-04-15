DA Java EE Project 3 - JDK 11 
==
How to compile the application ?
-
From IntelliJ: you have to go on the Maven runtime window and run the command -> clean install
<br/>
###Launch of the IntelliJ console application
first of all you have to go in the folder who contains the jar file with the "cd" command then:<br/>
•	in debug mode execute the command: (you can replace "dev" by whatever you want)<br/>
java -Dfile.encoding=UTF-8 -jar mastermind-1.0-SNAPSHOT.jar dev <br/>
•	in player mode execute the command: <br/>
java -Dfile.encoding=UTF-8 -jar mastermind-1.0-SNAPSHOT.jar <br/>
###Launching the application in a specific directory:
•	extract the archive mastermind.zip in a directory <br/>
•	double click on mastermind.bat<br/>
•	and enjoy!<br/>
•	N.B.: if you want to execute in dev mode, you'll have to modify the .bat file by adding something at the end of the line<br/>
I.E.: java -Dfile.encoding=UTF-8 -jar mastermind-1.0-SNAPSHOT.jar dev<br/>

###How to Generate the Javadoc ?
In the Maven runtime window by running the command -> javadoc:javadoc <br/>
or by using in intelliJ Tools-> Generate Javadoc (select the output directory if there's none)<br/>
javadoc will be created in the directory in c:\javadoc<br/>

