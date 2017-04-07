package si.fri.prpo.vmesniki.Narocilo;

import java.util.List;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Racun;

public interface UpravljalecNarocilSBInterface {

	public void shraniNarocilo(Narocilo n);
	
	public Narocilo vrniNarocilo(int id);
	
	public void spremeniNarocilo(Narocilo nar, int id);
	
	public void odstraniNarocilo(int id);
	
	public List<Object[]> pridobiIzdelkeNarocila(int id);
	
	public Racun pridobiRacunNarocila(int id);
	
	public void prirediRacunNarocilu(Narocilo n, Racun r);
	
	public List<Narocilo> vrniVsaNarocila();
	
}
