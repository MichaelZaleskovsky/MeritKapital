package com.meritkapital.rules;

import com.meritkapital.dao.DbAccess;
import com.meritkapital.products.AbstractTrade;

/**
 * Class implement following rule for ALL types of trades:
 * "if the counterparty is one of the supported ones"
 * @author Zaleskovskiy Mikhail
 *
 */
public class AllValidCustomerRule implements Rule {

	@Override
	public String check(AbstractTrade trade) {
		String message = "";
		DbAccess db = DbAccess.getInstance();
		if(!db.getCustomers().contains(trade.getCustomer())) {
			message = "Customer is not valid; customer";
		}
		return message;
	}

}
