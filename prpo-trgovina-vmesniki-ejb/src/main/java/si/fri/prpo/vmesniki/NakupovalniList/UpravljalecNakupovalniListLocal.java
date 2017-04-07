package si.fri.prpo.vmesniki.NakupovalniList;

import java.util.List;

import si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniList;

public interface UpravljalecNakupovalniListLocal {

	public NakupovalniList vrniNakupovalniList(int id);
	
	public void zbrisiNakupovalniList(int id);
	
	public NakupovalniList dodajNovNakupovalniList(NakupovalniList n);
	
}
