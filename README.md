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
2) Open `http://localhost:9000` in your browser to check list of services
<img src="https://github.com/rizz5091660/orderbook/blob/master/run-services.PNG" alt="Run Services"/>
<img src="https://github.com/rizz5091660/orderbook/blob/master/list-services.PNG" alt="List of Services"/>
3) Run java standalone appliction at `orderbook/orderbook-client-ws/target/orderbook-client-ws-1.0-jar-with-dependencies.jar`
  java -jar orderbook-client-ws-1.0-jar-with-dependencies.jar



4) Steps to build and start the UI, refer to :
https://github.com/rizz5091660/orderbook-ui/blob/master/README.md 

5) Orderbook application is up and running
<img src="https://github.com/rizz5091660/orderbook/blob/master/orderbook-ui.PNG" alt="Orderbook Apps"/>

