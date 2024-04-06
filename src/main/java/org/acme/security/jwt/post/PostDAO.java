package org.acme.security.jwt.post;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

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
        Bson projection = Projections.fields(Projections.include("usuario", "mensaje"),
                Projections.excludeId());
        List<Document> posts = new ArrayList<>();
        coleccionPost.find().projection(projection).into(posts);

        return json.toJson(posts);
    }

    public String obtenerPostsUsuario(String arroba) {
        Bson projection = Projections.fields(Projections.include("usuario", "mensaje"),
                Projections.excludeId());
        List<Document> postsUsuario = new ArrayList<>();
        coleccionPost.find(eq("usuario", arroba)).projection(projection).into(postsUsuario);
        Gson json = new Gson();

        return json.toJson(postsUsuario);
    }

    public void eliminarPost(String arroba, String mensaje) {
        Bson filtro = Filters.and(eq("usuario", arroba), eq("mensaje", mensaje));
        coleccionPost.deleteOne(filtro);
    }
}
