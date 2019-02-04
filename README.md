# orderbook

It is a java lagom application that mirroring Bitfinex Cryptocurrency Trading Platform.
Current version only showing BTC convert to USD. 

<img src="https://github.com/rizz5091660/orderbook/blob/master/lagom-bitfinex.png" alt="Lagom-Bitfinex"/>

## Build application
1) Running maven comman "clean install" at `orderbook/pom.xml` 

<img src="https://github.com/rizz5091660/orderbook/blob/master/build.PNG" alt="Build step 1"/>

<img src="https://github.com/rizz5091660/orderbook/blob/master/build2.PNG" alt="Build step 1"/>

## Running locally

1) Start all services using `mvn lagom:runAll` at `orderbook/pom.xml` 
2) Open `http://localhost:9000` in your browser.
3) Running java standalone application at 
`orderbook/orderbook-client-ws/src/main/java/com/sonartrade/orderbook/client/bitfinex/WebsocketClient.java`
4) Start the UI using `npm start`

