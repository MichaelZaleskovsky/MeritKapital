package com.meritkapital.products;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.meritkapital.rules.SpotDateBeforeTradeRule;
import com.meritkapital.rules.SpotDateNotWeekend;
import com.meritkapital.rules.SpotValueDatePeriodRule;

/**
 * Implementation of specific SPOT trade
 * In order to add new rule to valid exactly this type of trade
 * add new element in List "rules" in constructor
 * @author Zaleskovskiy Mikhail
 *
 */
@JsonTypeName("Spot")
public class Spot extends AbstractTrade {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date valueDate;

	public Spot() {
		super();
		rules.add(new SpotDateBeforeTradeRule());
		rules.add(new SpotDateNotWeekend());
		rules.add(new SpotValueDatePeriodRule());
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}


	@Override
	public String toString() {
		return "Spot [valueDate=" + valueDate + ", customer=" + customer + ", ccyPair=" + ccyPair + ", type=" + type
				+ ", direction=" + direction + ", tradeDate=" + tradeDate + ", amount1=" + amount1 + ", amount2="
				+ amount2 + ", rate=" + rate + ", legalEntity=" + legalEntity + ", trader=" + trader + "]";
	}	
}
