package com.trading.kafka;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ArrayListSerde<MarkeQuote> implements Serde<ArrayList<MarkeQuote>> {

    private final Serializer  <MarkeQuote> innerSerialiser;
    private final Deserializer<MarkeQuote> innerDeserialiser;

    public ArrayListSerde(Serde<MarkeQuote> inner) {
        innerSerialiser   = inner.serializer ();
        innerDeserialiser = inner.deserializer();
    }
    
    public ArrayListSerde() {
		innerSerialiser = new MarketQuoteSerializer();;
		innerDeserialiser = new MarketQuoteDeserializer();
	}
    
    @Override
    public Serializer<ArrayList<MarkeQuote>> serializer() {
        return new Serializer<ArrayList<MarkeQuote>>() {
            @Override
            public byte[] serialize(String topic, ArrayList<MarkeQuote> data) {
            	System.out.println("Ser1" + data.size());
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                if (data != null ) {
                    final int size = data.size();
                    final DataOutputStream dos = new DataOutputStream(baos);
                    final Iterator<MarkeQuote> iterator = data.iterator();
                    try {
                        dos.writeInt(size);
                        while (iterator.hasNext()) {
                            final byte[] bytes = innerSerialiser.serialize(topic, iterator.next());
                            dos.writeInt(bytes.length);
                            dos.write(bytes);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Unable to serialize ArrayList", e);
                    }
                }
                return baos.toByteArray();
            }
        };
    }

    @Override
    public Deserializer<ArrayList<MarkeQuote>> deserializer() {
        return new Deserializer<ArrayList<MarkeQuote>>() {
            @Override
            public ArrayList<MarkeQuote> deserialize(String topic, byte[] data) {
                if (data == null || data.length == 0) {
                    return null;
                }

                final ArrayList<MarkeQuote> arrayList = new ArrayList<>();
                final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(data));

                try {
                    final int records = dataInputStream.readInt();
                    for (int i = 0; i < records; i++) {
                        final byte[] valueBytes = new byte[dataInputStream.readInt()];
                        dataInputStream.read(valueBytes);
                        arrayList.add(innerDeserialiser.deserialize(topic, valueBytes));
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Unable to deserialize ArrayList", e);
                }

                return arrayList;
            }
        };
    }
}