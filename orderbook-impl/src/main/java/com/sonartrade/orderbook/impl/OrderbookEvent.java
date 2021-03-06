package com.sonartrade.orderbook.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.serialization.Jsonable;


public interface OrderbookEvent extends Jsonable, AggregateEvent<OrderbookEvent> {

	@Override
	default public AggregateEventTag<OrderbookEvent> aggregateTag() {
		return OrderbookEventTag.INSTANCE;
	}

	@SuppressWarnings("serial")
	@Immutable
	@JsonDeserialize
	public class OrderbookCreated implements OrderbookEvent {
		public String ticker;
		public BigDecimal bid;
		public BigDecimal ask;
		public BigDecimal lastPrice;
		public BigDecimal low;
		public BigDecimal high;
		public BigDecimal volume;


		public final Instant timestamp;

		public OrderbookCreated(String ticker, BigDecimal bid, BigDecimal ask,BigDecimal lastPrice,BigDecimal low,BigDecimal high, BigDecimal volume){
			this(ticker, bid, ask, lastPrice, low, high, volume, Optional.empty());
		}

		@JsonCreator
		private OrderbookCreated(String ticker, BigDecimal bid, BigDecimal ask,BigDecimal lastPrice,BigDecimal low,BigDecimal high, BigDecimal volume,Optional<Instant> timestamp) {
			this.ticker = Preconditions.checkNotNull(ticker, "ticker");
			this.bid = bid;
			this.ask = ask; 
			this.lastPrice = lastPrice;
			this.low = low;
			this.high = high;
			this.volume = volume;
			this.timestamp = timestamp.orElseGet(() -> Instant.now());
		} 

		@Override
		public boolean equals(@Nullable Object another) {
			if (this == another)
				return true;
			return another instanceof OrderbookCreated && equalTo((OrderbookCreated) another);
		}

		private boolean equalTo(OrderbookCreated another) {
			return ticker.equals(another.ticker)  && timestamp.equals(another.timestamp);
		}

		@Override
		public int hashCode() {
			int h = 31;
			h = h * 17 + ticker.hashCode();
			h = h * 17 + timestamp.hashCode();
			return h;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper("OrderbookCreated").add("ticker", ticker).add("timestamp", timestamp).toString();
		}
	}


}
