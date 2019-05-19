package com.meritkapital.rules;

import java.util.Date;
import java.util.Set;

import com.meritkapital.dao.DbAccess;
import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Forward;

/**
 * Class implement following rule for FORWARD type of trades:
 * "Value date can not be weekend or holiday"
 * @author Zaleskovskiy Mikhail
 *
 */
public class ForwardDateNotWeekend implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		Forward forward = (Forward) trade;
		String message = "";
		DbAccess db = new DbAccess();
		String currency = forward.getCcyPair().substring(0, 3).toUpperCase();
		Set<Date> holidays = db.getHolidays(currency);
		if (forward.getValueDate().getDay() == 0 || forward.getValueDate().getDay() == 6 || holidays.contains(forward.getValueDate()))
			message = "Value date can not be weekend or holiday; valueDate";
		return message;
	}

}
