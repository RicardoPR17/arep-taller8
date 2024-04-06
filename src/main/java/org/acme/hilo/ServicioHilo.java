
package org.acme.hilo;

import org.acme.MongoUtil;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hilo")
public class ServicioHilo {
    private HiloDAO hilo = new HiloDAO(MongoUtil.getDB());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerHilo() {
        return hilo.obtenerHilo().toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void agregarPost(@FormParam("arroba") String arroba, @FormParam("mensaje") String mensaje) {
        hilo.añadirPost(arroba, mensaje);
    }
}
