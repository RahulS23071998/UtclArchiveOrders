package com.utcl.commerceservices.archive.orders.facade;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.Collection;
import java.util.List;


public interface ArchiveOrdersFacade {

	List<OrderModel> getOrderDetails(final Collection<String> orders);
}

