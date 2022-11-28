<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Events</title>
</head>
<body>
	<h3>Create new Event</h3>
	<form action="/PlanerWEB/CreateEventServlet" method="post">
		Description: <input type="text" name="description"><br><br>
		Starting Date:<input type="datetime-local" name="fromDate"><br><br>
		Ending Date: <input type="datetime-local" name="toDate"><br><br>
		Event type: <select name="eventTypeId">
						<c:forEach var="eventType" items="${planerbean.getTypes()}">
							<option value="${eventType.id}">${eventType.name}</option>
						</c:forEach>
					</select><br><br>
		<input type="submit" value="Create">
	</form>
	<h3>Show Events</h3>
	<form action="/PlanerWEB/ShowEventServlet" method="get">
		Date: <input type="date" name="eventsDate"><br><br>
		<input type="submit" value="Show">
	</form>
	<c:if test="${!empty events}">
		<table border="1">
		<tr>
			<th>From</th>
			<th>To</th>
			<th>Event type</th>
			<th>Description</th>
		</tr>
		<c:forEach var="event" items="${events}">
			<tr>
				<td>${event.fromDate}</td>
				<td>${event.toDate}</td>
				<td>${event.eventType.name}</td>
				<td>${event.description}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>