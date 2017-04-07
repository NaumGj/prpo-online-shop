package si.fri.prpo.vmesniki.Uporabnik;

import java.util.List;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;

public interface UpravljalecUporabnikovSBInterface{

	public void dodajNovegaUporabnika(Uporabnik u);
	
	public List<Uporabnik> vrniVseUporabnike();
	
	public Uporabnik vrniUporabnika(int id);
	
}
