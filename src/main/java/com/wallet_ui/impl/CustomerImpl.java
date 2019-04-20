package com.wallet_ui.impl;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet_ui.dto.CommonResponse;
import com.wallet_ui.interfaces.ICustomer;
import com.wallet_ui.model.Customer;

public class CustomerImpl implements ICustomer {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final ParameterizedTypeReference<Customer> PARAMETERIZED_TYPE_COMMON_RESP_CUSTOMER = new ParameterizedTypeReference<Customer>() {};
	private static final TypeReference<CommonResponse<List<Customer>>> TYPE_COMMON_RESP_CUSTOMERS = new TypeReference<CommonResponse<List<Customer>>>() {};
	private static final TypeReference<CommonResponse<Customer>> TYPE_COMMON_RESP_CUSTOMER = new TypeReference<CommonResponse<Customer>>() {};
	
	// url from rest api
	private static final String URL_CUSTOMER_LIST = "http://localhost:8070/api/customers";
	private static final String URL_CUSTOMER_AUTH = "http://localhost:8070/api/login/%s/%s";

	
	@Override
	public Customer getById(int customerNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getListCustomer() {
		List<Customer> customers = null;

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(URL_CUSTOMER_LIST, String.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			System.out.println(String.format("error %s", response.getStatusCodeValue()));
		} else if (StringUtils.isEmpty(response.getBody())) {
			System.out.println("response null");
		} else {
			try {
				CommonResponse<List<Customer>> respBody = MAPPER.readValue(response.getBody(), TYPE_COMMON_RESP_CUSTOMERS);
				if (!respBody.getstatus().equalsIgnoreCase("101")) {
					System.out.println(String.format("Failed with message %s ", respBody.getmessage()));
				} else {
					customers = respBody.getData();
				}

			} catch (IOException e) {
				System.out.println("Error at "+e.getMessage());
			}
		}

		return customers;
	}

	@Override
	public Customer delete(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getUserLogin(String username, String password) {
		Customer user = null;
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(URL_CUSTOMER_AUTH, username, password);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {
            try {
                CommonResponse<Customer> res = MAPPER.readValue(response.getBody(), TYPE_COMMON_RESP_CUSTOMER);
                if (res.getstatus().equalsIgnoreCase("101")){
                	user = res.getData();
                } else {
                	System.out.println(String.format("User not found!"));
                }
            } catch (Exception e){
                System.out.println("Error at "+e.getMessage());
            }
        }
        return user;
	}

	
}
