package com.sonartrade.orderbook.impl;


import java.util.Optional;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.pcollections.PSequence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.Jsonable;
import com.sonartrade.orderbook.api.Orderbook;

/**
 * The state for the {@link Hello} entity.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize

public final class OrderbookState implements Jsonable {

		  public final Optional<Orderbook> orderbook;

		  @JsonCreator
		  public OrderbookState(Optional<Orderbook> orderbook) {
		    this.orderbook = Preconditions.checkNotNull(orderbook, "orderbook");
		  }


		  @Override
		  public boolean equals(@Nullable Object another) {
		    if (this == another)
		      return true;
		    return another instanceof OrderbookState && equalTo((OrderbookState) another);
		  }

		  private boolean equalTo(OrderbookState another) {
		    return orderbook.equals(another.orderbook);
		  }

		  @Override
		  public int hashCode() {
		    int h = 31;
		    h = h * 17 + orderbook.hashCode();
		    return h;
		  }

		  @Override
		  public String toString() {
		    return MoreObjects.toStringHelper("OrderbookState").add("orderbook", orderbook).toString();
		  }
}
