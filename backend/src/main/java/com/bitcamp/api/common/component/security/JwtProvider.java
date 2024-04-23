package com.bitcamp.api.common.component.security;

import com.bitcamp.api.user.model.User;
import com.bitcamp.api.user.model.UserDto;
import com.bitcamp.api.user.repository.UserRepository;
import com.bitcamp.api.user.service.UserServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JwtProvider  {
    @Value("${jwt.iss}")
    private String issuer;

    private final SecretKey secretKey;
    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);

    // private final UserRepository repository;
    // private final UserServiceImpl service;


    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }


    public String createToken(UserDto dto) {

        String token = Jwts.builder()
        .issuer(issuer)
        .signWith(secretKey)
        .expiration(Date.from(expiredDate))
        .subject("bitcamp")
        .claim("username", dto.getUsername())
        .claim("job", dto.getJob())
        .claim("id", dto.getId())
        .compact();
        ;

        log.info("로그인 성공으로 발급된 토큰 : "+token);

        return token;
    }


    public String extractTokenFromHeader(HttpServletRequest request) {
        log.info("프론트에서 넘어온 Request getServletPath 값 : {}", request.getServletPath());
        String bearerToken = request.getHeader("Authorization");
        log.info("프론트에서 넘어온 토큰 값 : {}", bearerToken);
        return bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : "undefined";
    }

    public void printPayload(String accessToken) {
        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        log.info("Jwt 프로바이더 Access Token Header : "+header);
        log.info("Jwt 프로바이더 Access Token payload : "+payload);
    }

    public Claims getPayload(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public UserDto getUserDto(HttpServletRequest request) {

        Long id = Stream.of(request)
        .map(i -> extractTokenFromHeader(i))
        .filter(token->!token.equals("undefined"))
        .peek(token-> log.info("1- 인터셉터 토큰 로그 Bearer 포함 : {}", token))
        .map(user-> getPayload(user).get("id", Long.class))
        .findAny()
        .get()
        ;
        Optional<User> user = repository.findById(id);

        return service.entityToDto(user.get());
     
    }

}
