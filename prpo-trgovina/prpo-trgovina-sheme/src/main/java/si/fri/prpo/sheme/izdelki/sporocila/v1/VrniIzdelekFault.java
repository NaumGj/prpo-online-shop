
package si.fri.prpo.sheme.izdelki.sporocila.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fault" type="{http://prpo.fri.si/sheme/izdelki/sporocila/v1}VrniIzdelekFaultSporocilo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fault"
})
@XmlRootElement(name = "vrniIzdelekFault", namespace = "http://prpo.fri.si/storitve/izdelki/v1")
public class VrniIzdelekFault {

    @XmlElement(namespace = "", required = true)
    protected VrniIzdelekFaultSporocilo fault;

    /**
     * Gets the value of the fault property.
     * 
     * @return
     *     possible object is
     *     {@link VrniIzdelekFaultSporocilo }
     *     
     */
    public VrniIzdelekFaultSporocilo getFault() {
        return fault;
    }

    /**
     * Sets the value of the fault property.
     * 
     * @param value
     *     allowed object is
     *     {@link VrniIzdelekFaultSporocilo }
     *     
     */
    public void setFault(VrniIzdelekFaultSporocilo value) {
        this.fault = value;
    }

}
