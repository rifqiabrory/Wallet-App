package com.wallet_ui.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wallet_ui.interfaces.ICustomer;
import com.wallet_ui.model.Customer;

@EnableWebMvc
@Controller
public class AuthController {
	
	@Autowired
	private ICustomer ic;
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}
	
	@PostMapping("/auth")
	public ModelAndView getLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Customer data = ic.getUserLogin(username, password);
		
		if (StringUtils.isEmpty(data)) {
			return new ModelAndView("redirect:login", "customer", new Customer());
		} else {
			int customerNumber = data.getCustomerNumber();
			String cif = Integer.toString(customerNumber);
			Cookie cookie = new Cookie("customer",cif);
			
			res.addCookie(cookie);
			req.getSession().setAttribute("customer", cif);
			
			return new ModelAndView("redirect:dashboard", "data", data);
		}
	}

	@GetMapping("/register")
	public String getRegister() {
		return "sign-up";
	}
	
}