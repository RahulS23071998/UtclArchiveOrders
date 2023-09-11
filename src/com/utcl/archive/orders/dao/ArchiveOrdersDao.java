/**
 *
 */
package com.utcl.archive.orders.dao;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;

/**
 * @author Rahul.S
 *
 */
public interface ArchiveOrdersDao extends Dao
{

	List<OrderModel> orderModelList();
}
