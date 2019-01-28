package com.sonartrade.orderbook.api;

import java.math.BigDecimal;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

@Immutable
@JsonDeserialize
public class Orderbook {
	public String ticker;
	public BigDecimal bid;
	public BigDecimal ask;
	public BigDecimal lastPrice;
	public BigDecimal low;
	public BigDecimal high;
	public BigDecimal volume;
	public String timestamp;
	
	
	@JsonCreator
	public Orderbook(String ticker, BigDecimal bid, BigDecimal ask,BigDecimal lastPrice,BigDecimal low,BigDecimal high, BigDecimal volume) {
		this.ticker = Preconditions.checkNotNull(ticker, "ticker");
		this.bid = bid;
		this.ask = ask;
		this.lastPrice = lastPrice;
		this.low = low;
		this.high = high;
		this.volume = volume;
	}
	
	
	  @Override
	  public boolean equals(@Nullable Object another) {
	    if (this == another)
	      return true;
	    return another instanceof Orderbook && equalTo((Orderbook) another);
	  }

	  private boolean equalTo(Orderbook another) {
	    return ticker.equals(another.ticker);
	  }
	  
	  @Override
	  public int hashCode() {
	    int h = 31;
	    h = h * 17 + ticker.hashCode();
	    return h;
	  }

	  @Override
	  public String toString() {
		 return MoreObjects.toStringHelper("Orderbook").add("ticker", ticker).toString();
	}
	
	
}
