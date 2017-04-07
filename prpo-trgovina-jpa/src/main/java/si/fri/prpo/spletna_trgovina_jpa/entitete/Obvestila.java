package si.fri.prpo.spletna_trgovina_jpa.entitete;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
	@NamedQuery(name="Obvestila.findAll", query="SELECT o FROM Obvestila o")
})
@XmlRootElement
public class Obvestila implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String obvestilo;
	
	//bi-directional many-to-one association to Narocilo
	@ManyToOne
	private Narocilo narocilo;
	
	public Obvestila() {
	}

	@XmlID
	@XmlElement
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObvestilo() {
		return this.obvestilo;
	}

	public void setObvestilo(String obvestilo) {
		this.obvestilo = obvestilo;
	}

	@XmlIDREF
	public Narocilo getNarocilo() {
		return this.narocilo;
	}

	public void setNarocilo(Narocilo narocilo) {
		this.narocilo = narocilo;
	}
	
}
