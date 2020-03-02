package br.com.cabal.core.repository;

import br.com.cabal.core.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;
import java.util.List;

public interface StoreRepository extends PagingAndSortingRepository<Store, Long>{
	List<Store> findByName(String name);
	Store findByCnpj(String name);
	List<Store> findByNameLike(String name);
	List<Store> findAll();
}