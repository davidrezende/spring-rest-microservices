package br.com.cabal.store.controller;

import java.util.List;

import br.com.cabal.core.model.Store;
import br.com.cabal.core.repository.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import br.com.cabal.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Store endpoints")
public class StoreController {
	private final StoreRepository storeRepository;
	private final StoreService storeService;
	
	@GetMapping("/store/listByCNPJ/{cnpj}")
	private Store getStores(@PathVariable Long cnpj) {
		return null;
	}

	@GetMapping("/testeapi")
	private String test() {
		return "teste";
	}

	@GetMapping("/stores")
	private List<Store> getStores() {
		return storeRepository.findAll();
	}
	
	@GetMapping(path = "/listAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "List all stores", response = Store[].class)
	public ResponseEntity<Iterable<Store>> listAll(Pageable pageable){
		return new ResponseEntity<>(storeService.list(pageable), HttpStatus.OK);
	}

	@GetMapping(path = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "List all stores", response = Store[].class)
	public ResponseEntity<List<Store>> listAll(){
		return new ResponseEntity<List<Store>>(storeService.list(), HttpStatus.OK);
	}

	@PostMapping(path = "/save",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Save object store", response = ResponseEntity.class)
	public ResponseEntity<?> save(@Valid @RequestBody Store store){
		return new ResponseEntity<>(storeRepository.save(store), HttpStatus.CREATED);
	}

}
