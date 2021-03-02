<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="UTF-8">
<title>lesson-19-upload jsp</title>
</head>

<body>
		<h3>Student Registration Form</h3>
		<form method="POST" action="/uploadStudent" enctype="multipart/form-data">
		<label>First Name<br> 
		<input type="text" name="firstName"></label>
		<br> 
		<label>Last Name<br>
		<input type="text" name="lastName"></label> 
		<br>
		<label>Age<br> 
		<input type="number" id="age" name="age"></label>
		<br>
		<br> 
		<input type="file" name="file" required/>
			<button type="submit">Submit</button>
		</form>
</body>

</html>