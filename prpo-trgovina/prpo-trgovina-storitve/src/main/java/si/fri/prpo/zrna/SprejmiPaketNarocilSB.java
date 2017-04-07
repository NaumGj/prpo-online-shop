package si.fri.prpo.zrna;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.vmesniki.Narocilo.SprejmiPaketNarocilSBLocal;
import si.fri.prpo.vmesniki.Narocilo.SprejmiPaketNarocilSBRemote;

/**
 * Session Bean implementation class SprejmiPaketNarocilSB
 */
@Stateless
@Remote(SprejmiPaketNarocilSBRemote.class)
@Local(SprejmiPaketNarocilSBLocal.class)
@PermitAll
@JMSDestinationDefinition(
		name = "java:jboss/jms/queues/NarocilaQueue",
		interfaceName = "javax.jms.Queue",
		destinationName = "NarocilaQueue"
)
public class SprejmiPaketNarocilSB implements SprejmiPaketNarocilSBRemote, SprejmiPaketNarocilSBLocal {
	
	@Inject
	private JMSContext context; 
	
	@Resource(lookup = "java:jboss/jms/queues/NarocilaQueue")
	private Queue vrsta;
	
	@PersistenceContext
	private EntityManager em;	
	
    /**
     * Default constructor. 
     */
    public SprejmiPaketNarocilSB() {
        // TODO Auto-generated constructor stub
    }
    
    
    public void shraniNarocila(List<Narocilo> n) {
    	for(Narocilo narocilo: n) {
    		System.out.println(narocilo.getLokacija_Dostave());
    		context.createProducer().send(vrsta, narocilo);
    	}
    }

}
