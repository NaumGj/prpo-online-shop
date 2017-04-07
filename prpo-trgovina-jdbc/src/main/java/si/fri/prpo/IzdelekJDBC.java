package si.fri.prpo;

/**
 * Ta razred razsirja abstraktni razred Entiteta ter vsebuje polja tabele izdelkov
 * v podatkovni bazi
 * @author skupina05
 *
 */
public class IzdelekJDBC extends Entiteta{
	
	private static final long serialVersionUID = 1L;
	
	private String ime;			//ime izdelka
	private String kategorija;	//kategorija izdelka (opcijsko)
	private String opis;		//opis izdelka
	private double cena;		//cena izdelka
	private int zaloga;			//zaloga izdelka
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getKategorija() {
		return kategorija;
	}
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
	
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public int getZaloga() {
		return zaloga;
	}
	public void setZaloga(int zaloga) {
		this.zaloga = zaloga;
	}
	
}
