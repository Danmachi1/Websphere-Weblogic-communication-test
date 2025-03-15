package com.example.filenet.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulates FileNet Security & Role-based Access Control.
 * Uses simple role assignments for now.
 */
public class SecurityManager {
    private static final Map<String, String> userRoles = new HashMap<>();

    static {
        userRoles.put("admin", "WRITE");
        userRoles.put("user", "READ");
    }

    public static boolean hasPermission(String username, String action) {
        return "WRITE".equals(userRoles.get(username)) || "READ".equals(action);
    }
}