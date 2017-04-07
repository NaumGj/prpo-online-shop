
package si.fri.prpo.sheme.izdelki.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NapakeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NapakeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="koda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sporocilo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NapakeType", namespace = "http://prpo.fri.si/sheme/napake/v1", propOrder = {
    "status",
    "koda",
    "sporocilo"
})
public class NapakeType {

    protected Integer status;
    protected String koda;
    protected String sporocilo;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the koda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKoda() {
        return koda;
    }

    /**
     * Sets the value of the koda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKoda(String value) {
        this.koda = value;
    }

    /**
     * Gets the value of the sporocilo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSporocilo() {
        return sporocilo;
    }

    /**
     * Sets the value of the sporocilo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSporocilo(String value) {
        this.sporocilo = value;
    }

}
