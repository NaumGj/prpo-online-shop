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

import si.fri.prpo.genericni.IzdelkiInKolicine;
import si.fri.prpo.v1.vmesniki.NakupovalniListREST;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListIzdelekSBLocal;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListLocal;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListUporabnikaSBLocal;



@RequestScoped
@Path("/nakupovalnilisti")
@Produces({"application/json",}) 
@Consumes({"application/json", "application/xml"})
public class NakupovalniList implements NakupovalniListREST {

	@EJB
	private UpravljalecNakupovalniListLocal upravljalecLista;
	
	@EJB
	private UpravljalecNakupovalniListIzdelekSBLocal upravljalecListaIzdelkov;
	
	@EJB
	private UpravljalecNakupovalniListUporabnikaSBLocal upravljalecListaUporabnikov;
	
	@Context
	private UriInfo uriInfo;
	
	@Override
	@Path("/{id}")
	@GET
	public Response vrniNakupovalniList(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecLista.vrniNakupovalniList(id)).build();
	}
	
	@Override
	@POST
	public Response ustvariNakupovalniList(si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniList n) {
		upravljalecLista.dodajNovNakupovalniList(n);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(n.getId()));
		return Response.created(builder.build()).entity(upravljalecLista.vrniNakupovalniList(n.getId())).build();
	}
	
	@Override
	@Path("/{id}")
	@DELETE
	public Response zbrisiNakupovalniList(@PathParam("id") int id) {
		upravljalecListaIzdelkov.zbrisiVse(id);
		upravljalecListaUporabnikov.zbrisiVse(id);
		upravljalecLista.zbrisiNakupovalniList(id);
		return Response.noContent().build();
	}
	
	@Override
	@Path("/{id}/uporabniki")
	@GET
	public Response uporabnikiNakupovalnegaLista(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecListaUporabnikov.vrniUporabnikeNakupovalnegaLista(id)).build();
	}

	@Override
	@Path("/{id}/uporabniki")
	@POST
	public Response dodajUporabnika(@PathParam("id") int id, int u) {
		upravljalecListaUporabnikov.ustvariNakupovalniListUporabnika(u, id);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(u));
        return Response.created(builder.build()).entity(upravljalecLista.vrniNakupovalniList(id)).build();
	}

	@Override
	@Path("/{id}/uporabniki")
	@DELETE
	public Response zbrisiUporabnika(@PathParam("id") int id, int idU) {
		upravljalecListaUporabnikov.zbrisiNakupovalniListUporabnika(idU, id);
		return Response.noContent().build();
	}
	
	@Override
	@Path("/{id}/izdelki")
	@GET
	public Response izdelkiNakupovalnegaLista(@PathParam("id") int id) {
		return Response.ok().entity(upravljalecListaIzdelkov.vrniIzdelkeNakupovalnegaLista(id)).build();
	}

	@Override
	@Path("/{id}/izdelki")
	@PUT
	public Response dodajIzdelek(@PathParam("id") int id, IzdelkiInKolicine ik) {
		upravljalecListaIzdelkov.ustvariNakupovalniListZaIzdelke(ik, id);
		si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniList n = upravljalecLista.vrniNakupovalniList(id);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(n.getId()));
        return Response.created(builder.build()).entity(upravljalecLista.vrniNakupovalniList(id)).build();
	}
	
	@Override
	@Path("/{id}/izdelki")
	@DELETE
	public Response izbrisiIzdelek(@PathParam("id") int id, int idIzd) {
		upravljalecListaIzdelkov.zbrisiNakupovalniListIzdelek(id, idIzd);
		return Response.noContent().build();
	}
	
}
