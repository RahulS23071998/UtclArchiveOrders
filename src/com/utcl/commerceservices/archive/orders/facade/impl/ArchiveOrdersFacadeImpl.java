package com.utcl.commerceservices.archive.orders.facade.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.utcl.archive.orders.service.ArchiveOrdersService;
import com.utcl.commerceservices.archive.orders.facade.ArchiveOrdersFacade;
import com.utcl.core.model.ArchiveOrdersModel;
import com.utcl.facade.integration.data.ArchivedOrderData;


public class ArchiveOrdersFacadeImpl implements ArchiveOrdersFacade
{

	private ArchiveOrdersService archiveOrdersService;
	private Converter<OrderModel, ArchiveOrdersModel> orderConverter;
	private Converter<ArchiveOrdersModel, ArchivedOrderData> archiveOrderConverter;
	private ModelService modelService;
	private OrderService orderService;


	BiFunction<List<OrderModel>, Map<String, Date>, List<OrderModel>> extractCodeAndCreationTime = (orders,
			codeAndCreationTimeMap) -> {
		for (final OrderModel order : orders)
		{
			if (order != null)
			{
				final String code = order.getCode();
				final Date creationDate = order.getCreationtime();

				if (code != null && creationDate != null)
				{
					codeAndCreationTimeMap.put(code, creationDate);
	            }
	        }
	    }

		return orders;
	};


	@Override
	public List<OrderModel> getOrderDetails(final Collection<OrderModel> orderNumbers)
	{
		if (orderNumbers == null || orderNumbers.isEmpty()) {
			return Collections.emptyList();
	    }

	    final Map<String, Date> codeAndCreationTimeMap = new HashMap<>();
	    extractCodeAndCreationTime.apply(new ArrayList<>(orderNumbers), codeAndCreationTimeMap);

	    final List<OrderModel> resultOrders = codeAndCreationTimeMap.entrySet().stream()
				 .map(entry -> {
					 final OrderModel orderModel = new OrderModel();
					 orderModel.setCode(entry.getKey());
					 orderModel.setCreationtime(entry.getValue());
					 return orderModel;
				 }).collect(Collectors.toList());

		 return resultOrders;

	}

	@Override
	public List<ArchivedOrderData> getOrderDetails()
	{
		// TODO Auto-generated method stub
		final List<OrderModel> orderModels = getArchiveOrdersService().getAllOrders();

		final List<ArchivedOrderData> archivedOrderDataList = orderModels.stream().map(orderModel -> {
			final ArchiveOrdersModel archiveOrderModel = getModelService().create(ArchiveOrdersModel.class);
			getOrderConverter().convert(orderModel, archiveOrderModel);

			final ArchivedOrderData archivedOrderData = new ArchivedOrderData();
			getArchiveOrderConverter().convert(archiveOrderModel, archivedOrderData);

			getModelService().save(archiveOrderModel);
			//   modelService.remove(orderModel);

			return archivedOrderData;
		}).collect(Collectors.toList());



		return archivedOrderDataList;
	}

	public ArchiveOrdersService getArchiveOrdersService()
	{
		return archiveOrdersService;
	}

	public void setArchiveOrdersService(final ArchiveOrdersService archiveOrdersService)
	{
		this.archiveOrdersService = archiveOrdersService;
	}

	public Converter<OrderModel, ArchiveOrdersModel> getOrderConverter()
	{
		return orderConverter;
	}

	public void setOrderConverter(final Converter<OrderModel, ArchiveOrdersModel> orderConverter)
	{
		this.orderConverter = orderConverter;
	}

	public Converter<ArchiveOrdersModel, ArchivedOrderData> getArchiveOrderConverter()
	{
		return archiveOrderConverter;
	}

	public void setArchiveOrderConverter(final Converter<ArchiveOrdersModel, ArchivedOrderData> archiveOrderConverter)
	{
		this.archiveOrderConverter = archiveOrderConverter;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}





}
