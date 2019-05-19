package com.meritkapital.rules;

import java.util.Date;
import java.util.Set;

import com.meritkapital.dao.DbAccess;
import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Spot;

/**
 * Class implement following rule for SPOT type of trades:
 * "Value date can not be weekend or holiday"
 * @author Zaleskovskiy Mikhail
 *
 */
public class SpotDateNotWeekend implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		Spot spot = (Spot) trade;
		String message = "";
		DbAccess db = new DbAccess();
		String currency = spot.getCcyPair().substring(0, 3).toUpperCase();
		Set<Date> holidays = db.getHolidays(currency);
		if (spot.getValueDate().getDay() == 0 || spot.getValueDate().getDay() == 6 || holidays.contains(spot.getValueDate()))
			message = "Value date can not be weekend or holiday; valueDate";
		return message;
	}

}
