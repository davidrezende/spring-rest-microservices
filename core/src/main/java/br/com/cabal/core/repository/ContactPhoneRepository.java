package br.com.cabal.core.repository;

import br.com.cabal.core.model.ContactPhone;
import br.com.cabal.core.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContactPhoneRepository extends PagingAndSortingRepository<ContactPhone, Long>{
	List<ContactPhone> findAll();
}
