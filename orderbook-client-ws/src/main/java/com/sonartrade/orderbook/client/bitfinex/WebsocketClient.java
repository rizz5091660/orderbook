package com.sonartrade.orderbook.client.bitfinex;


import java.math.BigDecimal;
import java.net.URI;
import java.util.function.BiConsumer;

import org.json.JSONObject;

import com.github.jnidzwetzki.bitfinex.v2.BitfinexClientFactory;
import com.github.jnidzwetzki.bitfinex.v2.BitfinexWebsocketClient;
import com.github.jnidzwetzki.bitfinex.v2.BitfinexWebsocketConfiguration;
import com.github.jnidzwetzki.bitfinex.v2.entity.BitfinexTick;
import com.github.jnidzwetzki.bitfinex.v2.entity.currency.BitfinexCurrencyPair;
import com.github.jnidzwetzki.bitfinex.v2.manager.QuoteManager;
import com.github.jnidzwetzki.bitfinex.v2.symbol.BitfinexSymbols;
import com.github.jnidzwetzki.bitfinex.v2.symbol.BitfinexTickerSymbol;
import com.lightbend.lagom.javadsl.client.integration.LagomClientFactory;
import com.sonartrade.orderbook.api.Orderbook;
import com.sonartrade.orderbook.api.OrderbookService;
import com.sonartrade.orderbook.loader.api.OrderbookLoaderStreamService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;

public class WebsocketClient {

	private static final String SERVICE_LOCATOR_URI = "http://localhost:8000";
	private static LagomClientFactory clientFactory;
	private static OrderbookService orderbookService;
	private static OrderbookLoaderStreamService orderbookLoaderStreamService;
	private static ActorSystem system;
	private static Materializer mat;
	private static Client client;
	private static WebResource webResource;
	private static String symbol="BTC";


	 private static void initLagom() {
	        clientFactory = LagomClientFactory.create("Orderbook-Loader", WebsocketClient.class.getClassLoader());
	        orderbookService = clientFactory.createDevClient(OrderbookService.class, URI.create(SERVICE_LOCATOR_URI));
	        orderbookLoaderStreamService = clientFactory.createDevClient(OrderbookLoaderStreamService.class, URI.create(SERVICE_LOCATOR_URI));
	        system = ActorSystem.create();
	        mat = ActorMaterializer.create(system);
	    }
	 
	 private static void initRestWS() {
		 client = Client.create();
		 webResource = client.resource("http://localhost:9000/api/orderbook");
	 }
	 
	public static void synchronizeBitFinex() {
		final BitfinexWebsocketConfiguration config = new BitfinexWebsocketConfiguration();
		BitfinexCurrencyPair.registerDefaults();
		final BitfinexWebsocketClient bitfinexClient = BitfinexClientFactory.newSimpleClient(config);
		bitfinexClient.connect();		
		final BitfinexTickerSymbol symbol = BitfinexSymbols.ticker(BitfinexCurrencyPair.of("BTC","USD"));
		final QuoteManager quoteManager = bitfinexClient.getQuoteManager();		
		final BiConsumer<BitfinexTickerSymbol, BitfinexTick> callback = (orderbookConfig, entry) -> {			
			System.out.format("Got entry (%s) for orderbook (%s)\n", entry, orderbookConfig);
			sentRestWS(entry);
		};
		quoteManager.registerTickCallback(symbol, callback);
		quoteManager.subscribeTicker(symbol);		
	}
	
	
	private static void sentRestWS(BitfinexTick entry) {
		try {
		initRestWS();
		String request = "{\"ticker\":\""+ symbol+"\",\"bid\": "+ entry.getBid()+",\"ask\":"+ entry.getAsk()+",\"lastPrice\":"+ entry.getLastPrice()+",\"low\":"+ entry.getLow()+",\"high\":"+ entry.getLow()+",\"volume\":"+ entry.getVolume()+ "}";	
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, request); 
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	

	public static void main(String []args) {
		WebsocketClient.initRestWS();
		WebsocketClient.synchronizeBitFinex();
	}
}
