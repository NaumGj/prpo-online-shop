package si.fri.prpo.v1.viri;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import si.fri.prpo.fasade.UpravljalecNarocniskihRazmerijSBLocal;
import si.fri.prpo.genericni.IzdelkiInKolicine;
import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.izjeme.NiNaZalogiException;
import si.fri.prpo.v1.vmesniki.NarocniskaRazmerjaREST;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBLocal;

@RequestScoped
@Path("/narocniskarazmerja")
@Produces({"application/json",}) 
@Consumes({"application/json", "application/xml"})
public class NarocniskaRazmerja implements NarocniskaRazmerjaREST {

	@EJB
	private UpravljalecNarocniskihRazmerijSBLocal upravljalecRazmerij;
	
	@EJB
	private UpravljalecNarocilSBLocal upravljalecNarocil;
	
	@Context
	private UriInfo uriInfo;

	@Override
	@POST
	public Response ustvariNarocniskoRazmerje(IzdelkiInKolicine ik) throws NiNaZalogiException, IzdelekNotFoundException {
		List<Integer> idIzdelkov = ik.getIdIzdelkov();
		List<Integer> kolicine = ik.getKolicine();
//		System.out.println(upravljalecRazmerij.ustvariNarociloInPripadajociRacun(idIzdelkov, kolicine));
		int idNarocila = upravljalecRazmerij.ustvariNarociloInPripadajociRacun(idIzdelkov, kolicine);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(idNarocila));
		return Response.created(builder.build()).entity(upravljalecNarocil.vrniNarocilo(idNarocila)).build();
	}
	
}
