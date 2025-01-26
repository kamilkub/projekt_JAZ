package com.example.eventsservice;

import com.example.eventsservice.client.TicketmasterClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = "com.example")
@RequiredArgsConstructor
public class EventsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsServiceApplication.class, args);
	}


	private final TicketmasterClient ticketmasterClient;

	@EventListener(ApplicationReadyEvent.class)
	public void importEventsDataOntoTheDatabase() {
		this.ticketmasterClient.fetchAndSaveEvents();
	}
}
