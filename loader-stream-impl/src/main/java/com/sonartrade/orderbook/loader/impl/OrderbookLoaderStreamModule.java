package com.sonartrade.orderbook.loader.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.sonartrade.orderbook.api.OrderbookService;
import com.sonartrade.orderbook.loader.api.OrderbookLoaderStreamService;

public class OrderbookLoaderStreamModule extends AbstractModule implements ServiceGuiceSupport{
	@Override
	  protected void configure() {
	    // Bind the StreamService service
	    bindServices(serviceBinding(OrderbookLoaderStreamService.class, OrderbookLoaderStreamServiceImpl.class));
	    // Bind the HelloService client
	    bindClient(OrderbookService.class);
	  }
}
