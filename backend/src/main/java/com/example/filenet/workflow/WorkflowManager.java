package com.example.filenet.workflow;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Simulates FileNet Workflow Processing.
 * Handles approval and rejection of documents.
 */
public class WorkflowManager {
    private static final Queue<String> approvalQueue = new LinkedList<>();

    public static void submitForApproval(String documentId) {
        approvalQueue.add(documentId);
    }

    public static String approveDocument() {
        return approvalQueue.poll(); // Simulate approval process
    }

    public static int pendingApprovals() {
        return approvalQueue.size();
    }
}