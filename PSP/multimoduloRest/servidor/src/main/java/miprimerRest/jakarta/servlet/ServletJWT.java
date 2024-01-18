package miprimerRest.jakarta.servlet;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@WebServlet(name = "ServletJWT", value = "/JWT")
//@ServletSecurity(
//        @HttpConstraint(rolesAllowed = {"admin"})
//)
public class ServletJWT extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // clave aleatoria
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);


        // clave fija
        final MessageDigest digest =
                MessageDigest.getInstance("SHA-512");
        digest.update("clave".getBytes(StandardCharsets.UTF_8));
        final SecretKeySpec key2 = new SecretKeySpec(
                digest.digest(), 0, 64, "AES");
        SecretKey keyConfig = Keys.hmacShaKeyFor(key2.getEncoded());

        String jws = Jwts.builder()
                .setSubject("oscar")
                .setIssuer("self")
                .setExpiration(Date
                        .from(LocalDateTime.now().plusSeconds(60).atZone(ZoneId.systemDefault())
                                .toInstant()))
                .claim("user", "juan")
                .claim("group", Set.of("admin", "user"))
                .claim("jj","jajaj")
                .signWith(key).compact();

        response.getWriter().println(jws);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
