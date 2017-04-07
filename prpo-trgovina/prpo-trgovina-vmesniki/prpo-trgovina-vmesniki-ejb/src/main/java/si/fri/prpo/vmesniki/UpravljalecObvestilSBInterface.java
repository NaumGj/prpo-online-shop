package si.fri.prpo.vmesniki;

import java.util.List;

import si.fri.prpo.izjeme.NeustreznoPorociloException;
import si.fri.prpo.izjeme.NiNaZalogiException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Obvestila;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Racun;

public interface UpravljalecObvestilSBInterface {

	public String vrniHTMLRacun(List<Object[]> izdelkiInKolicina) throws NeustreznoPorociloException;
	
	public Racun narediRacun(List<Object[]> izdelkiInKolicina, String nacinPlacila) throws NiNaZalogiException;
	
	public Obvestila vrniObvestilo(int id);
	
	public void shraniObvestilo(Obvestila obv);
}
