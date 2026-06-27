package com.vetsportvzla.backend.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class CorrelationIdFilter implements Filter {

    private static final String CORRELATION_ID_HEADER_NAME = "correlation-id";
    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String correlationId = httpServletRequest.getHeader(CORRELATION_ID_HEADER_NAME);
            if (Strings.isBlank(correlationId)) {
                correlationId = UUID.randomUUID().toString();
            }
            MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
        }
    }
}
