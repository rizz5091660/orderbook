package com.sonartrade.orderbook.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import akka.NotUsed;

public interface OrderbookService extends Service {
	
	ServiceCall<NotUsed,String> allOrder(String id);
	final Logger log = LoggerFactory.getLogger(OrderbookService.class);
	
	@Override
	default Descriptor descriptor() {
	    return named("orderbook").withCalls(
			pathCall("/api/orderbook/:id",this::allOrder)	
		).withAutoAcl(true);
	}
	
}
