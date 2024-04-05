
package org.acme.security.jwt.hilo;

import org.acme.security.jwt.MongoUtil;

import jakarta.ws.rs.GET;
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
}
