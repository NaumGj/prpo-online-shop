package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


/**
 * The persistent class for the narocilo database table.
 * 
 */
@Entity
@NamedQueries({
	// pridobitev vseh izdelkov narocila
	@NamedQuery(name="Narocilo.pridobiIzdelkeNarocila", query="SELECT i, ni.kolicina FROM Narocilo n JOIN n.narociloIzdeleks ni JOIN ni.izdelek i WHERE n.id = :id"),

	// pridobitev racuna narocila
	@NamedQuery(name="Narocilo.pridobiRacunNarocila", query="SELECT r FROM Narocilo n JOIN n.racun r WHERE n.id = :id"),	
	
	@NamedQuery(name="Narocilo.findAll", query="SELECT n FROM Narocilo n")
})
@XmlRootElement
public class Narocilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="`lokacija _dostave`")
	private String lokacija_Dostave;

	private String nacin_prevzema;

	//bi-directional one-to-one association to Racun
	@OneToOne
	private Racun racun;

	//bi-directional many-to-one association to Uporabnik
	@ManyToOne
	private Uporabnik uporabnik;

	//bi-directional many-to-one association to NarociloIzdelek
	@OneToMany(mappedBy="narocilo", cascade=CascadeType.REMOVE)
	private List<NarociloIzdelek> narociloIzdeleks;
	
	//bi-directional many-to-one association to NarociloIzdelek
	@OneToMany(mappedBy="narocilo", cascade=CascadeType.REMOVE)
	private List<Obvestila> obvestilas;


	public Narocilo() {
	}

	@XmlID
	@XmlElement
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLokacija_Dostave() {
		return this.lokacija_Dostave;
	}

	public void setLokacija_Dostave(String lokacija_Dostave) {
		this.lokacija_Dostave = lokacija_Dostave;
	}

	public String getNacin_prevzema() {
		return this.nacin_prevzema;
	}

	public void setNacin_prevzema(String nacin_prevzema) {
		this.nacin_prevzema = nacin_prevzema;
	}

	@XmlIDREF
	public Racun getRacun() {
		return this.racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	@XmlIDREF
//	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = Uporabnik.class)
	public Uporabnik getUporabnik() {
		return this.uporabnik;
	}

	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
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
		narociloIzdelek.setNarocilo(this);

		return narociloIzdelek;
	}

	public NarociloIzdelek removeNarociloIzdelek(NarociloIzdelek narociloIzdelek) {
		getNarociloIzdeleks().remove(narociloIzdelek);
		narociloIzdelek.setNarocilo(null);

		return narociloIzdelek;
	}

	@XmlTransient
	public List<Obvestila> getObvestilas() {
		return this.obvestilas;
	}

	public void setObvestilas(List<Obvestila> obvestilas) {
		this.obvestilas = obvestilas;
	}

	public Obvestila addObvestila(Obvestila obvestila) {
		getObvestilas().add(obvestila);
		obvestila.setNarocilo(this);

		return obvestila;
	}

	public Obvestila removeObvestila(Obvestila obvestila) {
		getObvestilas().remove(obvestila);
		obvestila.setNarocilo(null);

		return obvestila;
	}

}