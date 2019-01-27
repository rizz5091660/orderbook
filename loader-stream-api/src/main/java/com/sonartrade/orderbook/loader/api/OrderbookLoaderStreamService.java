package com.sonartrade.orderbook.loader.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.namedCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.sonartrade.orderbook.api.Orderbook;

import akka.NotUsed;
import akka.stream.javadsl.Source;


public interface OrderbookLoaderStreamService extends Service {	
	ServiceCall<Orderbook, NotUsed>  stream();
	  
	  @Override
	  default Descriptor descriptor() {
	    return named("stream").withCalls(namedCall("stream", this::stream))
	      .withAutoAcl(true);
	  }
}
