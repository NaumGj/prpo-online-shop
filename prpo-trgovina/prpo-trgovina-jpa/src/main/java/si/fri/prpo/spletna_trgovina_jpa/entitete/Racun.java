package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the racun database table.
 * 
 */
@Entity
@NamedQuery(name="Racun.findAll", query="SELECT r FROM Racun r")
@XmlRootElement
public class Racun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private double cena;

	private String nacin_placila;

	//bi-directional many-to-one association to Narocilo
	@OneToOne(mappedBy="racun")
	private Narocilo narocilo;

	public Racun() {
	}

	@XmlID
	@XmlElement
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

	public String getNacin_placila() {
		return this.nacin_placila;
	}

	public void setNacin_placila(String nacin_placila) {
		this.nacin_placila = nacin_placila;
	}

	@XmlTransient
	public Narocilo getNarocilo() {
		return this.narocilo;
	}

	public void setNarocilo(Narocilo narocilo) {
		this.narocilo = narocilo;
	}

	public Narocilo removeNarocilo(Narocilo narocilo) {
		narocilo.setRacun(null);

		return narocilo;
	}

}