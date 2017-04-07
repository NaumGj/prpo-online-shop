package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the nakupovalni_list database table.
 * 
 */
@Entity
@Table(name="nakupovalni_list")
@NamedQuery(name="NakupovalniList.findAll", query="SELECT n FROM NakupovalniList n")
@XmlRootElement
public class NakupovalniList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to NakupovalniListIzdelek
	@OneToMany(mappedBy="nakupovalniList")
	private List<NakupovalniListIzdelek> nakupovalniListIzdeleks;

	//bi-directional many-to-one association to UporabnikNakupovalniList
	@OneToMany(mappedBy="nakupovalniList")
	private List<UporabnikNakupovalniList> uporabnikNakupovalniLists;

	public NakupovalniList() {
	}

	@XmlID
	@XmlElement
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
		nakupovalniListIzdelek.setNakupovalniList(this);

		return nakupovalniListIzdelek;
	}

	public NakupovalniListIzdelek removeNakupovalniListIzdelek(NakupovalniListIzdelek nakupovalniListIzdelek) {
		getNakupovalniListIzdeleks().remove(nakupovalniListIzdelek);
		nakupovalniListIzdelek.setNakupovalniList(null);

		return nakupovalniListIzdelek;
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
		uporabnikNakupovalniList.setNakupovalniList(this);

		return uporabnikNakupovalniList;
	}

	public UporabnikNakupovalniList removeUporabnikNakupovalniList(UporabnikNakupovalniList uporabnikNakupovalniList) {
		getUporabnikNakupovalniLists().remove(uporabnikNakupovalniList);
		uporabnikNakupovalniList.setNakupovalniList(null);

		return uporabnikNakupovalniList;
	}

}