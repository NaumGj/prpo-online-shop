package si.fri.prpo.fasade;

import java.util.List;

import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.izjeme.NiNaZalogiException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;

public interface UpravljalecNarocniskihRazmerijSBInterface {
	
	public String ustvariNarociloInVrniHTMLRacun(List<Integer> idIzdelkov, List<Integer> kolicine) throws IzdelekNotFoundException;
	
	public int ustvariNarociloInPripadajociRacun(List<Integer> idIzdelkov, List<Integer> kolicine) throws NiNaZalogiException, IzdelekNotFoundException;
	
	public void vstaviNarociloInGenerirajRacun(Narocilo n) throws NiNaZalogiException, IzdelekNotFoundException;
}
