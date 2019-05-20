package com.meritkapital.products;

import java.util.Calendar;

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
	private Calendar valueDate;

	public Spot() {
		super();
		rules.add(new SpotDateBeforeTradeRule());
		rules.add(new SpotDateNotWeekend());
		rules.add(new SpotValueDatePeriodRule());
	}

	public Calendar getValueDate() {
		return valueDate;
	}

	public void setValueDate(Calendar valueDate) {
		this.valueDate = valueDate;
	}
}
