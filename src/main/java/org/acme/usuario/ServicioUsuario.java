package org.acme.usuario;

import org.acme.MongoUtil;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuario")
public class ServicioUsuario {
    private UsuarioDAO usuarios = new UsuarioDAO(MongoUtil.getDB());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{nombre}")
    public String obtenerUsuario(@PathParam("nombre") String nombre) {
        return usuarios.obtenerUsuario(nombre).toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void crearUsuario(@FormParam("nombre") String nombre, @FormParam("correo") String correo) {
        usuarios.añadirUsuario(nombre, correo);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void eliminarUsuario(@FormParam("nombre") String nombre) {
        usuarios.eliminarUsuario("@" + nombre);
    }
}
