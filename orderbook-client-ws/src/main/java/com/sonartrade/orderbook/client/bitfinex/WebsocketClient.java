package com.sonartrade.orderbook.client.bitfinex;

import com.github.jnidzwetzki.bitfinex.v2.BitfinexClientFactory;
import com.github.jnidzwetzki.bitfinex.v2.BitfinexWebsocketClient;
import com.github.jnidzwetzki.bitfinex.v2.BitfinexWebsocketConfiguration;
import com.github.jnidzwetzki.bitfinex.v2.command.SubscribeTickerCommand;
import com.github.jnidzwetzki.bitfinex.v2.entity.currency.BitfinexCurrencyPair;
import com.github.jnidzwetzki.bitfinex.v2.symbol.BitfinexSymbols;
import com.github.jnidzwetzki.bitfinex.v2.symbol.BitfinexTickerSymbol;

public class WebsocketClient {
	public static void main(String []args) {

		// For public and private operations (executing orders, read wallets)
		final BitfinexWebsocketConfiguration config = new BitfinexWebsocketConfiguration();
		BitfinexCurrencyPair.registerDefaults();
		final BitfinexWebsocketClient bitfinexClient = BitfinexClientFactory.newSimpleClient(config);
		bitfinexClient.connect();

		
		final BitfinexTickerSymbol symbol = BitfinexSymbols.ticker(BitfinexCurrencyPair.of("BTC","USD"));		
		final SubscribeTickerCommand command = new SubscribeTickerCommand(symbol);
		bitfinexClient.sendCommand(command);
		
	}
}
