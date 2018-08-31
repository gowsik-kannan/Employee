<%@ page language="java" import="setget.*,java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
	<table cellspacing="0" cellpadding="10" border="1" bordercolor="green">
<%		out.println("<center><h1>"+"Employee Details..."+"</h1><center>");
			out.println("<tr><th bgcolor=cyan>"+"EmpId"+"</th><th bgcolor=lightgreen>"+"Name"+"</th><th bgcolor=lightgreen>"+"Age"+"</th><th bgcolor=lightgreen>"+
					"E-mail"+"</th><th bgcolor=lightgreen>"+"PhoneNumber"+"</th></tr>");
			List<Employee> employeeList =(List<Employee>) request.getAttribute("empList");
			for(Employee empList : employeeList){
			out.println("<tr><td bgcolor=cyan>"+empList.getEmpId()+"</td><td>"+empList.getName()+"</td><td>"+
			empList.getAge()+"</td><td>"+empList.getEmail()+"</td><td>"+empList.getPhoneNumber()+"</td></tr>");
			}
	
	%>
	</table>
	</center>
</body>
</html>