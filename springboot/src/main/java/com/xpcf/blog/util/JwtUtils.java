package com.xpcf.blog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TODO
 *
 * @author XPCF
 * @version 1.0
 * @date 10/3/2020 7:55 PM
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "xpcf.jwt")
public class JwtUtils {
    private String secret;
    private Long expire;
    private String header;


    /**
     *
     * @param userId
     * @return
     */
    public String generateToken(long userId){
        Date nowDate = new Date();

        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     *
     * @param token
     * @return
     */
    public Claims getClaimsByToken(String token){
        try{
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.debug("validate is token error ", e);
            return null;
        }

    }

    public Boolean isTokenExpire(Date expiration){
        return expiration.before(new Date());
    }

}
