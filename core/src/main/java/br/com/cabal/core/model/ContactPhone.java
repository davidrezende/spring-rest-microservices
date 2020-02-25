package br.com.cabal.core.model;

import br.com.cabal.core.constants.ValiteConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tb_phone")
public class ContactPhone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2953488720632156984L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactphone_id_seq")
	@SequenceGenerator(name = "contactphone_id_seq", sequenceName = "CONTACTPHONE_ID_SEQ", allocationSize = 100)
	private Long id;
	@NotNull(message = ValiteConstants.VALIDATE_PHONE_TYPE)
	@Column(nullable = false)
	private PhoneType type;
	@Column(nullable = false)
	@NotNull(message = ValiteConstants.VALIDATE_PHONE)
	private int areaCode;
	@NotNull(message = ValiteConstants.VALIDATE_PHONE)
	@Column(nullable = false)
	private Long number;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="STORE_ID", nullable = false)
	private Store store; 
	 
}
 