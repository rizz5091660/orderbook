package com.sonartrade.orderbook.api;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

@Immutable
@JsonDeserialize
public class Orderbook {
	public String ticker;
	public double mid;
	public double bid;
	public double ask;
	public double lastPrice;
	public double low;
	public double high;
	public double volume;
	public String timestamp;
	

	public Orderbook(String ticker, double mid, double bid, double ask, double lastPrice, double low, double high, double volume,
			String timestamp) {
		super();
		this.ticker = ticker;
		this.mid = mid;
		this.bid = bid;
		this.ask = ask;
		this.lastPrice = lastPrice;
		this.low = low;
		this.high = high;
		this.volume = volume;
		this.timestamp = timestamp;
	}
	
	
	
	@JsonCreator

	public Orderbook(String ticker, double bid, double ask) {
		super();
		 this.ticker = Preconditions.checkNotNull(ticker, "ticker");
		this.bid = bid;
		this.ask = ask;
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

	
	
}
