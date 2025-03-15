package com.example.filenet.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulates FileNet Document Storage.
 * This class stores document metadata (ID, name, version, owner).
 */
public class DocumentStorage {
    private static final Map<String, FileNetDocument> storage = new HashMap<>();

    public static void storeDocument(FileNetDocument doc) {
        storage.put(doc.getId(), doc);
    }

    public static FileNetDocument getDocument(String id) {
        return storage.get(id);
    }

    public static void deleteDocument(String id) {
        storage.remove(id);
    }
}