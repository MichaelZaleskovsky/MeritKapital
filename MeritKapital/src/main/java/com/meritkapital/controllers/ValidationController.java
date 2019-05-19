package com.meritkapital.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meritkapital.products.RequestType;

/**
 * REST Controller for MeritKapital Application
 * @author Zaleskovskiy Mikhail
 *
 */
@RestController
public class ValidationController {
	
	/**
	 * Implementation endpoint "/validate" for POST request
	 * @param request - JSON with the list of trades, needs to be validated
	 * @return response - JSON with list of errors list for each trades, according input request.
	 * Response list have the same elements like input data in the same order.
	 * Every errors list contain descriptions of all errors that found in corresponding trade 
	 * and the list of keys with wrong data, separated by ";".
	 * The empty list mean that trade is valid.
	 */
	@CrossOrigin
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public List<List<String>> validate(@RequestBody RequestType request) {
		List<List<String>> response = new LinkedList<>();
		System.out.println("Request for validate received");
		request.getTest().forEach(trade -> response.add(trade.validateTrade()));
		return response;
	}
	
	/**
	 * Controller support graceful shutdown via endpoint "/actuator/shutdown"
	 */

}
