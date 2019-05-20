package com.meritkapital.products;

import java.util.Calendar;
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
	private Calendar valueDate;

	public Forward() {
		super();
		rules.add(new ForwardDateBeforeTradeRule());
		rules.add(new ForwardDateNotWeekend());
		rules.add(new ForwardValueDatePeriodRule());
	}

	public Calendar getValueDate() {
		return valueDate;
	}

	public void setValueDate(Calendar valueDate) {
		this.valueDate = valueDate;
	}

}
