package com.Consultorio.Clinica.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private  String issuer;

    @Value("${security.jwt.ttlMillis}")
    private  Long ttlMillis;

    private final Logger log = LoggerFactory
            .getLogger(JWTUtils.class);

    public  String create(String id, String subject){

        //The JWT signature algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //sign JWT with out secret ApiKey
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if(ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-sage String
        return builder.compact();
    }

    /**  Method to validate and read the JWT
     *
     * @param jwt
     * @return
     * */
    public String getValue(String jwt){
        //This code will throw a exception if it is not a signed JWS

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getSubject();
    }
    /** Method to validate and read the JWT
     *
     * @paramjwt
     * @return
     **/

    public String getKey(String jwt){
        //This code will throw a exception if it is not a signed JWS

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getId();
    }
    public boolean validateJWT(String jwt){
       return getKey(jwt) != null ?true :false;
    }


}
