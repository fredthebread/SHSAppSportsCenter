<%@ page
	import="java.text.DateFormat, java.text.SimpleDateFormat, java.util.Date"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%

String date = (String)session.getAttribute("date");
String sport = (String)session.getAttribute("sport");
String league = (String)session.getAttribute("league");
String note = (String)session.getAttribute("note");

if(date == null){
	//by default the date will be the current date
	DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
	Date d = new Date();
	date = df.format(d);
}
if(sport == null)sport = "";
if(league == null)league = "";
if(note == null)note = "";

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SHS SC Data Contributor</title>
</head>
<body>
	<h1>SHS Sports Center</h1>
	<h3>Data Contributor</h3>

	<form method="get" action="givedata.jsp">

		Date <input type="text" name="date" size="8" value="<%=date%>"><br>

		Sport <input type="text" name="sport" size="8" value="<%=sport%>"><br>

		League <input type="text" name="league" size="8" value="<%=league%>"><br>

		Action <input type="text" name="action" size="8"><br> <input
			type="submit" value="Submit"><br>
		<br>
	</form>
	<h3><%=note%><!-- note -->
	</h3>
</body>
</html>