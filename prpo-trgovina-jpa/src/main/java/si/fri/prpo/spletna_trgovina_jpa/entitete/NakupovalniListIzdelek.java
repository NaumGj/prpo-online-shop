package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the nakupovalni_list_izdelek database table.
 * 
 */
@Entity
@Table(name="nakupovalni_list_izdelek")
@NamedQueries({
	@NamedQuery(name="NakupovalniListIzdelek.findAll", query="SELECT n FROM NakupovalniListIzdelek n"),
	@NamedQuery(name="vseOdNakupovalnegaLista", query="SELECT n FROM NakupovalniListIzdelek n WHERE n.nakupovalniList = :nakupovalniList"),
	@NamedQuery(name="vsiIzdelkiNakupovalnegaLista", query="SELECT n.izdelek, n.kolicina FROM NakupovalniListIzdelek n WHERE n.nakupovalniList = :nakupovalniList"),
	@NamedQuery(name="NakupovalniListIzdelka", query="SELECT u FROM NakupovalniListIzdelek u WHERE u.izdelek = :izdelek AND u.nakupovalniList  = :nakupovalniList")
})
@XmlRootElement
public class NakupovalniListIzdelek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private int kolicina;

	//bi-directional many-to-one association to Izdelek
	@ManyToOne
	private Izdelek izdelek;

	//bi-directional many-to-one association to NakupovalniList
	@ManyToOne
	@JoinColumn(name="Nakupovalni_list_Id")
	private NakupovalniList nakupovalniList;

	public NakupovalniListIzdelek() {
	}

	@XmlID
	@XmlElement
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	@XmlIDREF
	public Izdelek getIzdelek() {
		return this.izdelek;
	}

	public void setIzdelek(Izdelek izdelek) {
		this.izdelek = izdelek;
	}

	@XmlIDREF
	public NakupovalniList getNakupovalniList() {
		return this.nakupovalniList;
	}

	public void setNakupovalniList(NakupovalniList nakupovalniList) {
		this.nakupovalniList = nakupovalniList;
	}

}