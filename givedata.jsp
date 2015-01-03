<%@ page
	import="java.io.*, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
Date dateline = new Date();
System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"Givedata.jsp has been ACTIVATED!");

//as this program is executed, the supplied parameters will be put into the session, 
//necessary directories will be created if they don't exist already,
//

String sport="", league="", date="", action="";
try{
sport = request.getParameter("sport"); //all lowercase
league = request.getParameter("league"); //selection will vary for sports
date = request.getParameter("date"); //month-day-year
action = request.getParameter("action"); //varies with spports
} catch(Exception e){
	session.setAttribute("note","Data wasn't entered into all fields. Try again.");
}

if(sport.trim().equals("")||league.trim().equals("")||date.trim().equals("")||action.trim().equals(""))session.setAttribute("note","Data wasn't entered into all fields. Try again.");
else session.setAttribute("note","");

session.setAttribute("sport",sport);
session.setAttribute("league",league);
session.setAttribute("date",date);
		
//checks if main directory (C:/SC_Games) exists; if it doesn't exist, it creates it.
File rootDir = new File("C:/SC_Games");
if(!rootDir.exists()){
	if (rootDir.mkdir()) {
		System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+rootDir.getAbsolutePath()+" created");
	} else {
		System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"Failed to create "+rootDir.getAbsolutePath());
	}
}

//checks for sport specific directories inside SC_Games; if they don't exist, replace them. Only a few are named. More can be added.
//File rootDir = new File("C:/SC_Games");
File[] innerDirs = new File[]{
		new File(rootDir.getAbsolutePath()+"/football"),
		new File(rootDir.getAbsolutePath()+"/field_hockey"),
		new File(rootDir.getAbsolutePath()+"/cross_country"),
		new File(rootDir.getAbsolutePath()+"/water_polo"),
		new File(rootDir.getAbsolutePath()+"/volleyball"),
		new File(rootDir.getAbsolutePath()+"/tennis"),
		new File(rootDir.getAbsolutePath()+"/soccer"), 
		new File(rootDir.getAbsolutePath()+"/wrestling"), 
		new File(rootDir.getAbsolutePath()+"/basketball"), 
		new File(rootDir.getAbsolutePath()+"/baseball"), 
		new File(rootDir.getAbsolutePath()+"/track_and_field"), 
		new File(rootDir.getAbsolutePath()+"/math_club"), //lolololololololol
		new File(rootDir.getAbsolutePath()+"/dance_team"), 
		new File(rootDir.getAbsolutePath()+"/wrestling"), 
		 };

for(File file: innerDirs){
	if (!file.exists()) {
		if (file.mkdir()) {
			System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+file.getAbsolutePath() +" created");
		} else {
			System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"Failed to create " + file.getAbsolutePath());
		}
	}
}

//check if sport directory exists. If not don't do anything
if(!(new File(rootDir+"/"+sport).exists()) || sport.trim() == "") System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"The directory for the requested sport, "+ new File(rootDir+"/"+sport).getAbsolutePath()+", does not exist");

//otherwise		
else {	
	
	try{
	File outputFile = new File(rootDir.getAbsolutePath()+"/"+sport+"/"+league+"_"+date+".txt");//e.g. baseball/varsity_11-30-2014.txt
	
	//if the file doesn't exist yet, it will soon be created
	if(!outputFile.exists())System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+outputFile.getAbsolutePath()+" has been created");
	
	FileWriter writer = new FileWriter(outputFile, true);
	
	//find time
	DateFormat df = new SimpleDateFormat("h:mm a");
	Date d = new Date();
	
	writer.write("["+df.format(d)+"] "+action+"%");
	System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"Action "+action+" written to file "+outputFile.getAbsolutePath());
	writer.flush();
	writer.close();
	
	} catch(IOException e){e.printStackTrace();}
}

%>

<html>
<head>

<!-- automatically redirects -->
<meta http-equiv="refresh"
	content="0; url=http://73.162.233.8:8080/datacontributor.jsp" />

</head>
<body>
	If you aren't redirected immediately, click
	<a href=http://73.162.233.8:8080/datacontributor.jsp">here</a>
</body>
</html>
