package si.fri.prpo.v1.vmesniki;

import javax.ws.rs.core.Response;

import si.fri.prpo.genericni.IzdelkiInKolicine;
import si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniList;

public interface NakupovalniListREST {

	public Response vrniNakupovalniList(int id);
	
	public Response ustvariNakupovalniList(NakupovalniList n);
	
	public Response zbrisiNakupovalniList(int id);
	
	public Response uporabnikiNakupovalnegaLista(int id);
	
	public Response dodajUporabnika(int id, int u);
	
	public Response zbrisiUporabnika(int id, int u);
	
	public Response izdelkiNakupovalnegaLista(int id);
	
	public Response dodajIzdelek(int id, IzdelkiInKolicine ik);
	
	public Response izbrisiIzdelek(int id, int u);
}
