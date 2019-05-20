package com.meritkapital.rules;

import com.meritkapital.MeritKapitalApplication;
import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Option;

/**
 * Class implement following rule for OPTION type of trades:
 * "For AMERICAN trades Excercise Start Date must be after the trade date but before the expiry date"
 * @author Zaleskovskiy Mikhail
 *
 */
public class OptionAmericanStyleRule implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		Option option = (Option) trade;
		String message = "";
		if(option.getStyle().equalsIgnoreCase(MeritKapitalApplication.AMERICAN)) {
			if(!option.getExcerciseStartDate().after(option.getTradeDate()) || 
					!option.getExcerciseStartDate().before(option.getExpiryDate())) {
				message = "Excercise Start Date must be after the trade date but before the expiry date; "
						+ "excerciseStartDate; tradeDate; expiryDate";
			}
		}
		return message;
	}

}
