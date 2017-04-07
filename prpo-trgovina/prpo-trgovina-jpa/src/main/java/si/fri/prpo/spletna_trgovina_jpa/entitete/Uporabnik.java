package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


/**
 * The persistent class for the uporabnik database table.
 * 
 */
@Entity
@NamedQueries({
	// izbira uporabika po uporabniške imenu
	@NamedQuery(name="Uporabnik.izberiUporabnikaPoUporabniskemImenu", query="SELECT u FROM Uporabnik u WHERE u.uporabnisko_ime = :uporabniskoIme"),
	
	// izbira uporabika po id-ju
	@NamedQuery(name="Uporabnik.izberiUporabnikaPoId", query="SELECT u FROM Uporabnik u WHERE u.id = :id"),
	
	// izbriši uporabika po id-ju
	@NamedQuery(name="Uporabnik.izbrisiUporabnikaPoId", query="DELETE FROM Uporabnik u WHERE u.id = :id"),
	
	// izbira uporabika po imenu
	@NamedQuery(name="Uporabnik.izberiUporabnikaPoImenu", query="SELECT u FROM Uporabnik u WHERE u.ime = :ime"),
	
	// posodobitev uporabnika s fiksnim priimkom in emailom
	@NamedQuery(name="Uporabnik.posodobiFiksnePriimekInEmail", query="UPDATE Uporabnik u SET u.priimek = 'Gjorgjeski', u.e_mail = 'naum@naum.com' WHERE u.ime = :ime"),
	
	// pridobitev narocil dolocenega uporabnika
	@NamedQuery(name="Uporabnik.pridobiNarocilaUporabnika", query="SELECT n FROM Uporabnik u JOIN u.narocilos n WHERE u.uporabnisko_ime= :uporabnisko_ime"),

	// vsi uporabniki sortirani po imenu
	@NamedQuery(name="vsiUporabniki", query="SELECT u FROM Uporabnik u ORDER BY u.priimek, u.ime ASC"),
	
	@NamedQuery(name="Uporabnik.findAl", query="SELECT u FROM Uporabnik u")
})
@XmlRootElement
public class Uporabnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="`E-mail`", length=45)
	private String e_mail;

	@Column(length=45)
	private String geslo;

	@Column(nullable=false, length=45)
	private String ime;

	@Column(nullable=false, length=45)
	private String priimek;

	@Column(nullable=false, length=45)
	private String uporabnisko_ime;

	//bi-directional many-to-one association to Narocilo
	@OneToMany(mappedBy="uporabnik")
	private List<Narocilo> narocilos;

	//bi-directional many-to-one association to UporabnikNakupovalniList
	@OneToMany(mappedBy="uporabnik")
	private List<UporabnikNakupovalniList> uporabnikNakupovalniLists;

	public Uporabnik() {
	}

	@XmlID
	@XmlElement
//	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = Uporabnik.class)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getE_mail() {
		return this.e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getGeslo() {
		return this.geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPriimek() {
		return this.priimek;
	}

	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}

	public String getUporabnisko_ime() {
		return this.uporabnisko_ime;
	}

	public void setUporabnisko_ime(String uporabnisko_ime) {
		this.uporabnisko_ime = uporabnisko_ime;
	}

	@XmlTransient
	public List<Narocilo> getNarocilos() {
		return this.narocilos;
	}

	public void setNarocilos(List<Narocilo> narocilos) {
		this.narocilos = narocilos;
	}

	public Narocilo addNarocilo(Narocilo narocilo) {
		getNarocilos().add(narocilo);
		narocilo.setUporabnik(this);

		return narocilo;
	}

	public Narocilo removeNarocilo(Narocilo narocilo) {
		getNarocilos().remove(narocilo);
		narocilo.setUporabnik(null);

		return narocilo;
	}

	@XmlTransient
	public List<UporabnikNakupovalniList> getUporabnikNakupovalniLists() {
		return this.uporabnikNakupovalniLists;
	}

	public void setUporabnikNakupovalniLists(List<UporabnikNakupovalniList> uporabnikNakupovalniLists) {
		this.uporabnikNakupovalniLists = uporabnikNakupovalniLists;
	}

	public UporabnikNakupovalniList addUporabnikNakupovalniList(UporabnikNakupovalniList uporabnikNakupovalniList) {
		getUporabnikNakupovalniLists().add(uporabnikNakupovalniList);
		uporabnikNakupovalniList.setUporabnik(this);

		return uporabnikNakupovalniList;
	}

	public UporabnikNakupovalniList removeUporabnikNakupovalniList(UporabnikNakupovalniList uporabnikNakupovalniList) {
		getUporabnikNakupovalniLists().remove(uporabnikNakupovalniList);
		uporabnikNakupovalniList.setUporabnik(null);

		return uporabnikNakupovalniList;
	}

}