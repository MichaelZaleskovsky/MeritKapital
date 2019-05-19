package com.meritkapital.products;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.meritkapital.rules.ForwardDateBeforeTradeRule;
import com.meritkapital.rules.ForwardDateNotWeekend;
import com.meritkapital.rules.ForwardValueDatePeriodRule;

/**
 * Implementation of specific FORWARD trade
 * In order to add new rule to valid exactly this type of trade
 * add new element in List "rules" in constructor
 * @author Zaleskovskiy Mikhail
 *
 */
@JsonTypeName("Forward")
public class Forward extends AbstractTrade {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date valueDate;

	public Forward() {
		super();
		rules.add(new ForwardDateBeforeTradeRule());
		rules.add(new ForwardDateNotWeekend());
		rules.add(new ForwardValueDatePeriodRule());
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	@Override
	public String toString() {
		return "Forward [valueDate=" + valueDate + ", customer=" + customer + ", ccyPair=" + ccyPair + ", type=" + type
				+ ", direction=" + direction + ", tradeDate=" + tradeDate + ", amount1=" + amount1 + ", amount2="
				+ amount2 + ", rate=" + rate + ", legalEntity=" + legalEntity + ", trader=" + trader + "]";
	}
}
