package br.com.cabal.core.repository;

import br.com.cabal.core.model.EmailAddress;
import br.com.cabal.core.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmailAddressRepository extends PagingAndSortingRepository<EmailAddress, Long>{
	List<EmailAddress> findAll();
}
