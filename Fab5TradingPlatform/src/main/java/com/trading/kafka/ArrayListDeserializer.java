package com.trading.kafka;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.kafka.common.serialization.Deserializer;

public class ArrayListDeserializer<MarketQuote> implements Deserializer<ArrayList<MarketQuote>> {

    @Override
    public ArrayList<MarketQuote> deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        final ArrayList<MarketQuote> arrayList = new ArrayList<>();
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(data));

        try {
            final int records = dataInputStream.readInt();
            for (int i = 0; i < records; i++) {
                final byte[] valueBytes = new byte[dataInputStream.readInt()];
                dataInputStream.read(valueBytes);
                MarketQuote quote = (MarketQuote) (new MarketQuoteDeserializer()).deserialize(topic, valueBytes);
                arrayList.add((MarketQuote) quote);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to deserialize ArrayList", e);
        }

        return arrayList;
    }

}
