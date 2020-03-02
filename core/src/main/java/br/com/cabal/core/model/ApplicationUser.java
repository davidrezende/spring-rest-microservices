package br.com.cabal.core.model;

import br.com.cabal.core.constants.ValidateConstants;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_user")
public class ApplicationUser implements Serializable {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "USER_ID_SEQ", allocationSize = 100)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = ValidateConstants.VALIDATE_USER_USERNAME)
    @Column(nullable = false)
    private String username;

    @NotBlank(message = ValidateConstants.VALIDATE_USER_PASSWORD)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String role = "USER";

    public ApplicationUser(@NotNull ApplicationUser applicationUser) {
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();
    }
}
