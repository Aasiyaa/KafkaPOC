package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ProduceOrder {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	
	@PostMapping("/placeorder")
	public String placeOrder(@RequestBody Order order)
	{String jsonorder=null;
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			 jsonorder=objectMapper.writeValueAsString(order);
			 
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kafkaTemplate.send("ordertopic", jsonorder);
		return "SUCCESS";
	}
}
