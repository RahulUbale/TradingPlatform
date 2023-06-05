package com.trading.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.trading.bean.Equity;
import com.trading.bean.MarketDepth;
import com.trading.bean.MarketMovers;
import com.trading.bean.MarketQuote;
import com.trading.bean.Ohlc;
import com.trading.bean.OrderQuote;
import com.trading.kafka.KafkaProducerConfig;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Depth;
import com.zerodhatech.models.Instrument;
import com.zerodhatech.models.Quote;
import com.zerodhatech.models.Tick;
import com.zerodhatech.ticker.KiteTicker;
import com.zerodhatech.ticker.OnConnect;
import com.zerodhatech.ticker.OnTicks;

public class KiteConnectAPI {
	
	public static KiteConnect connection;
	public static HashMap<String, Equity> equityList = new HashMap<String, Equity>();

	 //@Autowired
	 private static KafkaTemplate<String, ArrayList<MarketQuote>> kafkaTemplate;
	 
	   /** Get quote for a scrip.*/
    public static List<MarketQuote> getQuote(List<String> tickers) {
        
    	// Get quotes returns quote for desired trading symbol.
    	List<MarketQuote> list = new ArrayList<MarketQuote>();
    	try {
    		int size = tickers.size();
    		String[] instruments = new String[size];
    		for(int i=0; i<size; i++) {
    			instruments[i]=tickers.get(i);
    		}
	        Map<String, Quote> quotes = connection.getQuote(instruments);
	        System.out.println("Got quotes");
	        Iterator<String> companies = quotes.keySet().iterator();
	        while(companies.hasNext()) {
	        	String company = companies.next();
	        	Quote quote = quotes.get(company);
	        	double diff = quote.lastPrice - quote.ohlc.close;
	        	double change = (diff/quote.ohlc.close) * 100;
	        	MarketQuote data = new MarketQuote(company, equityList.get(company).getName(), quote.lastPrice,diff,change);
	        	list.add(data);
	        }
    	}catch(KiteException e) {
    		System.out.println(e.getMessage());
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
        return list;
    }
    
    public static MarketMovers getMarketMovers() {
    	List<MarketQuote> topList = new ArrayList<MarketQuote>();
    	List<MarketQuote> bottomList = new ArrayList<MarketQuote>();
    	MarketMovers movers = new MarketMovers();
    	
    	try {
    		List<MarketQuote> list = new ArrayList<MarketQuote>();
     		String[] instruments = {"NSE:RELIANCE","NSE:HDFCBANK","NSE:INFY","NSE:HDFC","NSE:TCS","NSE:ICICIBANK","NSE:KOTAKBANK",
     				"NSE:HINDUNILVR","NSE:ITC","NSE:BHARTIARTL","NSE:LT","NSE:AXISBANK","NSE:BAJFINANCE","NSE:MARUTI",
     				"NSE:ASIANPAINT","NSE:HCLTECH","NSE:SBIN", "NSE:NESTLEIND","NSE:M&M","NSE:SUNPHARMA",
     				"NSE:ULTRACEMCO","NSE:TITAN","NSE:APTECHT","NSE:BAJAJ-AUTO"
     				};
            Map<String, Quote> quotes = connection.getQuote(instruments);
            Iterator<String> companies = quotes.keySet().iterator();
            while(companies.hasNext()) {
            	String company = companies.next();
            	Quote quote = quotes.get(company);
            	double diff = quote.lastPrice - quote.ohlc.close;
            	double change = (diff/quote.ohlc.close) * 100;
            	String companyName = equityList.get(company).getTicker();
            	
            		
            	MarketQuote data = new MarketQuote(company, companyName, quote.lastPrice,diff,change);
            	list.add(data);
            }
            Collections.sort(list);
            for(int i=0; i<5; i++) {
            	bottomList.add(list.get(i));
            }
            movers.setBottomFive(bottomList);
            
            Collections.sort(list,Collections.reverseOrder());
            for(int i=0; i<5; i++) {
            	topList.add(list.get(i));
            }
            movers.setTopFive(topList);
            
     	}catch(KiteException e) {
     		System.out.println(e.getMessage());
     	}catch(Exception e) {
     		System.out.println(e.getMessage());
     	}	
    	
    	return movers;
    }

    
	   /** Get quote for a scrip.*/
 public static List<MarketQuote> getExchangeData() {
     
 	// Get quotes returns quote for desired trading symbol.
 	List<MarketQuote> list = new ArrayList<MarketQuote>();
 	try {
 		String[] instruments = {"NSE:NIFTY 50","BSE:SENSEX"};
        Map<String, Quote> quotes = connection.getQuote(instruments);
        Iterator<String> companies = quotes.keySet().iterator();
        while(companies.hasNext()) {
        	String company = companies.next();
        	Quote quote = quotes.get(company);
        	double diff = quote.lastPrice - quote.ohlc.close;
        	double change = (diff/quote.ohlc.close) * 100;
        	String companyName;
        	if(company.equals("NSE:NIFTY 50"))
        		companyName = "NIFTY 50";
        	else
        		companyName = "SENSEX";
        		
        	MarketQuote data = new MarketQuote(company, companyName, quote.lastPrice,diff,change);
        	list.add(data);
        }
 	}catch(KiteException e) {
 		System.out.println(e.getMessage());
 	}catch(Exception e) {
 		System.out.println(e.getMessage());
 	}
	 KiteConnectAPI.setMarketData();
     return list;
 }
	public static void loadCompanyData() {
		try {
			List<Instrument> instruments = connection.getInstruments();
			int size = instruments.size();
			for(int i=0; i< size; i++) {
				Instrument instrument = instruments.get(i);
				if(instrument.instrument_type.equals("EQ")) {
					String uniqueTicker = instrument.exchange + ":" + instrument.tradingsymbol;
					equityList.put(uniqueTicker, new Equity(instrument.instrument_token, instrument.exchange_token, 
							instrument.tradingsymbol, instrument.name, instrument.exchange,instrument.instrument_type, uniqueTicker));
				}
				
			}
		} catch (JSONException e) {
			System.out.println("Json exception " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception " + e.getMessage());
			e.printStackTrace();
		} catch (KiteException e) {
			System.out.println("Kite exception " + e.getMessage());
			e.printStackTrace();
		}
	
	}	

	
	   /** Get quote for a scrip.*/
 public static OrderQuote getQuote(String ticker) {
	 OrderQuote orderQuote =  null;
	try{
		String[] instruments = new String[1];
		instruments[0] = ticker;
	    Map<String, Quote> quotes = connection.getQuote(instruments);
	    Iterator<String> companies = quotes.keySet().iterator();
	    if(companies.hasNext()) {
	    	String company = companies.next();
	    	
	    	Quote quote = quotes.get(company);
	    	Ohlc ohlc = new Ohlc(quote.ohlc.high, quote.ohlc.low, quote.ohlc.close, quote.ohlc.open);
	    	List<Depth> bidDepth = quote.depth.buy;
	    	int size = bidDepth.size();
	    	List<com.trading.bean.Depth> buyDepth = new ArrayList();
	    	for(int i=0; i<size; i++) {
	    		Depth d = bidDepth.get(i);
	    		com.trading.bean.Depth depth = new com.trading.bean.Depth(d.getQuantity(),d.getPrice(),d.getOrders());
	    		buyDepth.add(depth);
	    	}
	    	List<Depth> offerDepth = quote.depth.sell;
	    	size = offerDepth.size();
	    	List<com.trading.bean.Depth> sellDepth = new ArrayList();
	    	for(int i=0; i<size; i++) {
	    		Depth d = offerDepth.get(i);
	    		com.trading.bean.Depth depth = new com.trading.bean.Depth(d.getQuantity(),d.getPrice(),d.getOrders());
	    		sellDepth.add(depth);
	    	}
	    	MarketDepth marketDepth = new MarketDepth(buyDepth, sellDepth);
	    	orderQuote = new OrderQuote();
	    	orderQuote.setVolumeTradedToday(quote.volumeTradedToday);
	    	orderQuote.setLastTradedQuantity(quote.lastTradedQuantity);
	    	orderQuote.setLastTradedTime(quote.lastTradedTime);
	    	orderQuote.setChange(quote.lastPrice - quote.ohlc.close);
	    	orderQuote.setOi(quote.oi);
	    	orderQuote.setSellQuantity(quote.sellQuantity);
	    	orderQuote.setLastPrice(quote.lastPrice);
	    	orderQuote.setBuyQuantity(quote.buyQuantity);
	    	orderQuote.setOhlc(ohlc);
	    	orderQuote.setInstrumentToken(quote.instrumentToken);	
	    	orderQuote.setTimestamp(quote.timestamp);
	    	orderQuote.setAveragePrice(quote.averagePrice);
	    	orderQuote.setOiDayHigh(quote.oiDayHigh);
	    	orderQuote.setOiDayLow(quote.oiDayLow);
	    	orderQuote.setDepth(marketDepth);
	    	orderQuote.setLowerCircuitLimit(quote.lowerCircuitLimit);
	    	orderQuote.setUpperCircuitLimit(quote.upperCircuitLimit);
	    	orderQuote.setCompany(equityList.get(ticker));
	    }
 	}catch(KiteException e) {
 		System.out.println(e.getMessage());
 	}catch(Exception e) {
 		System.out.println(e.getMessage());
 	}
     return orderQuote;
}

 
 public static void setMarketData(){

     try {
    	 kafkaTemplate = new KafkaProducerConfig().kafkaTemplate();

	     final KiteTicker tickerProvider = new KiteTicker(connection.getAccessToken(), connection.getApiKey());
	
	     tickerProvider.setOnConnectedListener(new OnConnect() {
	         @Override
	         public void onConnected() {
	             /** Subscribe ticks for token.
	              * By default, all tokens are subscribed for modeQuote.
	              * */
	        	 ArrayList<Long> tokens = new ArrayList<Long>();
	        	 tokens.add(new Long(256265));
	        	 tokens.add(new Long(265));
	             tickerProvider.subscribe(tokens);
	             
	             tickerProvider.setMode(tokens, KiteTicker.modeQuote);
	         }
	     });
	
	
	     tickerProvider.setOnTickerArrivalListener(new OnTicks() {
	         @Override
	         public void onTicks(ArrayList<Tick> ticks) {
	             System.out.println("ticks size "+ticks.size());
	             int size = ticks.size();
	             ArrayList<MarketQuote> list = new ArrayList<MarketQuote>();
	             for(int i=0; i<size; i++) {
	            	 Tick tick = ticks.get(i);
	            	 String ticker = "ticker";
	            	 String company = "company";
	            	 if(tick.getInstrumentToken()==256265) {
	            		 ticker = "NSE:NIFTY 50";
	            		 company = "NIFTY 50";
	            				 
	            	 }
	            	 if(tick.getInstrumentToken()==265) {
	            		 ticker = "BSE:SENSEX";
	            		 company = "SENSEX";
	            				 
	            	 }
	            	 double price = tick.getLastTradedPrice();
	            	 double change = price - tick.getClosePrice();
	            	 double percentChange = (change/tick.getClosePrice())  * 100;
	            	 MarketQuote quote = new MarketQuote(ticker,company,price,change,percentChange);
	            	 if(tick.getInstrumentToken()==256265) {
	            		 list.add(0,quote);
	            				 
	            	 }
	            	 if(tick.getInstrumentToken()==265) {
	            		 list.add(1,quote);
	            				 
	            	 }
	             }
	             
	             if(size == 2) {
	             ListenableFuture<SendResult<String, ArrayList<MarketQuote>>> future = 
	            		 kafkaTemplate.send("market",list);
	            	    future.addCallback(new ListenableFutureCallback<SendResult<String, ArrayList<MarketQuote>>>() {
	            	    	
	            	        @Override
	            	        public void onSuccess(SendResult<String, ArrayList<MarketQuote>> result) {
	            	            System.out.println("Sent message=[" + list + 
	            	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	            	        }
	            	        @Override
	            	        public void onFailure(Throwable ex) {
	            	            System.out.println("Unable to send message=[" 
	            	              + list + "] due to : " + ex.getMessage());
	            	        }
	            	        
	            	        
	            	    });
	             }
	         }
	     });
	     tickerProvider.setTryReconnection(true);
	     tickerProvider.setMaximumRetries(10);
	     tickerProvider.setMaximumRetryInterval(30);
	     tickerProvider.connect();
	
	     boolean isConnected = tickerProvider.isConnectionOpen();
	     System.out.println(isConnected);

  	} catch (KiteException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}
     catch (Exception e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}

 } 
 
}
