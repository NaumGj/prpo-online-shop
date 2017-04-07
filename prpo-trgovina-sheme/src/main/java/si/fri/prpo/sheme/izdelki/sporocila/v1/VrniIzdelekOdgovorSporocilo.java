
package si.fri.prpo.sheme.izdelki.sporocila.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import si.fri.prpo.sheme.izdelki.v1.IzdelkiType;


/**
 * <p>Java class for VrniIzdelekOdgovorSporocilo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VrniIzdelekOdgovorSporocilo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="izdelek" type="{http://prpo.fri.si/sheme/izdelki/v1}IzdelkiType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VrniIzdelekOdgovorSporocilo", propOrder = {
    "izdelek"
})
public class VrniIzdelekOdgovorSporocilo {

    protected IzdelkiType izdelek;

    /**
     * Gets the value of the izdelek property.
     * 
     * @return
     *     possible object is
     *     {@link IzdelkiType }
     *     
     */
    public IzdelkiType getIzdelek() {
        return izdelek;
    }

    /**
     * Sets the value of the izdelek property.
     * 
     * @param value
     *     allowed object is
     *     {@link IzdelkiType }
     *     
     */
    public void setIzdelek(IzdelkiType value) {
        this.izdelek = value;
    }

}
