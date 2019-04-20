package com.wallet_ui.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet_ui.dto.CommonResponse;
import com.wallet_ui.interfaces.IAccount;
import com.wallet_ui.model.Account;
import com.wallet_ui.model.Customer;

public class AccountImpl implements IAccount {

	private static final ObjectMapper MAPPER = new ObjectMapper();// object mapper thread-safe
	private static final ParameterizedTypeReference<Account> PARAMETERIZED_TYPE_COMMON_RESP_ACCOUNT = new ParameterizedTypeReference<Account>() {
	};
	private static final TypeReference<CommonResponse<List<Account>>> TYPE_COMMON_RESP_ACCOUNTS = new TypeReference<CommonResponse<List<Account>>>() {
	};
	private static final TypeReference<Account> TYPE_COMMON_RESP_ACCOUNT = new TypeReference<Account>() {
	};

	private static final String URL_ACCOUNT_LIST = "http://localhost:8070/api/accounts";
	
	@Override
	public List<Account> getListAccount() {
		List<Account> accounts = null;

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(URL_ACCOUNT_LIST, String.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			System.out.println(String.format("error %s", response.getStatusCodeValue()));
		} else if (StringUtils.isEmpty(response.getBody())) {
			System.out.println("response null");
		} else {
			try {
				List<Account> respBody = MAPPER.readValue(response.getBody(), TYPE_COMMON_RESP_ACCOUNTS);

				accounts = respBody;

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		return accounts;
	}

	@Override
	public Account save(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account delete(Account accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountByCustomerNumber(int customerNumber) {
		// TODO Auto-generated method stub
		List<Account> listAccounts = null;
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("http://localhost:8070/api/account?customerNumber=1095466",customerNumber);
		System.out.println(url);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			System.out.println(String.format("error %s", response.getStatusCodeValue()));
		} else if (StringUtils.isEmpty(response.getBody())) {
			System.out.println("response null");
		} else {
			try {
				CommonResponse<List<Account>> respBody = MAPPER.readValue(response.getBody(),
						TYPE_COMMON_RESP_ACCOUNTS);
				if (!respBody.getstatus().equalsIgnoreCase("101")) {
					System.out.println(String.format("failed with message %s", respBody.getmessage()));
				} else {
					listAccounts = respBody.getData();
				}
			} catch (IOException e) {
				System.out.println("Error at "+e.getMessage());
			}
		}
		return listAccounts;
	}

}
