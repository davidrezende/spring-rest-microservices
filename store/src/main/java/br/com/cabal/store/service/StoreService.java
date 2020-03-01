package br.com.cabal.store.service;

import br.com.cabal.core.model.Store;
import br.com.cabal.core.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.cabal.store.constants.LogConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreService {
	private final StoreRepository storeRepository;
	
	public Store listByCNPJ(Long cnpj) {
		return null;
	}
	
	public Iterable<Store> list(Pageable pageable){
		log.info(LogConstants.LIST_ALL_STORES);
		return storeRepository.findAll(pageable);
	}

	public List<Store> list(){
		log.info(LogConstants.LIST_ALL_STORES);
		return storeRepository.findAll();
	}

	public Store save(Store store){
		log.info(LogConstants.SAVE_STORE);
		return storeRepository.save(store);
	}

	public Store update(Store store){
		log.info(LogConstants.UPDATE_STORE);
		return storeRepository.save(store);
	}

}
