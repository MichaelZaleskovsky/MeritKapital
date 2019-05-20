package com.meritkapital.products;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.meritkapital.rules.OptionAmericanStyleRule;
import com.meritkapital.rules.OptionExpiryAndPremiumDateRule;

/**
 * Implementation of specific OPTION trade
 * In order to add new rule to valid exactly this type of trade
 * add new element in List "rules" in constructor
 * @author Zaleskovskiy Mikhail
 *
 */
@JsonTypeName("VanillaOption")
public class Option extends AbstractTrade {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Calendar excerciseStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Calendar deliveryDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Calendar expiryDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Calendar premiumDate;
	private String style;
	private String strategy;
	private String payCcy;
	private Double premium;
	private String premiumCcy;
	private String premiumType;
	
	public Option() {
		super();
		rules.add(new OptionExpiryAndPremiumDateRule());
		rules.add(new OptionAmericanStyleRule());
	}

	public Calendar getExcerciseStartDate() {
		return excerciseStartDate;
	}

	public void setExcerciseStartDate(Calendar excerciseStartDate) {
		this.excerciseStartDate = excerciseStartDate;
	}

	public Calendar getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Calendar getPremiumDate() {
		return premiumDate;
	}

	public void setPremiumDate(Calendar premiumDate) {
		this.premiumDate = premiumDate;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getPayCcy() {
		return payCcy;
	}

	public void setPayCcy(String payCcy) {
		this.payCcy = payCcy;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public String getPremiumCcy() {
		return premiumCcy;
	}

	public void setPremiumCcy(String premiumCcy) {
		this.premiumCcy = premiumCcy;
	}

	public String getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}

}
