package com.company.shop.controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.shop.model.ProductDto;
import com.company.shop.model.UserDto;
import com.company.shop.services.ProductService;


@WebServlet("/cart")
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CART_VIEW = "cart.jsp";
	
	static List<ProductDto> cartProducts;
	public boolean check ;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		cartProducts = (List<ProductDto>) session.getAttribute("cartProducts");
		if(cartProducts.size() == 0) {
			System.out.println("No items in cart!");
			request.setAttribute("cartProducts", cartProducts);
			 check = false;
		} else {
			
			for(int i = 0; i < cartProducts.size(); i ++) {
				  System.out.println("cart init:" + cartProducts.get(i));
			  }
			  
			request.setAttribute("cartProducts", cartProducts);
		check = true;
		}
		
		// Forward to cart page			
		request.setAttribute("check", check);

		RequestDispatcher dispatcher = request.getRequestDispatcher(CART_VIEW);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 long deletedProductId;
		HttpSession session = request.getSession();
		cartProducts = (List<ProductDto>) session.getAttribute("cartProducts");
		if(request.getParameter("id") != null) {
			  deletedProductId = Long.valueOf( request.getParameter("id"));
			  for(int i = 0; i< cartProducts.size(); i++) {
				  if(cartProducts.get(i).getId() == deletedProductId) {
					  cartProducts.remove(i);
					  break;
				  }
			  }
			  
			  session.setAttribute("cartProducts",cartProducts );
			  
		} else {
			System.out.println("error trying to delete product");
		}
		this.doGet(request,response);
//		  response.sendRedirect("cart");

			
	}

	
}

