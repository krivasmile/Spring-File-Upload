<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Status</title>
</head>
<body>
<p>First name: ${student.firstName}</p>
<p>Last name: ${student.lastName}</p>
<p>Age: ${student.age}</p>
<label>Photo:</label>
<br>
<img src="${student.fileDownloadUri}" alt="Photo" height="300" width="300">
</body>
</html>