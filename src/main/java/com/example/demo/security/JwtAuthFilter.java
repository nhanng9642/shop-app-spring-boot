package com.example.demo.security;

import com.example.demo.exception.ErrorResponse;
import com.example.demo.token.Token;
import com.example.demo.token.TokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    protected void doFilterInternal
            (@NonNull HttpServletRequest request,
             @NonNull HttpServletResponse response,
             @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        try {
            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
            }
            else {
                int beginTokenIndex = 7; //length of "Bearer "
                final String jwt = header.substring(beginTokenIndex);
                final String username = jwtService.extractUsername(jwt);

                Token token = tokenRepository.findByToken(jwt).orElse(null);
                if (token != null && token.isRevoked()) {
                    filterChain.doFilter(request, response);
                    return;
                }

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails user = userDetailsService.loadUserByUsername(username);
                    if (!jwtService.isTokenExpired(jwt)) {
                        UsernamePasswordAuthenticationToken authenticationToken
                                = new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request, response);
                    }
                }
            }
        }
        catch (ExpiredJwtException eje) {
            log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
            log.trace("Security exception trace: {}", (Object) eje.getStackTrace());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            errorResponse.setMessage(eje.getMessage());

            new ObjectMapper().writeValue(response.getOutputStream(),
                    errorResponse);
        } catch (MalformedJwtException mje) {
            log.info("Security exception: {}", mje.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            errorResponse.setMessage(mje.getMessage());

            new ObjectMapper().writeValue(response.getOutputStream(),
                    errorResponse);
        }
    }
}
