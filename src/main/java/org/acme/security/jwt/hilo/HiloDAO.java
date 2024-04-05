
package org.acme.security.jwt.hilo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

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

    public void añadirPost(String arroba, String mensaje) {
        List<Document> post = (List<Document>) coleccionHilo.find().first().get("posts");
        post.add(new Document("arroba", arroba).append("mensaje", mensaje));

        coleccionHilo.findOneAndUpdate(eq("nombre", "principal"), set("posts", post));
    }
}
