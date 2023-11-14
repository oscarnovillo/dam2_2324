package cliente.ui;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class TestJwt {



    private static class Cred{
        public Set<String> groups = Set.of("kk","gg");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {



        Cred c = new Cred();

        final MessageDigest digest =
                MessageDigest.getInstance("SHA-512");
        digest.update("clave".getBytes(StandardCharsets.UTF_8));
        final SecretKeySpec key2 = new SecretKeySpec(
                digest.digest(), 0, 64, "AES");
        SecretKey keyConfig = Keys.hmacShaKeyFor(key2.getEncoded());


        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String jws = Jwts.builder()
                .setSubject("Joe")
                .setIssuer("yo")

                .setExpiration(Date
                        .from(LocalDateTime.now().plusSeconds(60).atZone(ZoneId.systemDefault())
                                .toInstant()))
                .claim("user", "juan")
                .claim("group", c.groups)
                .signWith(keyConfig).compact();

        System.out.println(jws);


        Jws<Claims> jwsV = Jwts.parserBuilder()
                .setSigningKey(keyConfig)
                .build()
                .parseClaimsJws(jws);

        List<String> s = (List<String>) jwsV.getBody().get("group");

        s.forEach(s1 -> System.out.println(s1 + " :::: "));

        new HashSet<String>(s);

    }

}
