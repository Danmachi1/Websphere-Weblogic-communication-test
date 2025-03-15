package com.example.filenet.audit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates FileNet Audit Logging.
 * Tracks document access, changes, and workflow events.
 */
public class AuditLogger {
    private static final List<String> logs = new ArrayList<>();

    public static void log(String action, String user, String documentId) {
        String entry = LocalDateTime.now() + " - " + user + " " + action + " document " + documentId;
        logs.add(entry);
        System.out.println("[Audit Log] " + entry);
    }

    public static List<String> getLogs() {
        return logs;
    }
}