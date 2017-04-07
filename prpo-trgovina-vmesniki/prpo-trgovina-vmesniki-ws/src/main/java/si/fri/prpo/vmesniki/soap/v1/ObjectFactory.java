
package si.fri.prpo.vmesniki.soap.v1;

import javax.xml.bind.annotation.XmlRegistry;

import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekFault;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekFaultSporocilo;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekOdgovorSporocilo;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekRequest;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekResponse;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekZahtevaSporocilo;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekeOdgovorSporocilo;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekeRequest;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekeResponse;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekeZahtevaSporocilo;
import si.fri.prpo.sheme.izdelki.v1.IzdelkiType;
import si.fri.prpo.sheme.izdelki.v1.NapakeType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.fri.prpo.soap.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.fri.prpo.soap.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VrniIzdelekZahtevaSporocilo }
     * 
     */
    public VrniIzdelekZahtevaSporocilo createVrniIzdelekZahtevaSporocilo() {
        return new VrniIzdelekZahtevaSporocilo();
    }

    /**
     * Create an instance of {@link VrniIzdelekOdgovorSporocilo }
     * 
     */
    public VrniIzdelekOdgovorSporocilo createVrniIzdelekOdgovorSporocilo() {
        return new VrniIzdelekOdgovorSporocilo();
    }

    /**
     * Create an instance of {@link VrniIzdelekeZahtevaSporocilo }
     * 
     */
    public VrniIzdelekeZahtevaSporocilo createVrniIzdelekeZahtevaSporocilo() {
        return new VrniIzdelekeZahtevaSporocilo();
    }

    /**
     * Create an instance of {@link VrniIzdelekeOdgovorSporocilo }
     * 
     */
    public VrniIzdelekeOdgovorSporocilo createVrniIzdelekeOdgovorSporocilo() {
        return new VrniIzdelekeOdgovorSporocilo();
    }

    /**
     * Create an instance of {@link VrniIzdelekFaultSporocilo }
     * 
     */
    public VrniIzdelekFaultSporocilo createVrniIzdelekFaultSporocilo() {
        return new VrniIzdelekFaultSporocilo();
    }

    /**
     * Create an instance of {@link NapakeType }
     * 
     */
    public NapakeType createNapakeType() {
        return new NapakeType();
    }

    /**
     * Create an instance of {@link IzdelkiType }
     * 
     */
    public IzdelkiType createIzdelkiType() {
        return new IzdelkiType();
    }

    /**
     * Create an instance of {@link VrniIzdelekRequest }
     * 
     */
    public VrniIzdelekRequest createVrniIzdelekRequest() {
        return new VrniIzdelekRequest();
    }

    /**
     * Create an instance of {@link VrniIzdelekResponse }
     * 
     */
    public VrniIzdelekResponse createVrniIzdelekResponse() {
        return new VrniIzdelekResponse();
    }

    /**
     * Create an instance of {@link VrniIzdelekFault }
     * 
     */
    public VrniIzdelekFault createVrniIzdelekFault() {
        return new VrniIzdelekFault();
    }

    /**
     * Create an instance of {@link VrniIzdelekeRequest }
     * 
     */
    public VrniIzdelekeRequest createVrniIzdelekeRequest() {
        return new VrniIzdelekeRequest();
    }

    /**
     * Create an instance of {@link VrniIzdelekeResponse }
     * 
     */
    public VrniIzdelekeResponse createVrniIzdelekeResponse() {
        return new VrniIzdelekeResponse();
    }

}
