package br.com.cabal.store;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.cabal.store.controller.StoreController;
import br.com.cabal.core.repository.StoreRepository;
import br.com.cabal.store.service.StoreService;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreController.class)
public class StoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StoreService storeService;

	@MockBean
	private StoreRepository storeRepository;

	@Test
	public void getStore_ShouldReturnOneStore() throws Exception {

		Mockito.when(storeRepository.findAll()).thenReturn(Collections.emptyList());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/").accept(MediaType.APPLICATION_JSON))
				.andReturn();

		System.out.println(mvcResult.getResponse());
		Mockito.verify(storeRepository).findAll();
	}

}
