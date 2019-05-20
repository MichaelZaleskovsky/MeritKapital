package com.meritkapital.rules;

import com.meritkapital.products.AbstractTrade;
import com.meritkapital.products.Option;

/**
 * Class implement following rule for OPTION type of trades:
 * "Expiry date and premium date must be before delivery date"
 * @author Zaleskovskiy Mikhail
 *
 */
public class OptionExpiryAndPremiumDateRule implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		Option option = (Option) trade;
		String message = "";
		if(!option.getExpiryDate().before(option.getDeliveryDate()) || !option.getPremiumDate().before(option.getDeliveryDate())) {
			message = "Expiry date and premium date must be before delivery date; expiryDate; premiumDate; deliveryDate";
		}
		return message;
	}

}
