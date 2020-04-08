package dev.kcrm.web.security.jwt;

import dev.kcrm.web.dto.UserDto;
import dev.kcrm.web.security.WebApiUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@Component
public class JwtProvider {

    private final Log log = LogFactory.getLog(JwtProvider.class);

    private final Base64.Encoder encoder = Base64.getEncoder();

    private String secretKey;

    @Value("${application.securitySecret}")
    private String secretKeyValue = "";

    @PostConstruct
    public void init() {
        log.info("secretKeyValue: " + secretKeyValue);
        this.secretKey = encoder.encodeToString(secretKeyValue.getBytes(StandardCharsets.UTF_8));

        log.info("secretKey: " + secretKey);
    }

    public String createToken(Authentication authentication) {

        WebApiUserDetails details = (WebApiUserDetails)authentication.getDetails();

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", details.getId());
        claims.put("username", details.getUsername());
        claims.put("roles", details.getRoles().stream().collect(Collectors.joining(",")));

        Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
       // SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setId(details.getId())
                .setSubject(authentication.getName())
                .setClaims(claims)
                .setExpiration(Date.from(Instant.now().plus(12, ChronoUnit.HOURS)))
                .signWith(signingKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        if (StringUtils.isEmpty(token) || !validateToken(token)) {
            throw new BadCredentialsException("Invalid token");
        }

        Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());


        Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("roles").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.get("username", String.class), "", authorities);


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, token, authorities);
        UserDto details = new UserDto();
        details.setUsername(principal.getUsername());
        details.setRoles(authorities.stream().map(x->x.getAuthority()).collect(Collectors.joining(",")));
        details.setId(claims.get("id", String.class));

        authenticationToken.setDetails(details);

        return authenticationToken;
    }

    public boolean validateToken(String authToken) {
        try {
            Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(authToken);
            return true;
        }
        catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}
