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
import javax.ws.rs.core.UriInfo;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.v1.vmesniki.PaketNarocilREST;
import si.fri.prpo.vmesniki.SpremljanjeIzvajanjaZrnoLocal;
import si.fri.prpo.vmesniki.Narocilo.SprejmiPaketNarocilSBLocal;

@RequestScoped
@Path("/paketinarocil")
@Produces({"application/json",}) 
@Consumes({"application/json", "application/xml"})
public class PaketNarocil implements PaketNarocilREST {

	@EJB
	private SprejmiPaketNarocilSBLocal paket;
	
	@EJB
	private SpremljanjeIzvajanjaZrnoLocal spremljanjeIzvajanjaZrno;
	
	@Context 
	private UriInfo uriInfo;
	
	@Override
	@POST
	public Response vstaviPaket(List<Narocilo> n) {
		spremljanjeIzvajanjaZrno.napredekIzvajanja("Sprejem sem zahtevo z " + n.size() + " narocili");
		paket.shraniNarocila(n);
		return Response.ok().entity(n).build();
	}
	
}
