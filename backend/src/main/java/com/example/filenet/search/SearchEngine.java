package com.example.filenet.search;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.store.RAMDirectory;
import java.io.StringReader;

/**
 * Simulates FileNet Content Search Services (CSS).
 * Uses Apache Lucene for text search.
 */
public class SearchEngine {
    private static RAMDirectory index = new RAMDirectory();
    private static StandardAnalyzer analyzer = new StandardAnalyzer();

    public static void addDocument(String id, String content) throws Exception {
        IndexWriter writer = new IndexWriter(index, new IndexWriterConfig(analyzer));
        org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
        doc.add(new org.apache.lucene.document.TextField("id", id, org.apache.lucene.document.Field.Store.YES));
        doc.add(new org.apache.lucene.document.TextField("content", content, org.apache.lucene.document.Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    public static String searchDocument(String queryStr) throws Exception {
        QueryParser parser = new QueryParser("content", analyzer);
        return parser.parse(queryStr).toString();
    }
}