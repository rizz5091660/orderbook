package com.sonartrade.orderbook.impl;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import com.sonartrade.orderbook.api.Orderbook;

import akka.Done;

public interface OrderbookCommand extends Jsonable {
	@SuppressWarnings("serial")
	@Immutable
	@JsonDeserialize
	public final class CreateOrderbook implements OrderbookCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
		public final Orderbook orderbook;

		@JsonCreator
		public CreateOrderbook(Orderbook orderbook) {
			this.orderbook = Preconditions.checkNotNull(orderbook, "orderbook");
		}

		@Override
		public boolean equals(@Nullable Object another) {
			if (this == another)
				return true;
			return another instanceof CreateOrderbook && equalTo((CreateOrderbook) another);
		}

		private boolean equalTo(CreateOrderbook another) {
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
			return MoreObjects.toStringHelper("CreateOrderbook").add("orderbook", orderbook).toString();
		}
	}



	@SuppressWarnings("serial")
	@Immutable
	@JsonDeserialize
	public final class GetOrderbook implements OrderbookCommand,PersistentEntity.ReplyType<GetOrderbookReply> {

		@Override
		public boolean equals(@Nullable Object another) {
			return this instanceof GetOrderbook;
		}

		@Override
		public int hashCode() {
			return 2053226012;
		}

		@Override
		public String toString() {
			return "GetOrderbook{}";
		} 
	}

	@SuppressWarnings("serial")
	@Immutable
	@JsonDeserialize
	public final class GetOrderbookReply implements Jsonable {
		public final Optional<Orderbook> orderbook;

		@JsonCreator
		public GetOrderbookReply(Optional<Orderbook> orderbook) {
			this.orderbook = Preconditions.checkNotNull(orderbook, "orderbook");
		}

		@Override
		public boolean equals(@Nullable Object another) {
			if (this == another)
				return true;
			return another instanceof GetOrderbookReply && equalTo((GetOrderbookReply) another);
		}

		private boolean equalTo(GetOrderbookReply another) {
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
			return MoreObjects.toStringHelper("GetOrderbookReply").add("orderbook", orderbook).toString();
		}
	}
	
	@SuppressWarnings("serial")
	@Immutable
	@JsonDeserialize
	public final class UpdateOrderbook implements OrderbookCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
		public final Orderbook orderbook;

		@JsonCreator
		public UpdateOrderbook(Orderbook orderbook) {
			this.orderbook = Preconditions.checkNotNull(orderbook, "orderbook");
		}

		@Override
		public boolean equals(@Nullable Object another) {
			if (this == another)
				return true;
			return another instanceof UpdateOrderbook && equalTo((UpdateOrderbook) another);
		}

		private boolean equalTo(UpdateOrderbook another) {
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
			return MoreObjects.toStringHelper("UpdateOrderbook").add("orderbook", orderbook).toString();
		}
	}

}
