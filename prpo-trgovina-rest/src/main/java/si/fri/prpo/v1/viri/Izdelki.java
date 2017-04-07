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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.izjeme.NeustreznoPorociloException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;
import si.fri.prpo.v1.vmesniki.IzdelkiREST;
import si.fri.prpo.vmesniki.Izdelek.UpravljalecIzdelkovSBLocal;




@RequestScoped
@Path("/izdelki")
@Produces({"application/json",}) 
@Consumes({"application/json", "application/xml"})
public class Izdelki implements IzdelkiREST {

	
	@EJB
	private UpravljalecIzdelkovSBLocal upravljalecIzdelkov;
	
	@Context
	private UriInfo uriInfo;
	
//	@OPTIONS
//	@Path("{id}")
//	public Response options() { 
//		return Response.ok().header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
//				.header("Access-Control-Allow-Headers", "accept, content-type").build();
//	}
	
	private static ArrayList<String> orderList = new ArrayList<String>() {{
	    add("id");
	    add("ime");
	    add("kategorija");
	    add("cena");
	    add("zaloga");
	    add("davek");
	    add("datum");
	}};
	
	private static ArrayList<String> ascList = new ArrayList<String>() {{
	    add("asc");
	    add("desc");
	}};
	
	@GET
	public Response vrniIzdelke(@QueryParam("offset") int offset, @QueryParam("limit") int limit, @QueryParam("order") String order, @QueryParam("where") String where) throws NeustreznoPorociloException {
		if(order == null) {
			order = "id ASC";
		}
		String[] orderParams = order.split(" ");
		System.out.println(where);
		String[] whereParams = null;
		if(where != null && !where.trim().isEmpty()) {
			whereParams = where.split(",");
		}
		if(limit > 0 && orderParams.length == 2 && orderList.contains(orderParams[0].toLowerCase()) && ascList.contains(orderParams[1].toLowerCase())) {
//			if (true){
//				throw new NeustreznoPorociloException("Neustrezno");
//			}
			long stIzdelkov = upravljalecIzdelkov.prestejIzdelke();
			int previousOffset = (offset - limit < 0) ? 0 : offset - limit;
			int nextOffset = (offset + limit) > stIzdelkov ? offset : offset + limit;
			List<Izdelek> izdelki = (List<Izdelek>) upravljalecIzdelkov.vrniOstranjeneIzdelke(offset, limit, orderParams[0], "asc".equals(orderParams[1].toLowerCase()) ? true : false, whereParams);
			return Response.ok().entity(izdelki)
				.link(uriInfo.getAbsolutePathBuilder().queryParam("offset", Integer.toString(previousOffset)).queryParam("limit", Integer.toString(limit)).build(), "prev")
				.link(uriInfo.getAbsolutePathBuilder().queryParam("offset", Integer.toString(nextOffset)).queryParam("limit", Integer.toString(limit)).build(), "next")
				.header("X-Total-Count", upravljalecIzdelkov.vrniOstranjeneIzdelke(0, 10000, orderParams[0], "asc".equals(orderParams[1].toLowerCase()) ? true : false, whereParams).size()).build();
		}
		
		return Response.ok().entity(upravljalecIzdelkov.vrniIzdelke()).build();
	}
	
	@Path("/{id}")
	@GET
	public Response vrniIzdelek(@PathParam("id") int id) throws IzdelekNotFoundException	
	{
		return Response.ok().entity(upravljalecIzdelkov.vrniIzdelek(id)).build();
	}

	@POST
	public Response ustvariIzdelek(Izdelek i) throws IzdelekNotFoundException {
		upravljalecIzdelkov.shraniIzdelek(i);
		return Response.ok().build();
	}
	
	@Path("/{id}")
	@PUT
	public Response spremeniIzdelek(Izdelek i, @PathParam("id") int id) throws IzdelekNotFoundException {
		upravljalecIzdelkov.spremeniIzdelek(i, id);
		return Response.ok().entity(upravljalecIzdelkov.vrniIzdelek(id)).build();
	}
	
	@Path("/{id}")
	@DELETE
	public Response odstraniIzdelek(@PathParam("id") int id) {
		upravljalecIzdelkov.odstraniIzdelek(id);
		return Response.noContent().build();
	}
	
}
