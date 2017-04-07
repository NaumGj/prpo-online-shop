package si.fri.prpo.v1.viri;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.v1.vmesniki.NarocilaREST;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBLocal;

@RequestScoped
@Path("/narocila")
@Produces({"application/json",}) 
@Consumes({"application/json", "application/xml"})
public class Narocila implements NarocilaREST {

	@EJB
	private UpravljalecNarocilSBLocal upravljalecNarocil;
	
	@Context 
	private UriInfo uriInfo;
	
	@Override
	@Path("/{id}")
	@GET
	public Response vrniNarocilo(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecNarocil.vrniNarocilo(id)).build();
	}
	
	@Override
	@GET
	public Response vrniNarocila() {
		return Response.ok().entity(upravljalecNarocil.vrniVsaNarocila()).build();
	}

	@Override
	@POST
	public Response ustvariNarocilo(Narocilo n) {
		upravljalecNarocil.shraniNarocilo(n);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(n.getId()));
		return Response.created(builder.build()).entity(upravljalecNarocil.vrniNarocilo(n.getId())).build();
	}

	@Override
	@Path("/{id}")
	@PUT
	public Response spremeniNarocilo(Narocilo n, @PathParam("id") int id) {
		upravljalecNarocil.spremeniNarocilo(n, id);
		return Response.ok().entity(upravljalecNarocil.vrniNarocilo(id)).build();
	}

	@Override
	@Path("/{id}")
	@DELETE
	public Response odstraniNarocilo(@PathParam("id") int id) {
		upravljalecNarocil.odstraniNarocilo(id);
		return Response.noContent().build();
	}

	@Override
	@Path("/{id}/racun")
	@GET
	public Response pridobiRacunNarocila(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecNarocil.pridobiRacunNarocila(id)).build();
	}

	@Override
	@Path("/{id}/izdelki")
	@GET
	public Response pridobiIzdelkeNarocila(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecNarocil.pridobiIzdelkeNarocila(id)).build();
	}

}
