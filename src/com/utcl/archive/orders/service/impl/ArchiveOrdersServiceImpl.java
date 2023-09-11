/**
 *
 */
package com.utcl.archive.orders.service.impl;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.utcl.archive.orders.dao.ArchiveOrdersDao;
import com.utcl.archive.orders.service.ArchiveOrdersService;

/**
 * @author Rahul.S
 *
 */
public class ArchiveOrdersServiceImpl implements ArchiveOrdersService
{
	@Autowired
	private ArchiveOrdersDao archiveOrdersDao;


	@Override
	public List<OrderModel> getAllOrders()
	{
		final List<OrderModel> ordersList = archiveOrdersDao.orderModelList();
		return ordersList.stream().collect(Collectors.toList());
	}


}