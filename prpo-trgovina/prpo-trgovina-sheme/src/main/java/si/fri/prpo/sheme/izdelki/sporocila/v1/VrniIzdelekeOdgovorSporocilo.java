
package si.fri.prpo.sheme.izdelki.sporocila.v1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import si.fri.prpo.sheme.izdelki.v1.IzdelkiType;


/**
 * <p>Java class for VrniIzdelekeOdgovorSporocilo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VrniIzdelekeOdgovorSporocilo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="izdelek" type="{http://prpo.fri.si/sheme/izdelki/v1}IzdelkiType" maxOccurs="200" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VrniIzdelekeOdgovorSporocilo", propOrder = {
    "izdelek"
})
public class VrniIzdelekeOdgovorSporocilo {

    protected List<IzdelkiType> izdelek;

    /**
     * Gets the value of the izdelek property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the izdelek property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIzdelek().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IzdelkiType }
     * 
     * 
     */
    public List<IzdelkiType> getIzdelek() {
        if (izdelek == null) {
            izdelek = new ArrayList<IzdelkiType>();
        }
        return this.izdelek;
    }
    
    public void setIzdeleki(List<IzdelkiType> izdeleki) {
        this.izdelek = izdeleki;
    }

}
