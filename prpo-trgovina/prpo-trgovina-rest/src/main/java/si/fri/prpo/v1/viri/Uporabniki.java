package si.fri.prpo.v1.viri;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;
import si.fri.prpo.v1.vmesniki.UporabnikiREST;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListUporabnikaSBLocal;
import si.fri.prpo.vmesniki.Uporabnik.UpravljalecUporabnikovSBLocal;

@RequestScoped
@Path("/uporabniki")
@Produces({"application/json",}) 
@Consumes({"application/json", "application/xml"})
public class Uporabniki implements UporabnikiREST {

	@EJB
	private UpravljalecUporabnikovSBLocal upravljalecUporabnikov;
	
	@EJB
	private UpravljalecNakupovalniListUporabnikaSBLocal upravljalecListaUporabnikov;
	
	@Context 
	private UriInfo uriInfo;
	
	@Override
	@GET
	public Response vrniVsehUporabnikov() {
		return Response.ok().entity(upravljalecUporabnikov.vrniVseUporabnike()).build();
	}
	
	@Override
	@Path("/{id}")
	@GET
	public Response vrniUporabnika(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecUporabnikov.vrniUporabnika(id)).build();
	}

	@Override
	@POST
	public Response ustvariUporabnika(Uporabnik u) {
		upravljalecUporabnikov.dodajNovegaUporabnika(u);;
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(u.getId()));
		return Response.created(builder.build()).entity(upravljalecUporabnikov.vrniUporabnika(u.getId())).build();
	}
	
	@Override
	@Path("/{id}/nakupovalnilisti")
	@GET
	public Response vrniNakupovalneListeUporabnika(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecListaUporabnikov.vrniNakupovalneListeUporabnika(id)).build();
	}

}
