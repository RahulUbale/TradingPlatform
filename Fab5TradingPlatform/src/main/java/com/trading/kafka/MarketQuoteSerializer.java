package com.trading.kafka;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.bean.MarketQuote;

public class MarketQuoteSerializer implements Serializer {


	  @Override public void close() {

	  }

	@Override
	public byte[] serialize(String topic, Object data) {
		MarketQuote arg =(MarketQuote)data;
	    byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(arg).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}
}
