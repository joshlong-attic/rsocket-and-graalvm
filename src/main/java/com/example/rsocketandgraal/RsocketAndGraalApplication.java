package com.example.rsocketandgraal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class RsocketAndGraalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsocketAndGraalApplication.class, args);
	}
}

// http
@RestController
class GreetingHttpController {

	@GetMapping("/hello")
	Greeting greeting() {
		return new Greeting("Hello, world");
	}
}

// rsocket
@Data
@AllArgsConstructor
@NoArgsConstructor
class Greeting {
	private String message;
}

@Controller
class GreetingsRSocketController {

	@MessageMapping("hello")
	Greeting greet() {
		return new Greeting("Hello, world! ");
	}

}