package com.wallet_ui.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wallet_ui.interfaces.IAccount;
import com.wallet_ui.model.Account;

@EnableWebMvc
@Controller
public class AccountController {

	private IAccount ia;
	
	@GetMapping("/account")
	public String getAccounts(HttpServletResponse resp, HttpServletRequest req, ModelMap model) {
		Cookie[] cookie = req.getCookies();
		String result = "";
		for (Cookie ck : cookie) {
			if (ck.getName().equalsIgnoreCase("customer")) {
				result = ck.getValue();
				break;
			}
		}
		int customerNumber = Integer.parseInt(result);
		System.out.println("Cookie : "+customerNumber);
		ia.getAccountByCustomerNumber(1095466);
//		List<Account>acc=ia.getAccountByCustomerNumber(1095466);
//		System.out.println(acc.size());
//		model.addAttribute("data", );
		return "pages/account-list";
	}

	
}
