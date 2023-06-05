package com.trading.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.trading.bean.MarketQuote;

@Configuration
@EnableKafka
public class KafkaProducerConfig {
	private String bootstrapAddress = "localhost:9092";
	
	@Bean
	public ProducerFactory<String, ArrayList<MarketQuote>> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(
		ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
		  bootstrapAddress);
		configProps.put(
		ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
		  StringSerializer.class);
		configProps.put(
		ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
		  ArrayListSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, ArrayList<MarketQuote>> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	
}
