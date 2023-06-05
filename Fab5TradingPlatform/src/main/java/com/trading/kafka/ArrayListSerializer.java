package com.trading.kafka;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.kafka.common.serialization.Serializer;

public class ArrayListSerializer<MarkeQuote> implements Serializer<ArrayList<MarkeQuote>> {

    @Override
    public byte[] serialize(String topic, ArrayList<MarkeQuote> data) {
    	System.out.println("Ser" + data.size());
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (data != null ) {
            final int size = data.size();
            final DataOutputStream dos = new DataOutputStream(baos);
            final Iterator<MarkeQuote> iterator = data.iterator();
            try {
                dos.writeInt(size);
                while (iterator.hasNext()) {
                    final byte[] bytes = (new MarketQuoteSerializer()).serialize(topic, iterator.next());
                    dos.writeInt(bytes.length);
                    dos.write(bytes);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to serialize ArrayList", e);
            }
        }
        return baos.toByteArray();
    }


}
