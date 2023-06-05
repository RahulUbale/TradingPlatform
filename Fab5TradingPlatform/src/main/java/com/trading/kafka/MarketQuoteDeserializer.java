package com.trading.kafka;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.bean.MarketQuote;

public class MarketQuoteDeserializer implements Deserializer {

	@Override
	public MarketQuote deserialize(String topic, byte[] data) {
	    ObjectMapper mapper = new ObjectMapper();
	    MarketQuote quote = null;
	    try {
	    	quote = mapper.readValue(data, MarketQuote.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return quote;
	  }


}
