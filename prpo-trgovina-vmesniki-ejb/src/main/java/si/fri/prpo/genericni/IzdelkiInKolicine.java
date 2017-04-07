package si.fri.prpo.genericni;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IzdelkiInKolicine{
    private List<Integer> idIzdelkov;
    private List<Integer> kolicine;

    public IzdelkiInKolicine() {}

    public IzdelkiInKolicine(List<Integer> idIzdelkov, List<Integer> kolicine){
    	this.idIzdelkov = idIzdelkov;
    	this.kolicine = kolicine;
    }

    @XmlElement
    public List<Integer> getIdIzdelkov(){
    	return this.idIzdelkov;
    }
    
    @XmlElement
    public List<Integer> getKolicine(){
    	return this.kolicine;
    }
}
