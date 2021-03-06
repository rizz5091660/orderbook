package com.sonartrade.orderbook.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.sonartrade.orderbook.api.Orderbook;
import com.sonartrade.orderbook.impl.OrderbookCommand.CreateOrderbook;
import com.sonartrade.orderbook.impl.OrderbookCommand.GetOrderbook;
import com.sonartrade.orderbook.impl.OrderbookCommand.GetOrderbookReply;
import com.sonartrade.orderbook.impl.OrderbookEvent.OrderbookCreated;

import akka.Done;

public class OrderbookEntity extends PersistentEntity<OrderbookCommand, OrderbookEvent, OrderbookState> {

	@Override
	public Behavior initialBehavior(Optional<OrderbookState> snapshotState) {

		BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(new OrderbookState(Optional.empty())));
		
		b.setCommandHandler(CreateOrderbook.class, (cmd, ctx) -> {
			Orderbook orderbook = cmd.orderbook;
			List<OrderbookEvent> events = new ArrayList<OrderbookEvent>();
			events.add(new OrderbookCreated(orderbook.ticker, orderbook.bid, orderbook.ask ,orderbook.lastPrice,orderbook.low,orderbook.high,orderbook.volume ));		
			return ctx.thenPersistAll(events, () -> ctx.reply(Done.getInstance()));
		});
		
		b.setEventHandler(OrderbookCreated.class,
				evt -> new OrderbookState(Optional.of(new Orderbook(evt.ticker, evt.bid, evt.ask, evt.lastPrice, evt.low, evt.high, evt.volume ))));
				 

		b.setReadOnlyCommandHandler(GetOrderbook.class, (cmd, ctx) -> {
			ctx.reply(new GetOrderbookReply(state().orderbook));
		});

		return b.build();
	}

}
