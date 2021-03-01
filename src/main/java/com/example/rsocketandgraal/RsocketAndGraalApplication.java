package com.example.rsocketandgraal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class RsocketAndGraalApplication {

	@Bean
	ApplicationRunner runner(
		DatabaseClient dbc,
		CustomerRepository customerRepository) {
		return args -> {

			var ddl = dbc.sql("create table customer(id serial primary key, name varchar (255) not null)").fetch().rowsUpdated();
			var names = Flux.just("A", "B", "C").map(name -> new Customer(null, name)).flatMap(customerRepository::save);

			ddl.thenMany(names).subscribe(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RsocketAndGraalApplication.class, args);
	}
}


interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Customer {

	@Id
	private Integer id;
	private String name;
}

@RestController
@RequiredArgsConstructor
class CustomerRestController {

	private final CustomerRepository customerRepository;

	@GetMapping("/customers")
	Flux<Customer> get() {
		return this.customerRepository.findAll();
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