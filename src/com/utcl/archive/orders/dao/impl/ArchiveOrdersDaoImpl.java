/**
 *
 */
package com.utcl.archive.orders.dao.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.utcl.archive.orders.dao.ArchiveOrdersDao;


/**
 * @author Rahul.S
 *
 */
public class ArchiveOrdersDaoImpl extends AbstractItemDao implements ArchiveOrdersDao
{

	private static final Logger LOGGER = Logger.getLogger(ArchiveOrdersDaoImpl.class);
	final String FIND_ALL_ORDERS = "SELECT{" + OrderModel.PK + "}FROM{" + OrderModel._TYPECODE + "}";


	@Override
	public List<OrderModel> orderModelList()
	{
		LOGGER.info("inside dao impl");
		LOGGER.info(getFlexibleSearchService().search(FIND_ALL_ORDERS).getClass());

		final SearchResult<OrderModel> orders = getFlexibleSearchService().search(FIND_ALL_ORDERS);
		return orders.getResult() == null ? Collections.emptyList() : orders.getResult();

	}
}
