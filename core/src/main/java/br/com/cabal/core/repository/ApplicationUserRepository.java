package br.com.cabal.core.repository;

import br.com.cabal.core.model.ApplicationUser;
import br.com.cabal.core.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long>{
	ApplicationUser findByUsername(String username);
}