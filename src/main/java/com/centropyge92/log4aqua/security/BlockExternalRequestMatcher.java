package com.centropyge92.log4aqua.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

public class BlockExternalRequestMatcher implements RequestMatcher {

    private final List<String> protectedPaths = List.of("/cleanAquariumWithoutUser");

    @Override
    public boolean matches(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String path = request.getRequestURI();

        // Si on est sur une route protégée...
        if (protectedPaths.contains(path)) {
            // ...et que ce n'est PAS localhost → on bloque
            return !(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1") || ip.equals("::1"));
        }
        return false; // Laisse Spring gérer les autres routes
    }
}
