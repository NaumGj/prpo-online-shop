package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the uporabnik_nakupovalni_list database table.
 * 
 */
@Entity
@Table(name="uporabnik_nakupovalni_list")
@NamedQueries({
	@NamedQuery(name="UporabnikNakupovalniList.findAll", query="SELECT u FROM UporabnikNakupovalniList u"),
	@NamedQuery(name="UporabnikoviNakupovalniListi", query="SELECT u.nakupovalniList FROM UporabnikNakupovalniList u WHERE u.uporabnik = :uporabnik"),
	@NamedQuery(name="UporabnikiNakupovalnegaLista", query="SELECT u.uporabnik FROM UporabnikNakupovalniList u WHERE u.nakupovalniList = :nakupovalniList"),
	@NamedQuery(name="VseOdNakupovalnegaLista", query="SELECT u FROM UporabnikNakupovalniList u WHERE u.nakupovalniList = :nakupovalniList"),
	@NamedQuery(name="NakupovalniListUporabnika", query="SELECT u FROM UporabnikNakupovalniList u WHERE u.uporabnik = :uporabnik AND u.nakupovalniList  = :nakupovalniList")
})
@XmlRootElement
public class UporabnikNakupovalniList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to NakupovalniList
	@ManyToOne
	@JoinColumn(name="Nakupovalni_list_Id")
	private NakupovalniList nakupovalniList;

	//bi-directional many-to-one association to Uporabnik
	@ManyToOne
	private Uporabnik uporabnik;

	public UporabnikNakupovalniList() {
	}

	@XmlID
	@XmlElement
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlIDREF
	public NakupovalniList getNakupovalniList() {
		return this.nakupovalniList;
	}

	public void setNakupovalniList(NakupovalniList nakupovalniList) {
		this.nakupovalniList = nakupovalniList;
	}

	@XmlIDREF
	public Uporabnik getUporabnik() {
		return this.uporabnik;
	}

	public void setUporabnik(Uporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}

}