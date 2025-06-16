package com.keretaapi.user_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.springframework.lang.NonNull;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        
        logger.info("================== AuthTokenFilter START ==================");
        logger.info("Request URI: {}", request.getRequestURI());

        try {
            String jwt = parseJwt(request);
            if (jwt != null) {
                logger.info("JWT ditemukan dalam header.");

                if (jwtUtils.validateJwtToken(jwt)) {
                    logger.info("JWT valid.");
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);
                    logger.info("Username dari token: {}", username);

                    logger.info("Mencoba memuat UserDetails...");
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    logger.info("UserDetails berhasil dimuat untuk user: {}. Dengan roles: {}", userDetails.getUsername(), userDetails.getAuthorities());

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    logger.info("Mencoba mengatur SecurityContext...");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("SecurityContext BERHASIL diatur. User sekarang terotentikasi.");

                } else {
                    logger.warn("Validasi JWT GAGAL!");
                }
            } else {
                logger.warn("JWT tidak ditemukan dalam header 'Authorization'.");
            }
        } catch (Exception e) {
            logger.error("Error di dalam AuthTokenFilter: ", e);
        }

        logger.info("================== AuthTokenFilter END ====================");
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}