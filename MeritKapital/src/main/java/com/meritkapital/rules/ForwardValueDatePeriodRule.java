package com.meritkapital.rules;

import java.util.Date;
import java.util.Set;

import com.meritkapital.MeritKapitalApplication;
import com.meritkapital.dao.DbAccess;
import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Forward;

/**
 * Class implement following rule for FORWARD type of trades:
 * "validate the value date against the product type"
 * @author Zaleskovskiy Mikhail
 *
 */
public class ForwardValueDatePeriodRule implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		Forward forward = (Forward) trade;
		String message = "";
		DbAccess db = new DbAccess();
		String currency = forward.getCcyPair().substring(0, 3).toUpperCase();
		Set<Date> holidays = db.getHolidays(currency);
		
		long current = forward.getTradeDate().getTime();
		int shift = 0;
		Date newDate = new Date(current);
		
		while(shift < MeritKapitalApplication.FORWARD_PERIOD) {
			current += MeritKapitalApplication.MS_PER_DAY;
			newDate = new Date(current);
			if (!(newDate.getDay() == 0 || newDate.getDay() == 6 || holidays.contains(newDate))) shift++;
		}
		
		if(forward.getValueDate().after(newDate))
			message = "Value date can not be late the "+MeritKapitalApplication.FORWARD_PERIOD+" banking day from tradeDate; valueDate; tradeDate";
		
		return message;
	}

}
