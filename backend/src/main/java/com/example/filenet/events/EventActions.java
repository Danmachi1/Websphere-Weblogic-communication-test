package com.example.filenet.events;

/**
 * Simulates FileNet Event Actions.
 * Triggers when documents are uploaded, modified, or deleted.
 */
public class EventActions {
    public static void onDocumentUpload(String docId) {
        System.out.println("[Event] Document " + docId + " uploaded! Sending notification...");
    }

    public static void onDocumentDelete(String docId) {
        System.out.println("[Event] Document " + docId + " deleted!");
    }
}