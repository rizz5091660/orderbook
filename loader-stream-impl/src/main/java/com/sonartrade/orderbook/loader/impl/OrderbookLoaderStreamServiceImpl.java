package com.sonartrade.orderbook.loader.impl;

import javax.inject.Inject;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.sonartrade.orderbook.api.Orderbook;
import com.sonartrade.orderbook.api.OrderbookService;
import com.sonartrade.orderbook.loader.api.OrderbookLoaderStreamService;

import akka.NotUsed;
import akka.stream.javadsl.Source;


public class OrderbookLoaderStreamServiceImpl implements OrderbookLoaderStreamService{

	private final OrderbookService orderbookService;
	
	@Inject
	  public OrderbookLoaderStreamServiceImpl(OrderbookService orderbookService) {
	    this.orderbookService = orderbookService;
	  }

	@Override
	public ServiceCall<Orderbook, NotUsed> stream() {
		 return create  -> orderbookService.createOrderbook().invoke();
	}


}
