package com.meritkapital.rules;

import java.util.Calendar;
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
		DbAccess db = DbAccess.getInstance();
		String currency = spot.getCcyPair().substring(0, 3).toUpperCase();
		Set<Calendar> holidays = db.getHolidays(currency);
		if (spot.getValueDate().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY  || 
				spot.getValueDate().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY  || 
				holidays.contains(spot.getValueDate())) {
			message = "Value date can not be weekend or holiday; valueDate";
		}
		return message;
	}

}
