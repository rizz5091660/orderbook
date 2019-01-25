package com.sonartrade.orderbook.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.sonartrade.orderbook.api.OrderbookService;



public class OrderbookModule extends AbstractModule implements ServiceGuiceSupport {
	@Override
	  protected void configure() {
	    bindServices(serviceBinding(OrderbookService.class, OrderbookServiceImpl.class));
	  }
}
