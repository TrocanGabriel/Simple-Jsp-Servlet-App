
<c:if test="${check != false}">

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

	</c:if>
		