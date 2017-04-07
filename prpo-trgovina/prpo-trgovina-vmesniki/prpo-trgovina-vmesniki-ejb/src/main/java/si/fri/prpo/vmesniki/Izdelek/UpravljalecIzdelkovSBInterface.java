package si.fri.prpo.vmesniki.Izdelek;

import java.util.List;

import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;

public interface UpravljalecIzdelkovSBInterface {

	public Izdelek vrniIzdelek(int id) throws IzdelekNotFoundException;
	
	public List<Izdelek> vrniIzdelke();
	
	public List<Izdelek> vrniOstranjeneIzdelke(int offset, int limit, String order, boolean ascending, String[] whereParams);
	
	public long prestejIzdelke();

	void spremeniIzdelek(Izdelek iz, int id);

	void odstraniIzdelek(int id);

	void shraniIzdelek(Izdelek n);
	
}
