package br.com.cabal.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cabal.core.model.Address;
import br.com.cabal.core.model.ContactPhone;
import br.com.cabal.core.model.EmailAddress;
import br.com.cabal.core.model.PhoneType;
import br.com.cabal.core.model.Status;
import br.com.cabal.core.model.Store;
import br.com.cabal.core.repository.StoreRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StoreRepositoryTest {
	@Autowired
	private StoreRepository storeRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Ignore
	public void createShouldPersistData() {
		Store store = new Store();
		store.setAddress(new Address(null, 70675120L, "DF", "Brasilia", "W3", "Apto"));
		store.setCpnj("99999999999999");
		store.setInclusionDate(new Date());
		store.setIsActive(Status.REGISTER_ACTIVE);
		store.setLastUpdated(new Date());
		store.setName("StoreTest");
		List<EmailAddress> listEmails = new ArrayList<EmailAddress>();
		listEmails.add(new EmailAddress(null, "test@test.com", new Store()));
		store.setEmails(listEmails);
		List<ContactPhone> listPhones = new ArrayList<ContactPhone>();
		listPhones.add(new ContactPhone(null, PhoneType.HOME_PHONE, 31, 34752566L, new Store()));
		store.setPhones(listPhones);

		this.storeRepository.save(store);
		assertThat(store.getId()).isNotNull();
		assertThat(store.getName()).isEqualTo("StoreTest");
		assertThat(store.getCpnj()).isNotNull();
		assertThat(store.getEmails()).isNotNull();
		assertThat(store.getIsActive()).isNotNull();
		assertThat(store.getInclusionDate()).isNotNull();
		assertThat(store.getLastUpdated()).isNotNull();
		assertThat(store.getLastUpdated()).isNotNull();
		assertThat(store.getPhones()).isNotNull();
	}
}
