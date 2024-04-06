package org.acme.usuario;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class UsuarioDAO {
    private final MongoCollection<Document> coleccionUsuario;

    public UsuarioDAO(MongoDatabase database) {
        this.coleccionUsuario = database.getCollection("usuarios");
    }

    public void añadirUsuario(String nombre, String correo) {
        Document newUser = new Document("nombre", nombre)
                .append("arroba", "@" + nombre.replace(" ", "_"))
                .append("correo", correo);
        coleccionUsuario.insertOne(newUser);
    }

    public void listarUsuarios() {
        FindIterable<Document> users = coleccionUsuario.find();
        for (Document user : users) {
            System.out.println(user.toJson());
        }
    }

    public String obtenerUsuario(String nombre) {
        Bson projection = Projections.fields(Projections.include("arroba"),
                Projections.excludeId());
        Document usuario = coleccionUsuario.find(eq("arroba", nombre.replace(" ", "_"))).projection(projection)
                .first();
        Gson json = new Gson();
        if (usuario != null) {
            return json.toJson(usuario);
        } else {
            return json.toJson(new Document());
        }
    }

    public void eliminarUsuario(String arroba) {
        coleccionUsuario.deleteOne(eq("arroba", arroba));
    }
}
