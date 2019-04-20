package com.wallet_ui.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Welcome {
	
	@GetMapping("/signup")
	public String getwelcome(Model model) {
		model.addAttribute("serverTime", new Date());
		model.addAttribute("message", "Hello Spring MVC 5!");
		return "sign-up";
	}
	
	@RequestMapping("/dashboard")
	public String getDashboard(Model model) {
		return "pages/home";
	}

	@GetMapping("/wallet")
	public String getWallet(Model model) {
		return "pages/wallet-list";
	}
	
	@GetMapping("/transaction")
	public String getTransaction(Model model) {
		return "pages/transaction-list";
	}
	
	@GetMapping("/print")
	public String getPrint(Model model) {
		return "pages/print";
	}
	
}