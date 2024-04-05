package org.acme.security.jwt.usuario;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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

    public Document obtenerUsuario(String nombre) {
        Document usuario = coleccionUsuario.find(eq("arroba", "@" + nombre.replace(" ", "_"))).first();
        if (usuario != null) {
            return usuario;
        } else {
            return new Document();
        }
    }

    public void eliminarUsuario(String arroba) {
        coleccionUsuario.deleteOne(eq("arroba", arroba));
    }
}
