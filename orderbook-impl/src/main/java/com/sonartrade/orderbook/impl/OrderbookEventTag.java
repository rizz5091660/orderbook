package com.sonartrade.orderbook.impl;

import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;

public class OrderbookEventTag {
	 public static final AggregateEventTag<OrderbookEvent> INSTANCE = 
			    AggregateEventTag.of(OrderbookEvent.class);

}
