package si.fri.prpo.vmesniki.NakupovalniList;

import java.util.List;

import si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniList;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;

public interface UpravljalecNakupovalniListUporabnikaSBLocal {

	public void ustvariNakupovalniListUporabnika(int u, int idList);
	
	public void zbrisiNakupovalniListUporabnika(int u, int idList);
	
	public List<NakupovalniList> vrniNakupovalneListeUporabnika(int id);

	public List<Uporabnik> vrniUporabnikeNakupovalnegaLista(int id);

	void zbrisiVse(int idList);
	
}
