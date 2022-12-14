package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumeOrder {
	@KafkaListener(topics = "ordertopic", groupId = "consumer_id")
	public void consumerMessage(String order) {
		System.out.println("Hello"+order);
	}

}
