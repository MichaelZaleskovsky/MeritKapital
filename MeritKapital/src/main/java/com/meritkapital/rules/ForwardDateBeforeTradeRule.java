package com.meritkapital.rules;

import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Forward;

/**
 * Class implement following rule for FORWARD type of trades:
 * "value date cannot be before trade date "
 * @author Zaleskovskiy Mikhail
 *
 */
public class ForwardDateBeforeTradeRule implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		Forward forward = (Forward) trade;
		String message = "";
		if (forward.getValueDate().before(forward.getTradeDate())) 
			message = "Value date can not be before trade date; valueDate; tradeDate";
		return message;
	}

}
