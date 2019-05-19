package com.meritkapital.rules;

import java.util.List;

import com.meritkapital.products.AbstractTrade;

/**
 * Interface represent special rule to validate trade.
 * To add new rule create new class and implement this interface
 * @author Zaleskovskiy Mikhail
 *
 */
public interface Rule {
	String check(AbstractTrade trade);
}
