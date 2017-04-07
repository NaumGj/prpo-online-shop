package si.fri.prpo.vmesniki.NakupovalniList;

import java.util.List;

import si.fri.prpo.genericni.IzdelkiInKolicine;

public interface UpravljalecNakupovalniListIzdelekSBLocal {

	public void ustvariNakupovalniListIzdelek(int idIzd, int kolicina, int idList);
	
	public void ustvariNakupovalniListZaIzdelke(IzdelkiInKolicine ik, int idList);
	
	public void zbrisiNakupovalniListIzdelek(int idList, int idIzd);
	
	public List<Object[]> vrniIzdelkeNakupovalnegaLista(int id);

	void zbrisiVse(int idList);
	
}
