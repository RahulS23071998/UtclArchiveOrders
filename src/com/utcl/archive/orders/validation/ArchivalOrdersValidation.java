/**
 *
 */
package com.utcl.archive.orders.validation;

import java.util.Calendar;
import java.util.Date;


/**
 * @author Rahul.S
 *
 */
public class ArchivalOrdersValidation
{
	public static void isOrderOlderThanSixMonths(final Date orderCreationDate) throws IllegalArgumentException
	{
		final Calendar sixMonthsAgo = Calendar.getInstance();
		sixMonthsAgo.add(Calendar.MONTH, -6);
		if (!orderCreationDate.before(sixMonthsAgo.getTime()))
		{
			throw new IllegalArgumentException("Order is less than 6 months old and cannot be processed.");
		}
	}
}
