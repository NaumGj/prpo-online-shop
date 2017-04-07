package si.fri.prpo.v1.vmesniki;

import javax.ws.rs.core.Response;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;

public interface UporabnikiREST {

	public Response vrniUporabnika(int id);

	public Response ustvariUporabnika(Uporabnik u);
	
	public Response vrniVsehUporabnikov();

	public Response vrniNakupovalneListeUporabnika(int id);
	
}
