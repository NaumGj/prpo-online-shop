package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the izdelek database table.
 * 
 */
@Entity
@NamedQueries({
	// izbira izdelka po kategoriji tehnika
	@NamedQuery(name="Izdelek.izberiVseIzdelekeKategorijeTehnika", query="SELECT i FROM Izdelek i WHERE i.kategorija = 'Tehnika'"),
	
	// izbira izdelka po kategoriji tehnika
	@NamedQuery(name="Izdelek.izberiVseIzdelekePoKategoriji", query="SELECT i FROM Izdelek i WHERE i.kategorija = :kategorija"),
	
	// izbira izdelka po id-ju
	@NamedQuery(name="Izdelek.izberiIzdelekPoId", query="SELECT i FROM Izdelek i WHERE i.id = :id"),
	
	// izbira izdelka po imenu
	@NamedQuery(name="Izdelek.izberiIzdelekPoImenu", query="SELECT i FROM Izdelek i WHERE i.ime = :ime"),
	
	// izbira izdelkov po kategoriji
	@NamedQuery(name="Izdelek.izberaIzdelekovPoKategoriji", query="SELECT i FROM Izdelek i WHERE i.kategorija = :kategorija"),
	
	// izbriši izdelek po id-ju
	@NamedQuery(name="Izdelek.izbrisiIzdelekPoId", query="DELETE FROM Izdelek i WHERE i.id = :id"),
	
	// prestej vse izdelke
	@NamedQuery(name="Izdelek.prestejIzdelke", query="SELECT COUNT(i) FROM Izdelek i"),
	
	@NamedQuery(name="Izdelek.findAll", query="SELECT i FROM Izdelek i")
})
@XmlRootElement
public class Izdelek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private double cena;

	@Column(nullable=false)
	private double davek;

	@Column(nullable=false, length=45)
	private String ime;

	@Column(nullable=false, length=45)
	private String kategorija;

	@Column(length=150)
	private String opis;
	
	@Column(nullable=false)
	private int zaloga;

	@Column(length=75)
	private String slika;
	
	@Column(name = "Datum")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Calendar datum;
	
	//bi-directional many-to-one association to NakupovalniListIzdelek
	@OneToMany(mappedBy="izdelek")
	private List<NakupovalniListIzdelek> nakupovalniListIzdeleks;

	//bi-directional many-to-one association to NarociloIzdelek
	@OneToMany(mappedBy="izdelek")
	private List<NarociloIzdelek> narociloIzdeleks;

	public Izdelek() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getDavek() {
		return this.davek;
	}

	public void setDavek(double davek) {
		this.davek = davek;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getZaloga() {
		return this.zaloga;
	}

	public void setZaloga(int zaloga) {
		this.zaloga = zaloga;
	}
	
	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
	
	public java.util.Calendar getDatum() {
		return this.datum;
	}

	public void setDatum(java.util.Calendar datum) {
		this.datum = datum;
	}

	@XmlTransient
	public List<NakupovalniListIzdelek> getNakupovalniListIzdeleks() {
		return this.nakupovalniListIzdeleks;
	}

	public void setNakupovalniListIzdeleks(List<NakupovalniListIzdelek> nakupovalniListIzdeleks) {
		this.nakupovalniListIzdeleks = nakupovalniListIzdeleks;
	}

	public NakupovalniListIzdelek addNakupovalniListIzdelek(NakupovalniListIzdelek nakupovalniListIzdelek) {
		getNakupovalniListIzdeleks().add(nakupovalniListIzdelek);
		nakupovalniListIzdelek.setIzdelek(this);

		return nakupovalniListIzdelek;
	}

	public NakupovalniListIzdelek removeNakupovalniListIzdelek(NakupovalniListIzdelek nakupovalniListIzdelek) {
		getNakupovalniListIzdeleks().remove(nakupovalniListIzdelek);
		nakupovalniListIzdelek.setIzdelek(null);

		return nakupovalniListIzdelek;
	}

	@XmlTransient
	public List<NarociloIzdelek> getNarociloIzdeleks() {
		return this.narociloIzdeleks;
	}

	public void setNarociloIzdeleks(List<NarociloIzdelek> narociloIzdeleks) {
		this.narociloIzdeleks = narociloIzdeleks;
	}

	public NarociloIzdelek addNarociloIzdelek(NarociloIzdelek narociloIzdelek) {
		getNarociloIzdeleks().add(narociloIzdelek);
		narociloIzdelek.setIzdelek(this);

		return narociloIzdelek;
	}

	public NarociloIzdelek removeNarociloIzdelek(NarociloIzdelek narociloIzdelek) {
		getNarociloIzdeleks().remove(narociloIzdelek);
		narociloIzdelek.setIzdelek(null);

		return narociloIzdelek;
	}

}