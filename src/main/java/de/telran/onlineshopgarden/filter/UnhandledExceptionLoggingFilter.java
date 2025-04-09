package de.telran.onlineshopgarden.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UnhandledExceptionLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LogManager.getLogger(UnhandledExceptionLoggingFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            log.error("Unprocessed error when requesting [{} {}]: {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    ex.getMessage(),
                    ex);
            throw ex;
        }
    }
}
