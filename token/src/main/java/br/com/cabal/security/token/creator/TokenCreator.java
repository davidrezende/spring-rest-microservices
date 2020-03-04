package br.com.cabal.security.token.creator;

import br.com.cabal.core.model.ApplicationUser;
import br.com.cabal.core.property.JwtConfiguration;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenCreator {
    private final JwtConfiguration jwtConfiguration;

    /* Nimbus Rose vs JJWT ( mais pratico )
     *Utilizarei Nimbus Rose por ter mais funcionalidades, como a criptografia.
     *Criacao de um JSON assido
     *Para criptografar um token: 1º Assinatura 2º Criptografa o token
     */
    @SneakyThrows
    public SignedJWT createSignedJWT(Authentication auth) {
        log.info("Starting to create the signed JWT ( JWS Json Web Signed )");
        ApplicationUser applicationUser = (ApplicationUser) auth.getPrincipal();
        JWTClaimsSet jwtClaimSet = createJWTClaimSet(auth, applicationUser);
        KeyPair rsaKeys = generateKeyPair();
        log.info("Building JWK from the RSA keys");
        JWK jwk = new RSAKey.Builder((RSAPublicKey) rsaKeys.getPublic()).keyID(UUID.randomUUID().toString()).build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
                .jwk(jwk)
                .type(JOSEObjectType.JWT)
                .build(), jwtClaimSet);

        log.info("Signing the token with the private RSA key");

        RSASSASigner rsassaSigner = new RSASSASigner(rsaKeys.getPrivate());
        signedJWT.sign(rsassaSigner);

        log.info("Serialized token '{}'", signedJWT.serialize());

        return signedJWT;
    }

    private JWTClaimsSet createJWTClaimSet(Authentication auth, ApplicationUser applicationUser) {
        log.info("Creating the JwtClaimSet Object for '{}'", applicationUser);
        return new JWTClaimsSet.Builder()
                .subject(applicationUser.getUsername())
                .claim("authorities", auth.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(toList()))
                .issuer("http://issuer.com.br")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
                .build();
    }

    /*
     *Metodo responsavel por gerar a chave
     *Gerada uma vez e utilizada para os outros microservicos
     */
    @SneakyThrows
    private KeyPair generateKeyPair() {
        log.info("Generating RSA 2048 bits keys");
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public String encryptToken(SignedJWT signedJWT) throws JOSEException {
        log.info("Starting the encrypting");
        DirectEncrypter directEncrypter = new DirectEncrypter((jwtConfiguration.getPrivateKey().getBytes()));
        JWEObject jweObject = new JWEObject(new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                .contentType("JWT")
                .build(), new Payload(signedJWT));
        log.info("Encrypting token with system's private key");
        jweObject.encrypt(directEncrypter);
        log.info("Token encrypted");
        return jweObject.serialize();
    }
}
