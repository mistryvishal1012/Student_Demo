package com.example.student.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.header.Header;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class AuthFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) httpServletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) httpServletResponse;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        System.out.println(httpRequest.getServletPath());
        String token = httpRequest.getHeader("Bearer");
        if(httpRequest.getServletPath().equals("/api/v1/auth/login")){
            filterChain.doFilter(httpRequest,httpResponse);
        }else{
        if (token == null) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
            return;
        }else{

                try {

                    Jws<Claims> claimsJws = Jwts.parser()
                            .setSigningKey("securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure")
                            .parseClaimsJws(token);
                    Claims body = claimsJws.getBody();

                    String username = body.getSubject();
                    List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("Role");

                    Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                            .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                            .collect(Collectors.toSet());

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,simpleGrantedAuthorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(httpRequest,httpResponse);
                }catch (Exception e) {
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
                    return;
                }
            }
    }
    }
}
