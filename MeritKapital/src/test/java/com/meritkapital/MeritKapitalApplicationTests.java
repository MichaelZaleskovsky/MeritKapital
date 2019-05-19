package com.meritkapital;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meritkapital.products.RequestType;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MeritKapitalApplicationTests {

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private String uri = "/validate";
    private ObjectMapper objectMapper = new ObjectMapper();
    private String host = "http://localhost:8080";
	
	@Test
	public void singleSpotValidTrade() throws Exception {
		   
		String inputJson = "{\n" + 
		   		"	    \"test\":\n" + 
		   		"	    [\n" + 
		   		"	        {\"customer\":\"PLUTO1\","
		   		+ "			\"ccyPair\":\"EURUSD\","
		   		+ "			\"type\":\"Spot\","
		   		+ "			\"direction\":\"BUY\","
		   		+ "			\"tradeDate\":\"2016-08-11\","
		   		+ "			\"amount1\":1000000.00,"
		   		+ "			\"amount2\":1120000.00,"
		   		+ "			\"rate\":1.12,"
		   		+ "			\"valueDate\":\"2016-08-15\","
		   		+ "			\"legalEntity\":\"CS Zurich\","
		   		+ "			\"trader\":\"JohannBaumfiddler\"}\n" + 
		   		"	     ]\n" + 
		   		"}\n" + 
		   		"";
		RequestType request = objectMapper.readValue(inputJson, RequestType.class);
		
		String response = restTemplate.postForObject(host + uri, request, String.class);
		
		String correct = "[[]]";
		
        assertThat(response).contains(correct);

	}

	@Test
	public void singleSpotTradeWithSingleSpotRuleBreak() throws Exception {
		   
		String inputJson = "{\n" + 
		   		"	    \"test\":\n" + 
		   		"	    [\n" + 
		   		"	        {\"customer\":\"PLUTO1\","
		   		+ "			\"ccyPair\":\"EURUSD\","
		   		+ "			\"type\":\"Spot\","
		   		+ "			\"direction\":\"SELL\","
		   		+ "			\"tradeDate\":\"2016-08-11\","
		   		+ "			\"amount1\":1000000.00,"
		   		+ "			\"amount2\":1120000.00,"
		   		+ "			\"rate\":1.12,"
		   		+ "			\"valueDate\":\"2016-08-22\","
		   		+ "			\"legalEntity\":\"CS Zurich\","
		   		+ "			\"trader\":\"JohannBaumfiddler\"}\n" + 
		   		"	     ]\n" + 
		   		"}\n" + 
		   		"";
		RequestType request = objectMapper.readValue(inputJson, RequestType.class);
		
		String response = restTemplate.postForObject(host + uri, request, String.class);
		
		String correct = "[[\"Value date can not be late the 2 banking day from tradeDate; valueDate; tradeDate\"]]";
		
        assertThat(response).contains(correct);

	}

	@Test
	public void singleForwardTradeWithOneForwardAndOneAllRulesBreak() throws Exception {
		   
		String inputJson = "{\n" + 
		   		"	    \"test\":\n" + 
		   		"	    [\n" + 
		   		"	        {\"customer\":\"PLUTO2\","
		   		+ "			\"ccyPair\":\"EURUSD\","
		   		+ "			\"type\":\"Forward\","
		   		+ "			\"direction\":\"BUY\","
		   		+ "			\"tradeDate\":\"2016-08-11\","
		   		+ "			\"amount1\":1000000.00,"
		   		+ "			\"amount2\":1120000.00,"
		   		+ "			\"rate\":1.12,"
		   		+ "			\"valueDate\":\"2016-08-21\","
		   		+ "			\"legalEntity\":\"CS Zurich\","
		   		+ "			\"trader\":\"JohannBaumfiddler\"}\n" + 
		   		"	     ]\n" + 
		   		"}\n" + 
		   		"";
		RequestType request = objectMapper.readValue(inputJson, RequestType.class);
		
		String response = restTemplate.postForObject(host + uri, request, String.class);
		
		String correct = "[[\"Value date can not be weekend or holiday; valueDate\","
				+ "\"Value date can not be late the 5 banking day from tradeDate; valueDate; tradeDate\"]]";
		
        assertThat(response).contains(correct);

	}

	@Test
	public void singleOpionTradeWithOneOptionAndOneAllRulesBreak() throws Exception {
		   
		String inputJson = "{\n" + 
		   		"	    \"test\":\n" + 
		   		"	    [\n" + 
		   		"	        {\"customer\":\"PLUTO3\","
		   		+ 			"\"ccyPair\":\"EURUSD\","
		   		+ 			"\"type\":\"VanillaOption\","
		   		+ 			"\"style\":\"AMERICAN\","
		   		+ 			"\"direction\":\"SELL\","
		   		+ 			"\"strategy\":\"CALL\","
		   		+ 			"\"tradeDate\":\"2016-08-11\","
		   		+ 			"\"amount1\":1000000.00,"
		   		+ 			"\"amount2\":1120000.00,"
		   		+ 			"\"rate\":1.12,"
		   		+ 			"\"deliveryDate\":\"2016-08-22\","
		   		+ 			"\"expiryDate\":\"2016-08-19\","
		   		+ 			"\"excerciseStartDate\":\"2016-08-10\","
		   		+ 			"\"payCcy\":\"USD\","
		   		+ 			"\"premium\":0.20,"
		   		+ 			"\"premiumCcy\":\"USD\","
		   		+ 			"\"premiumType\":\"%USD\","
		   		+ 			"\"premiumDate\":\"2016-08-12\","
		   		+ 			"\"legalEntity\":\"CS Zurich\","
		   		+ 			"\"trader\":\"Johann Baumfiddler\"}\n" + 
		   		"	     ]\n" + 
		   		"}\n" + 
		   		"";
		RequestType request = objectMapper.readValue(inputJson, RequestType.class);
		
		String response = restTemplate.postForObject(host + uri, request, String.class);
		
		String correct = "[[\"Customer is not valid; customer\"," + 
				"\"Excercise Start Date must be after the trade date but before the expiry date; excerciseStartDate; tradeDate; expiryDate\"]]";
		
        assertThat(response).contains(correct);

	}

	@Test
	public void allTradesWithManyRulesBreak() throws Exception {
		   
		String inputJson = "{\n" + 
		   		"	    \"test\":\n" + 
		   		"	    [\n" + 
		   		"	        {\"customer\":\"PLUTO3\","
		   		+ 			"\"ccyPair\":\"OURUSD\","
		   		+ 			"\"type\":\"VanillaOption\","
		   		+ 			"\"style\":\"AMERICAN\","
		   		+ 			"\"direction\":\"SELL\","
		   		+ 			"\"strategy\":\"CALL\","
		   		+ 			"\"tradeDate\":\"2016-08-11\","
		   		+ 			"\"amount1\":1000000.00,"
		   		+ 			"\"amount2\":1120000.00,"
		   		+ 			"\"rate\":1.12,"
		   		+ 			"\"deliveryDate\":\"2016-08-22\","
		   		+ 			"\"expiryDate\":\"2016-08-19\","
		   		+ 			"\"excerciseStartDate\":\"2016-08-10\","
		   		+ 			"\"payCcy\":\"USD\","
		   		+ 			"\"premium\":0.20,"
		   		+ 			"\"premiumCcy\":\"USD\","
		   		+ 			"\"premiumType\":\"%USD\","
		   		+ 			"\"premiumDate\":\"2016-08-28\","
		   		+ 			"\"legalEntity\":\"CS Zurich\","
		   		+ 			"\"trader\":\"Johann Baumfiddler\"},\n"
		   		+ ""
		   		+ "			{\"customer\":\"PLUT02\","
		   		+ "			\"ccyPair\":\"EURUSC\","
		   		+ "			\"type\":\"Forward\","
		   		+ "			\"direction\":\"BUY\","
		   		+ "			\"tradeDate\":\"2016-08-11\","
		   		+ "			\"amount1\":1000000.00,"
		   		+ "			\"amount2\":1120000.00,"
		   		+ "			\"rate\":1.12,"
		   		+ "			\"valueDate\":\"2016-08-01\","
		   		+ "			\"legalEntity\":\"CS Zurich\","
		   		+ "			\"trader\":\"JohannBaumfiddler\"},\n"
		   		+ ""  
		   		+ "	        {\"customer\":\"PLUTO4\","
		   		+ "			\"ccyPair\":\"EYRUSD\","
		   		+ "			\"type\":\"Spot\","
		   		+ "			\"direction\":\"BUY\","
		   		+ "			\"tradeDate\":\"2016-08-01\","
		   		+ "			\"amount1\":1000000.00,"
		   		+ "			\"amount2\":1120000.00,"
		   		+ "			\"rate\":1.12,"
		   		+ "			\"valueDate\":\"2016-08-21\","
		   		+ "			\"legalEntity\":\"CS Zurich\","
		   		+ "			\"trader\":\"JohannBaumfiddler\"}\n" + 
		   		"	     ]\n" + 
		   		"}\n" + 
		   		"";
		RequestType request = objectMapper.readValue(inputJson, RequestType.class);
		
		String response = restTemplate.postForObject(host + uri, request, String.class);
		
		String correct = 
			  "["
				+ "[\"Currency is not correct according ISO 4217; ccyPair\","
				 + "\"Customer is not valid; customer\","
				 + "\"Expiry date and premium date must be before delivery date; expiryDate; premiumDate; deliveryDate\","
				 + "\"Excercise Start Date must be after the trade date but before the expiry date; excerciseStartDate; tradeDate; expiryDate\"],"
				+ "[\"Currency is not correct according ISO 4217; ccyPair\","
				 + "\"Customer is not valid; customer\","
				 + "\"Value date can not be before trade date; valueDate; tradeDate\"],"
				+ "[\"Currency is not correct according ISO 4217; ccyPair\","
				 + "\"Customer is not valid; customer\","
				 + "\"Value date can not be weekend or holiday; valueDate\","
				 + "\"Value date can not be late the 2 banking day from tradeDate; valueDate; tradeDate\"]"
			+ "]"; 
		
        assertThat(response).contains(correct);

	}

}
