package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the narocilo_izdelek database table.
 * 
 */
@Entity
@Table(name="narocilo_izdelek")
@NamedQuery(name="NarociloIzdelek.findAll", query="SELECT n FROM NarociloIzdelek n")
@XmlRootElement
public class NarociloIzdelek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private int kolicina;

	//bi-directional many-to-one association to Izdelek
	@ManyToOne
	private Izdelek izdelek;

	//bi-directional many-to-one association to Narocilo
	@ManyToOne
	private Narocilo narocilo;

	public NarociloIzdelek() {
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
	public Narocilo getNarocilo() {
		return this.narocilo;
	}

	public void setNarocilo(Narocilo narocilo) {
		this.narocilo = narocilo;
	}

}