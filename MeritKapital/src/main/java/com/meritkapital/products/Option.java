package com.meritkapital.products;

import java.util.Date;

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
	Date excerciseStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date deliveryDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date expiryDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date premiumDate;
	String style;
	String strategy;
	String payCcy;
	Double premium;
	String premiumCcy;
	String premiumType;
	
	public Option() {
		super();
		rules.add(new OptionExpiryAndPremiumDateRule());
		rules.add(new OptionAmericanStyleRule());
	}

	@Override
	public String toString() {
		return "Option [excerciseStartDate=" + excerciseStartDate + ", deliveryDate=" + deliveryDate + ", expiryDate="
				+ expiryDate + ", premiumDate=" + premiumDate + ", style=" + style + ", strategy=" + strategy
				+ ", payCcy=" + payCcy + ", premium=" + premium + ", premiumCcy=" + premiumCcy + ", premiumType="
				+ premiumType + ", customer=" + customer + ", ccyPair=" + ccyPair + ", type=" + type + ", direction="
				+ direction + ", tradeDate=" + tradeDate + ", amount1=" + amount1 + ", amount2=" + amount2 + ", rate="
				+ rate + ", legalEntity=" + legalEntity + ", trader=" + trader + "]";
	}

	public Date getExcerciseStartDate() {
		return excerciseStartDate;
	}

	public void setExcerciseStartDate(Date excerciseStartDate) {
		this.excerciseStartDate = excerciseStartDate;
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

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public Date getPremiumDate() {
		return premiumDate;
	}

	public void setPremiumDate(Date premiumDate) {
		this.premiumDate = premiumDate;
	}
	
	
}
