package com.meritkapital.rules;

import java.util.Calendar;
import java.util.Set;

import com.meritkapital.MeritKapitalApplication;
import com.meritkapital.dao.DbAccess;
import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Spot;

/**
 * Class implement following rule for SPOT type of trades:
 * "validate the value date against the product type"
 * @author Zaleskovskiy Mikhail
 *
 */
public class SpotValueDatePeriodRule implements Rule {
	private Calendar calendar = Calendar.getInstance();

	@Override
	public String check(AbstractTrade trade) {
		Spot spot = (Spot) trade;
		String message = "";
		DbAccess db = DbAccess.getInstance();
		String currency = spot.getCcyPair().substring(0, 3).toUpperCase();
		Set<Calendar> holidays = db.getHolidays(currency);
		
		long current = spot.getTradeDate().getTimeInMillis();
		int shift = 0;
		Calendar newDate = Calendar.getInstance();
		
		while(shift < MeritKapitalApplication.SPOT_PERIOD) {
			current += MeritKapitalApplication.MS_PER_DAY;
			newDate.setTimeInMillis(current);
			if (!(newDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY  || 
					newDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY  || 
					holidays.contains(newDate))) {
				shift++;
			}
		}
		
		if(spot.getValueDate().after(newDate)) {
			message = "Value date can not be late the " + MeritKapitalApplication.SPOT_PERIOD + 
				" banking day from tradeDate; valueDate; tradeDate";
		}
		
		return message;
	}

}
