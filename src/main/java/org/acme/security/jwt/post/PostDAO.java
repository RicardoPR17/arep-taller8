package org.acme.security.jwt.post;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class PostDAO {

    private final MongoCollection<Document> coleccionPost;

    public PostDAO(MongoDatabase database) {
        this.coleccionPost = database.getCollection("posts");
    }

    public void añadirPost(String arroba, String mensaje) {
        Document nuevoPost = new Document("usuario", arroba)
                .append("mensaje", mensaje);
        coleccionPost.insertOne(nuevoPost);
    }

    public String listarPost() {
        Gson json = new Gson();
        FindIterable<Document> posts = coleccionPost.find();
        return json.toJson(posts);
    }

    public String obtenerPostsUsuario(String arroba) {
        Bson projection = Projections.fields(Projections.include( "usuario", "mensaje"),
                Projections.excludeId());
        FindIterable<Document> postsUsuario = coleccionPost.find(eq("usuario",  arroba)).projection(projection);
        Gson json = new Gson();
        if (postsUsuario != null) {
            return json.toJson(postsUsuario);
        } else {
            return json.toJson(new Document());
        }
    }

    public void eliminarPost(String arroba, String mensaje) {
        Bson filtro= Filters.and(Filters.eq("arroba", arroba),Filters.eq("mensaje", mensaje));
        coleccionPost.deleteOne(filtro);
    }
}
