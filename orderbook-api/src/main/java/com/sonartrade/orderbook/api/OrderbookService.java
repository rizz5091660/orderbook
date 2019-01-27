package com.sonartrade.orderbook.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import akka.NotUsed;

public interface OrderbookService extends Service {	
	ServiceCall<NotUsed, Orderbook> getOrderbook(String ticker);
	
	ServiceCall<Orderbook, NotUsed> createOrderbook();
	
	@Override
	default Descriptor descriptor() { 
	    return named("orderbook").withCalls(
			pathCall("/api/orderbook/:id",this::getOrderbook),
			pathCall("/api/orderbook",this::createOrderbook)
		).withAutoAcl(true);
	}
	
}
