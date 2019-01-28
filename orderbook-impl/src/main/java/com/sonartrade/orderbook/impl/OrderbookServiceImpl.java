package com.sonartrade.orderbook.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.lightbend.lagom.javadsl.persistence.ReadSide;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;
import com.sonartrade.orderbook.api.Orderbook;
import com.sonartrade.orderbook.api.OrderbookService;
import com.sonartrade.orderbook.impl.OrderbookCommand.CreateOrderbook;
import com.sonartrade.orderbook.impl.OrderbookCommand.GetOrderbook;

import akka.NotUsed;

public class OrderbookServiceImpl implements OrderbookService{

	private final PersistentEntityRegistry persistentEntities;
	private final CassandraSession db;
	private final Logger log = LoggerFactory.getLogger(OrderbookServiceImpl.class); 

	  @Inject
	  public OrderbookServiceImpl(PersistentEntityRegistry persistentEntities, ReadSide readSide,
	      CassandraSession db) {
	    this.persistentEntities = persistentEntities;
	    this.db = db;
	    persistentEntities.register(OrderbookEntity.class);
	  }
	  
	

	@Override 
	public ServiceCall<NotUsed, Orderbook> getOrderbook(String ticker) { 
	    return request -> {
	        return orderbookEntityRef(ticker).ask(new GetOrderbook()).thenApply(reply -> {
	          if (reply.orderbook.isPresent())
	            return reply.orderbook.get();
	          else
	            throw new NotFound("orderbook " + ticker + " not found");
	        });
	      };
	}
	
	 private PersistentEntityRef<OrderbookCommand> orderbookEntityRef(String ticker) {
		    PersistentEntityRef<OrderbookCommand> ref = persistentEntities.refFor(OrderbookEntity.class, ticker);
		    return ref;
	 }



	@Override
	public ServiceCall<Orderbook, NotUsed> createOrderbook() {
		return request -> {
		      return orderbookEntityRef(request.ticker).ask(new CreateOrderbook(request))
		          .thenApply(ack -> NotUsed.getInstance());
		    };
	}

}
