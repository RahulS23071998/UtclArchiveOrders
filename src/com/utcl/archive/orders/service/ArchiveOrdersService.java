/**
 *
 */
package com.utcl.archive.orders.service;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;


/**
 * @author Rahul.S
 *
 */
public interface ArchiveOrdersService
{
	List<OrderModel> getAllOrders();

}
