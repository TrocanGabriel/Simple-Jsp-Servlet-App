<h3>Available offerings:</h3>


 <form action="home" method="post">
 
<c:forEach var="i" begin="0" end="${products.size() - 1}">

	<c:set var="product" scope="page" value="${products.get(i)}"/>  
	  
   	<p>
   		<b>${product.label}</b>
   		<input type = "checkbox" name= "selections" value="${products.get(i).getId()}" />
   		<br/>
   		<span>${product.description}</span> 
         </p>
   	
</c:forEach>  

 <input type="submit" value="Submit"/> 
 
</form>

