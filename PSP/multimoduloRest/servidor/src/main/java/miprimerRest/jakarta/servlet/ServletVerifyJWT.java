package miprimerRest.jakarta.servlet;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@WebServlet(name = "ServletVerifyJWT", value = "/VerifyJWT")
public class ServletVerifyJWT extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String auth = request.getParameter("auth");

        final MessageDigest digest =
                MessageDigest.getInstance("SHA-512");
        digest.update("clave".getBytes(StandardCharsets.UTF_8));
        final SecretKeySpec key2 = new SecretKeySpec(
                digest.digest(), 0, 64, "AES");
        SecretKey key22 = Keys.hmacShaKeyFor(key2.getEncoded());


        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key22)
                .build()
                .parseClaimsJws(auth);



       response.getWriter().println(jws.getBody().get("user"));
       response.getWriter().println(jws.getBody().get("group"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
