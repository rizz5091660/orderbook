package com.sonartrade.orderbook.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.sonartrade.orderbook.api.OrderbookService;

import akka.NotUsed;

public class OrderbookServiceImpl implements OrderbookService{
	
	private final PersistentEntityRegistry persistentEntityRegistry;
	private final Logger log = LoggerFactory.getLogger(OrderbookServiceImpl.class);

	@Inject
	  public OrderbookServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
	    this.persistentEntityRegistry = persistentEntityRegistry;
	   // persistentEntityRegistry.register(HelloEntity.class);
	  }
	
	@Override
	public ServiceCall<NotUsed,String> allOrder(String id) {
		System.out.println("supppp");
		log.info("Yooooooo");
		  return request -> {
			 log.info("Yooooooo");
			 System.out.print("suppppp");			 
			return null;			  
		  };
	}

}
