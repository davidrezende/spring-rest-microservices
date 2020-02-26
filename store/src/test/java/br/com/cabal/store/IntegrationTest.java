package br.com.cabal.store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cabal.core.model.Store;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Ignore
	public void getStoresAndReturnsStoreDetails() throws Exception {
		
		ResponseEntity<Store> response = restTemplate
				.getForEntity("v1/test", Store.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getName()).isEqualTo("marketTest");
		assertThat(response.getBody().getCnpj()).isEqualTo("99999999999999");
	}
}
