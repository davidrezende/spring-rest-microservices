package br.com.cabal.core.model;

import br.com.cabal.core.constants.ValiteConstants;
import io.swagger.models.Contact;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_store")
public class Store implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5610832045139819767L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_id_seq")
    @SequenceGenerator(name = "store_id_seq", sequenceName = "STORE_ID_SEQ", allocationSize = 100)
    private Long id;

    @NotBlank(message = ValiteConstants.VALIDATE_NAME)
    @Size(min = ValiteConstants.MIN_NAME_SIZE, max = ValiteConstants.MAX_NAME_SIZE, message = ValiteConstants.INVALID_SIZE)
    @Column(nullable = false)
    private String name;

    @NotBlank(message = ValiteConstants.VALIDATE_CNPJ)
    @Column(nullable = false)
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Address address;

    @Column(nullable = false)
    private Date inclusionDate;
    @Column(nullable = false)
    private Date lastUpdated;
    @Column(nullable = false)
    private Status isActive;

/*
	@OneToMany(mappedBy = "emailAddress")
	private StoreEmails emails;

	@OneToMany(mappedBy = "contactPhone")
	private StorePhones phones;
*/

    @NotEmpty(message = ValiteConstants.VALIDATE_PHONE_AT_LEAST_ONE)
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_store")
    private List<ContactPhone> phones;

    @NotEmpty(message = ValiteConstants.VALIDATE_EMAIL_AT_LEAST_ONE)
    @OneToMany(cascade = {CascadeType.ALL}) //(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_store")
    private List<EmailAddress> emails;
}
