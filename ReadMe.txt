Team: Polar Bear

About:
	- This program implements a Library Management System. In this program, the users can view different books and can checkin/checkout any book of their preference. The program also calculates fines based on the books that have turned in late or have not been turned in yet.

Technical Dependencies:
	- Java Swing
	- MySQL
	- MySQL Workbench
	- OS: Windows
	- Java Class Library

Setting Up:

Step 1:
	- Run all the .java files can be found in the "Library Management System Code" in any JAVA IDE (We used ECLIPSE). 
		- For Java Files, Navigate to:
			- /"Library Management System Code"/src/Main
Step 2:
	- To install MySQL, follow the instructions in the “Connecting DB to Java.txt” file. 
Step 3:
	- Create all the needed tables using the “CREATE” commands in the “4347 Project Tables Script.sql” file.
	- Run the command “SHOW VARIABLES LIKE "secure_file_priv";”. 
	- Place all the required csv files in the directory returned as the output of the previous command. 
	- Run the import commands for the CSV files. 
Step 4:
	- Change the password in any .java files to your database password. 
		- Example: (Replace “password” with your database password)
			- Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password"); 

Note: If your database is already populated with all the necessary data, please skip to Step 4.
Please ensure that the table names used in the java program correspond to the table names in your database.
Detailed information about the libraries can be found in the design document.

Questions:
If you have any questions, please contact any one of us:
Ruben Thomas - rst180005@utdallas.edu
Tisbet Anaya - tjal170130@utdallas.edu
Oswaldo Alvarado-Amador - oxal170003@utdallas.edu
Syed Abidi - sma180003@utdallas.edu
Suhas Sompalli - sxs180253@utdallas.edu
