package ru.itis.javawarrior.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.javawarrior.db.model.AppUser;
import ru.itis.javawarrior.db.service.UserService;
import ru.itis.javawarrior.dto.UserDto;
import ru.itis.javawarrior.util.BeanUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * @author lnurullina
 */
public class TokenAuthenticationService {
    private static final long EXPIRATIONTIME = 864_000_000; // 10 days
    private static final String SECRET = "ThisIsASecret";
    private static final String HEADER_STRING = "Authorization";
    private static final String APPLICATION_JSON = "application/json";
    private static final String UTF_8 = "UTF-8";
    private UserService userService;

    TokenAuthenticationService() {
        userService = BeanUtil.getBean(UserService.class);
    }

    void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();


        AppUser user = userService.findByEmail(username);
        UserDto userDto = new UserDto(username,
                user.getLevel(),
                JWT);
        try {
            res.setContentType(APPLICATION_JSON);
            res.setCharacterEncoding(UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            res.getWriter().write(objectMapper.writeValueAsString(userDto));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String addAuthenticationAfterSignUp(String username, HttpServletResponse res) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(username, null, emptyList()));
        return JWT;
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}