package com.meritkapital.products;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.meritkapital.rules.AllCurrencyValidRule;
import com.meritkapital.rules.AllValidCustomerRule;
import com.meritkapital.rules.Rule;

/**
 * Abstract class represent common trade and the method how to validate specific trade.
 * In order to add new trade type you need to extend this class, add @JsonTypeName annotation
 * to the sub-class and refresh @JsonSubTypes annotation. Mapping sub-classes during deserialization
 * depend on content of field "type" in input JSON.
 * In order to add new rule for all type of trades you need add new element to List rules in constructor.
 * In order to add new rules for specific trade type you need add new rules in according class.
 * @author Zaleskovskiy Mikhail
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Option.class, name = "VanillaOption"),
        @JsonSubTypes.Type(value = Spot.class, name = "Spot"),
        @JsonSubTypes.Type(value = Forward.class, name = "Forward")}
)
public abstract class AbstractTrade {
	private String customer;
	private String ccyPair;
	private String type;
	private String direction;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Calendar tradeDate;
	private double amount1;
	private double amount2;
	private double rate;
	private String legalEntity;
	private String trader;
	List<Rule> rules;
	
	public AbstractTrade() {
		rules = new LinkedList<>();
		rules.add(new AllCurrencyValidRule());
		rules.add(new AllValidCustomerRule());
	}
	
	public List<String> validateTrade(){
		List<String> result = new LinkedList<>();
		rules.forEach(rule -> {
			String error = rule.check(this);
			if (!error.equals("")) result.add(error);
		});
		return result;
	}

	
	  public String getCustomer() { return customer; }
	  
	  public void setCustomer(String customer) { this.customer = customer; }
	  
	  public String getCcyPair() { return ccyPair; }
	  
	  public void setCcyPair(String ccyPair) { this.ccyPair = ccyPair; }
	  
	  public String getType() { return type; }
	  
	  public void setType(String type) { this.type = type; }
	  
	  public String getDirection() { return direction; }
	  
	  public void setDirection(String direction) { this.direction = direction; }
	  
	  public Calendar getTradeDate() { return tradeDate; }
	  
	  public void setTradeDate(Calendar tradeDate) { this.tradeDate = tradeDate; }
	  
	  public double getAmount1() { return amount1; }
	  
	  public void setAmount1(double amount1) { this.amount1 = amount1; }
	  
	  public double getAmount2() { return amount2; }
	  
	  public void setAmount2(double amount2) { this.amount2 = amount2; }
	  
	  public double getRate() { return rate; }
	  
	  public void setRate(double rate) { this.rate = rate; }
	  
	  public String getLegalEntity() { return legalEntity; }
	  
	  public void setLegalEntity(String legalEntity) { this.legalEntity =
	  legalEntity; }
	  
	  public String getTrader() { return trader; }
	  
	  public void setTrader(String trader) { this.trader = trader; }
	 
}
