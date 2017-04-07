package si.fri.prpo.v1.vmesniki;

import javax.ws.rs.core.Response;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;

public interface NarocilaREST {

	public Response vrniNarocilo(int id);

	public Response ustvariNarocilo(Narocilo n);
	
	public Response spremeniNarocilo(Narocilo n, int id);
	
	public Response odstraniNarocilo(int id);
	
	public Response pridobiRacunNarocila(int id);
	
	public Response pridobiIzdelkeNarocila(int id);

	public Response vrniNarocila();
	
}
