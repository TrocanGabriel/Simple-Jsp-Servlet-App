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
import javax.websocket.Session;

import com.company.shop.model.ProductDto;
import com.company.shop.model.UserDto;
import com.company.shop.services.ProductService;
import com.company.shop.services.UserService;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String HOME_VIEW = "home.jsp";
	static List<ProductDto> cartProducts;

	
	UserService userService = new UserService();
	
	ProductService productService = new ProductService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Populate user data
		UserDto user = userService.getCurrentLoggedInUser();
		request.setAttribute("user", user);
		
		// Populate products data
		List<ProductDto> availableProducts = productService.getListAvailableProducts();
		request.setAttribute("products", availableProducts);
		
		// Forward to home page
		RequestDispatcher dispatcher = request.getRequestDispatcher(HOME_VIEW);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		HttpSession session = request.getSession();
		
		System.out.println("DoPost in home controller");
		
		if(session.getAttribute("cartProducts") != null ) {
		cartProducts = (List<ProductDto>) session.getAttribute("cartProducts");
		} else {
			cartProducts = new ArrayList<ProductDto>();

		}
			
		ProductDto newProduct = new ProductDto();

		  String[] selections = request.getParameterValues("selections");
		  if(selections == null) {
			  System.out.println("No products to add! Please reconsider");
			  response.sendRedirect("home");

		  } else {
		  List<String> list =  Arrays.asList(selections); 
		  for(int i = 0; i < list.size(); i ++) {
			  System.out.println(list.get(i));
		  }
		  
			ProductService productService = new ProductService();
			List<ProductDto> availableProducts = productService.getListAvailableProducts();
			for(int i = 0; i < list.size(); i++) {
				for(int j=0; j< availableProducts.size(); j++) {
					if(availableProducts.get(j).getId() == Long.valueOf(list.get(i))) {
						cartProducts.add(availableProducts.get(j));
						System.out.println("adding in cart");

					}
				}
			}
			

			  session.setAttribute("cartProducts",cartProducts );

			  for(int i = 0; i < cartProducts.size(); i ++) {
				  System.out.println("cart added:" + cartProducts.get(i));
			  }
			  
			  response.sendRedirect("home");
		 
		}
	}

}
