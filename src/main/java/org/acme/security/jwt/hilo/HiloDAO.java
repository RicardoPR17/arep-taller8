
package org.acme.security.jwt.hilo;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HiloDAO {
    private final MongoCollection<Document> coleccionHilo;

    public HiloDAO(MongoDatabase database) {
        this.coleccionHilo = database.getCollection("hilo");

        Document hiloPrincipal = new Document("nombre", "Principal").append("posts", new ArrayList<Document>());

        coleccionHilo.insertOne(hiloPrincipal);
    }

    public Document obtenerHilo() {
        Document hilo = coleccionHilo.find().first();
        if (hilo != null) {
            return hilo;
        } else {
            return new Document();
        }
    }
}
