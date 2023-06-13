package com.example.test.filters;

import com.example.test.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order( Ordered.LOWEST_PRECEDENCE)
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getAccessTokenFromCookie(request);
        if (accessToken == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access token missing");
            return;
        }

        Jws<Claims> jws = JwtUtils.validateToken(accessToken, "SECRET_KEY");
        if (jws == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access token invalid");
            return;
        }

        String userType = (String) jws.getBody().get("userType");
        int userId = Integer.parseInt(jws.getBody().getSubject());
        request.setAttribute("userType", userType);
        request.setAttribute("userId", userId);

        filterChain.doFilter(request, response);
    }

    private String getAccessTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("accessToken")) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
