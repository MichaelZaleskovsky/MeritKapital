package com.meritkapital.rules;

import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Spot;

/**
 * Class implement following rule for SPOT type of trades:
 * "value date cannot be before trade date "
 * @author Zaleskovskiy Mikhail
 *
 */
public class SpotDateBeforeTradeRule implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		Spot spot = (Spot) trade;
		String message = "";
		if (spot.getValueDate().before(spot.getTradeDate())) 
			message = "Value date can not be before trade date; valueDate; tradeDate";
		return message;
	}

}
