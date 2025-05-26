package org.example.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidateProvider {
    @Value("${security.token.secret.candidate}")
    private String secret;

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            JWT.require(algorithm).build().verify(token);
            var tokenDecoded = JWT.require(algorithm).build().verify(token);
            return tokenDecoded;

        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}
