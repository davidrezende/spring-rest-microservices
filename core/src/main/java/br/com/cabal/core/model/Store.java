package br.com.cabal.core.model;

import br.com.cabal.core.constants.ValidateConstants;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

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

    @NotBlank(message = ValidateConstants.VALIDATE_NAME)
    @Size(min = ValidateConstants.MIN_NAME_SIZE, max = ValidateConstants.MAX_NAME_SIZE, message = ValidateConstants.INVALID_SIZE)
    @Column(nullable = false)
    private String name;

    @NotBlank(message = ValidateConstants.VALIDATE_CNPJ)
    @CNPJ(message = ValidateConstants.VALIDATE_CNPJ_NOT_VALID)
    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Address address;

    @Column(nullable = false)
    private Date inclusionDate;
    @Column(nullable = false)
    private Date lastUpdated;
    @Column(nullable = false)
    private Status isActive;

    @NotEmpty(message = ValidateConstants.VALIDATE_PHONE_AT_LEAST_ONE)
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_store", nullable = false)
    private List<ContactPhone> phones;

    @NotEmpty(message = ValidateConstants.VALIDATE_EMAIL_AT_LEAST_ONE)
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_store",  nullable = false)
    private List<EmailAddress> emails;
}
