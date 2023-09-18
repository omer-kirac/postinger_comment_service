package com.kafein.intern.postinger_comment_service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RequiredArgsConstructor
@Service
public class JwtUtil {
    private String SECRET_KEY = "NTNv7j0TuYARvmNMmWXo6fKvM4o6nv/aUi9ryX38ZH+L1bkrnD1ObOQ8JAUmHCBq7Iy7otZcyAagBLHVKvvYaIpmMuxmARQ97jUVG16Jkpkp1wXOPsrF9zwew6TpczyHkHgX5EuLg2MeBuiT/qJACs1J0apruOOJCg/gOtkjB4c=";

    public Long extractId(@CookieValue(name = "jwtToken",required = false)String jwtFromCookie){
        if (jwtFromCookie != null){
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtFromCookie)
                    .getBody();
            Long userId = claims.get("userId", Long.class);
            return userId;
        }else{
            return null;
        }
    }
}
