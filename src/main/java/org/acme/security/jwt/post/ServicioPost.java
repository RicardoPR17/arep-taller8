package org.acme.security.jwt.post;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.security.jwt.MongoUtil;
import org.acme.security.jwt.usuario.UsuarioDAO;

@Path("/post")
public class ServicioPost {

    private PostDAO post= new PostDAO(MongoUtil.getDB());
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{post}")
    public String obtenerPost(@PathParam("nombre") String nombre) {
        return post.obtenerPostsUsuario("@"+nombre);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerPosts() {
        return post.listarPost();
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void crearPost(@FormParam("arroba") String arroba, @FormParam("mensaje") String mensaje) {
        post.añadirPost(arroba, mensaje);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void eliminarPost(@FormParam("arroba") String arroba , @FormParam("mensaje") String mensaje) {
        post.eliminarPost(arroba, mensaje);
    }
}
