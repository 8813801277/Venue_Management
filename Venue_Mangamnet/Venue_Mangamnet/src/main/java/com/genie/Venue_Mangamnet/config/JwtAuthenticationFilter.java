package com.genie.Venue_Mangamnet.config;

import com.genie.Venue_Mangamnet.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("Extracted Token: " + token);

            boolean isValid = jwtUtil.validateToken(token);
            System.out.println("Token Validation Result: " + isValid);

            if (isValid) {
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);
                System.out.println("Extracted Username: " + username);
                System.out.println("Extracted Role: " + role);


                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))  // Add "ROLE_" prefix
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else{
                System.out.println("Token Authentication Failed");
            }
        }

        filterChain.doFilter(request, response);
    }
}