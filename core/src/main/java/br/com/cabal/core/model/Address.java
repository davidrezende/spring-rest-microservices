package br.com.cabal.core.model;

import br.com.cabal.core.constants.ValiteConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tb_address")
public class Address implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
	@SequenceGenerator(name = "address_id_seq", sequenceName = "ADDRESS_ID_SEQ", allocationSize = 100)
	private Long id;
	//TODO VALIDAR COM API DOS CORREIOS
	@NotNull(message = ValiteConstants.VALIDATE_ZIPCODE)
	@Column(nullable = false)
	private Long zipCode;
	@NotBlank(message = ValiteConstants.VALIDATE_STATE)
	@Column(nullable = false)
	private String state;
	@NotBlank(message = ValiteConstants.VALIDATE_CITY)
	@Column(nullable = false)
	private String city;
	@NotBlank(message = ValiteConstants.VALIDATE_STREETNAME)
	@Column(nullable = false)
	private String streetName;
	private String aditionalInfo;
	
}
