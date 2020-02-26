package br.com.cabal.core.repository;

import br.com.cabal.core.model.Address;
import br.com.cabal.core.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long>{
	List<Address> findAll();
}
