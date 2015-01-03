<%@ page import="java.io.*, java.util.Date, java.util.Scanner" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
Date dateline = new Date();
System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"Requestdata.jsp has been ACTIVATED");

String sport = request.getParameter("sport"); //all lowercase
String league = request.getParameter("league"); //selection will vary for sports
String date = request.getParameter("date"); //month-day-year

File mainDir = new File("C:/SC_Games");
if(!mainDir.exists())mainDir.mkdir();

File sportDir = new File(mainDir.getAbsolutePath()+"/"+sport);

String contents = "";

if(!sportDir.exists() || sport.equals(" ")){
	System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"The requested directory "+sportDir.getAbsolutePath()+" doesn't exist");
	contents += "Requested sport doesn't exist";
}

else {
	try{
		File file = new File(sportDir.getAbsolutePath()+"/"+league+"_"+date+".txt");
		Scanner scanner = new Scanner(file);
		
		String line = "";
		while(scanner.hasNext()){
			line = scanner.nextLine();
			contents+=line+"\n";
		}
		
	} catch(FileNotFoundException e){
		System.out.println("["+dateline.toString()+"] "+request.getRemoteAddr()+": "+"The requested file doesn't exist");
		contents += "The requested events could not be found because they do not exist or have not happened yet";
	}
	
}

%>
<%//returns all content of C:/SC_Games/sport/league_date.txt -->%>
<%= contents %>