package com.meritkapital.rules;

import com.meritkapital.dao.DbAccess;
import com.meritkapital.products.AbstractTrade;

/**
 * Class implement following rule for ALL types of trades:
 * "validate currencies if they are valid ISO codes (ISO 4217) "
 * @author Zaleskovskiy Mikhail
 *
 */
public class AllCurrencyValidRule implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		String message = "";
		DbAccess db = DbAccess.getInstance();
		String currency1 = trade.getCcyPair().substring(0,3).toUpperCase();
		String currency2 = trade.getCcyPair().substring(3,6).toUpperCase();
		if(!db.getIso4217().contains(currency1) || !db.getIso4217().contains(currency2)) {
			message = "Currency is not correct according ISO 4217; ccyPair";
		}
		return message;
	}

}
