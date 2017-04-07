
package si.fri.prpo.sheme.izdelki.sporocila.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import si.fri.prpo.sheme.izdelki.v1.NapakeType;


/**
 * <p>Java class for VrniIzdelekFaultSporocilo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VrniIzdelekFaultSporocilo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="napaka" type="{http://prpo.fri.si/sheme/napake/v1}NapakeType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VrniIzdelekFaultSporocilo", propOrder = {
    "napaka"
})
public class VrniIzdelekFaultSporocilo {

    protected NapakeType napaka;

    /**
     * Gets the value of the napaka property.
     * 
     * @return
     *     possible object is
     *     {@link NapakeType }
     *     
     */
    public NapakeType getNapaka() {
        return napaka;
    }

    /**
     * Sets the value of the napaka property.
     * 
     * @param value
     *     allowed object is
     *     {@link NapakeType }
     *     
     */
    public void setNapaka(NapakeType value) {
        this.napaka = value;
    }

}
