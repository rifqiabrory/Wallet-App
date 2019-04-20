package com.wallet_ui.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wallet_ui.interfaces.ICustomer;
import com.wallet_ui.model.Customer;

@EnableWebMvc
@Controller
public class CustomerController {

	@Autowired
	private ICustomer ic;
	
	// @GetMapping("/login")
	// public ModelAndView login(Model model) {
	// model.addAttribute("customer", new Customer());
	// return new ModelAndView ("login","data",data);
	// }

	@GetMapping("/customer-list")
	public ModelAndView getCustomers() {
		List<Customer> data = ic.getListCustomer();
		return new ModelAndView("pages/customer-list", "data", data);
	}

}
