package com.example.backend.config;

import com.example.backend.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthService authService;

    // Login should not be checked
    private static final List<String> EXCLUDED_PATHS = List.of(
            "/api/auth/login"
    );

    public JwtAuthFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    // Dont filter excluded_paths (login)
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return EXCLUDED_PATHS.contains(request.getRequestURI());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // CORS is options method, should be let through
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // Anyone is allowed to GET anything, needed to render html pages
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (!authService.validateToken(authHeader)) {
            System.out.println("Unauthorized");

            response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid or missing token\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
