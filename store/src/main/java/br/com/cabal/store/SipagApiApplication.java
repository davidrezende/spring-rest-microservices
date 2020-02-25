package br.com.cabal.store;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan({"br.com.cabal.core.model"})
@EnableJpaRepositories({"br.com.cabal.core.repository"})
public class SipagApiApplication {
// test
	public static void main(String[] args) {
		SpringApplication.run(SipagApiApplication.class, args);
	}
	/*
	 * @GetMapping("/hello") public String hello(@RequestParam(value = "name",
	 * defaultValue = "World") String name) { return String.format("Hello %s!",
	 * name); }
	 */

}
