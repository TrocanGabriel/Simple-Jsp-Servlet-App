<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page errorPage="error.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>

<c:forEach var="i" begin="0" end="${cartProducts.size() - 1}">

	<c:set var="cartProduct" scope="page" value="${cartProducts.get(i)}"/>  
	  <form action="cart" method="POST">
	  
	  		<p>
		   		
		   		Name: <b>${cartProduct.label }</b>
		   		Description:<span>${cartProduct.description}</span> 
		   	</p>
		   	<input type="hidden" name="id" value="${cartProduct.id}"/>		   	
		   	<input type="submit" name="delete"  value="Delete" />
   		</form>
</c:forEach> 		

</body>
<footer>
 	<a href="<c:url value="/home" />" > Change cart!</a>

</footer>
</html>