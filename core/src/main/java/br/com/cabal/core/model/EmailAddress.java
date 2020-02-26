package br.com.cabal.core.model;

import br.com.cabal.core.constants.ValidateConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ToString
@Builder
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
	@NotBlank(message = ValidateConstants.VALIDATE_EMAIL)
	@Email(message = ValidateConstants.VALIDATE_EMAIL)
	@Column(nullable = false)
	private String email;
/*	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="STORE_ID", nullable = false)
	private Store store; */
}
