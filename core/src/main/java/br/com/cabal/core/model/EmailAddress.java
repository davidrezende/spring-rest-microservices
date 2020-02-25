package br.com.cabal.core.model;

import br.com.cabal.core.constants.ValiteConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tb_email")
public class EmailAddress implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emailaddress_id_seq")
	@SequenceGenerator(name = "emailaddress_id_seq", sequenceName = "EMAILADDRESS_ID_SEQ", allocationSize = 100)
	private Long id;
	@NotBlank(message = ValiteConstants.VALIDATE_EMAIL)
	@Email(message = ValiteConstants.VALIDATE_EMAIL)
	@Column(nullable = false)
	private String email;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="STORE_ID", nullable = false)
	private Store store; 
}
